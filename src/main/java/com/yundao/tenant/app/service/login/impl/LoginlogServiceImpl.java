package com.yundao.tenant.app.service.login.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.dto.login.UserAccountModel;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.service.AbstractService;
import com.yundao.core.service.login.UserAccountService;
import com.yundao.core.threadlocal.ThreadLocalUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.HeaderConstant;
import com.yundao.tenant.app.constant.url.ScmUrl;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.UserBaseData;
import com.yundao.tenant.app.dto.login.LoginLogAddReqDto;
import com.yundao.tenant.app.dto.login.UserLoginResDto;
import com.yundao.tenant.app.dto.login.UserloginReqDto;
import com.yundao.tenant.app.dto.user.UserDetailResDto;
import com.yundao.tenant.app.model.tenant.TenantModel;
import com.yundao.tenant.app.service.login.LoginlogService;
import com.yundao.tenant.app.service.user.impl.UserService;
import com.yundao.tenant.app.util.AddressData;
import com.yundao.tenant.app.util.AddressUtils;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.UserUtils;

/**
 * Created by gjl on 2017/12/1.
 */
@Service
public class LoginlogServiceImpl extends AbstractService implements LoginlogService {

	private static Log log = LogFactory.getLog(LoginlogServiceImpl.class);

	@Override
	public void logError(UserloginReqDto loginReqDto, HttpServletRequest request, String message,
			List<TenantModel> tenantModels) throws BaseException {
		Map<String, Object> accountMap = new HashMap<>();
		accountMap.put("account", loginReqDto.getUserName());
		Result<UserAccountModel> result = HttpUtils.get(ScmUrl.GET_BY_ACCOUNT, accountMap,
				new BaseTypeReference<Result<UserAccountModel>>() {
				});
		if (result.getSuccess() && result.getResult() != null) {
			UserAccountModel userAccountModel = result.getResult();

			LoginLogAddReqDto logAddReqDto = getCommon(request);
			logAddReqDto.setLoginAccount(loginReqDto.getUserName());
			loginReqDto.setPassword(null);
			String params = JsonUtils.objectToJson(loginReqDto);
			logAddReqDto.setParams(params);
			logAddReqDto.setResult(0);
			logAddReqDto.setResultMessage(message);
			Map<String, Object> paramsMap = ArgsUtils.toMap(logAddReqDto);
			if (tenantModels != null && !tenantModels.isEmpty()) {
				for (TenantModel tenantModel : tenantModels) {
					// 获取用户在当前租户中的数据,只用存在的租户才保存登录信息
					Map<String, Object> headerParams = new HashMap<>();
					headerParams.put(HeaderConstant.HEADER_USER_ID, userAccountModel.getId() + "");
					headerParams.put(HeaderConstant.HEADER_TENANT_ID, tenantModel.getId() + "");
					Map<String, Object> userMap = new HashMap<>();
					userMap.put("id", userAccountModel.getId() + "");
					Result<UserDetailResDto> userDetailResult = HttpUtils.get(TenantUrl.GET_USER_BY_ID, headerParams,
							userMap, new BaseTypeReference<Result<UserDetailResDto>>() {
							});
					if (userDetailResult.getSuccess() && userDetailResult.getResult() != null) {
						log.info("租户ID为：" + tenantModel.getId() + ",新增用户登录错误日志：" + paramsMap);
						Map<String, Object> userHeaderParams = new HashMap<>();
						userHeaderParams.put(HeaderConstant.HEADER_USER_ID, userAccountModel.getId() + "");
						userHeaderParams.put(HeaderConstant.HEADER_TENANT_ID, tenantModel.getId() + "");
						Result<Long> addResult = HttpUtils.post(TenantUrl.LOGIN_LOG_ADD, userHeaderParams, paramsMap,
								new BaseTypeReference<Result<Long>>() {
								});
						log.info("租户ID为：" + tenantModel.getId() + ",新增用户登录错误日志返回：" + JsonUtils.objectToJson(addResult));
					}
				}
			}
		}

	}

	private LoginLogAddReqDto getCommon(HttpServletRequest request) {
		LoginLogAddReqDto addReqDto = new LoginLogAddReqDto();
		String ip = ThreadLocalUtils.getIp();
		addReqDto.setLoginIp(ip);
		AddressData addressData = AddressUtils.getAddresses(ip);
		addReqDto.setLoginAddress(addressData);
		addReqDto.setModel(request.getHeader(HeaderConstant.X_REQUEST_DEVICE_NAME));
		String deviceOSPlatform = request.getHeader(HeaderConstant.X_REQUEST_DEVICE_OS_PLATFORM);
		if (StringUtils.isNotBlank(deviceOSPlatform)) {
			if ("IOS".equals(deviceOSPlatform)) {
				addReqDto.setLoginType(2);
			} else {
				addReqDto.setLoginType(1);
			}
		}
		addReqDto.setVersion(request.getHeader(HeaderConstant.X_REQUEST_DEVICE_OS));
		return addReqDto;
	}

	@Override
	public void logSuccess(HttpServletRequest request) throws BaseException {
		Map<String, Object> userMethodParams = new HashMap<>();
		userMethodParams.put("id", UserUtils.getUserId() + "");
		Result<UserDetailResDto> userDetailResult = HttpUtils.get(TenantUrl.GET_USER_BY_ID, userMethodParams,
				new BaseTypeReference<Result<UserDetailResDto>>() {
				});
		if (userDetailResult.getSuccess() && userDetailResult.getResult() != null) {
			UserDetailResDto userDetailResDto = userDetailResult.getResult();

			LoginLogAddReqDto logAddReqDto = getCommon(request);
			logAddReqDto.setLoginAccount(userDetailResDto.getMobile());
			String params = JsonUtils.objectToJson(UserUtils.getCurrentUser());
			logAddReqDto.setParams(params);
			// 资源和权限不记录
			logAddReqDto.setResult(1);

			UserLoginResDto newUserDetail = new UserLoginResDto();
			BeanUtils.copyProperties(userDetailResDto, newUserDetail);
			logAddReqDto.setResultMessage(JsonUtils.objectToJson(newUserDetail));
			logAddReqDto.setUserAccountId(userDetailResDto.getId());
			Map<String, Object> paramsMap = ArgsUtils.toMap(logAddReqDto);
			log.info("新增用户日志：" + paramsMap);
			Result<Long> logResult = HttpUtils.post(TenantUrl.LOGIN_LOG_ADD, paramsMap,
					new BaseTypeReference<Result<Long>>() {
					});

			log.info("新增用户日志返回：" + JsonUtils.objectToJson(logResult));
		}
	}
}
