
package com.yundao.tenant.app.service.login.impl;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.enums.AppTypeEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.UUIDUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.HeaderConstant;
import com.yundao.tenant.app.constant.url.ScmUrl;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.UserBaseData;
import com.yundao.tenant.app.dto.keypair.KeyPairResDto;
import com.yundao.tenant.app.dto.login.RetrievePasswordDto;
import com.yundao.tenant.app.dto.login.RsaPublicResDto;
import com.yundao.tenant.app.dto.login.UserAppLoginResDto;
import com.yundao.tenant.app.dto.login.UserLoginResDto;
import com.yundao.tenant.app.dto.login.UserUpdatePasswordDto;
import com.yundao.tenant.app.dto.login.UserloginReqDto;
import com.yundao.tenant.app.dto.user.UserAccountDetailDto;
import com.yundao.tenant.app.dto.user.UserDetailResDto;
import com.yundao.tenant.app.enums.domain.DomainNamePlatformCodeEnum;
import com.yundao.tenant.app.enums.keypair.KeyPairAreaEnum;
import com.yundao.tenant.app.enums.user.EnabledEnum;
import com.yundao.tenant.app.model.tenant.DomainNameModel;
import com.yundao.tenant.app.model.tenant.TenantModel;
import com.yundao.tenant.app.service.login.LoginService;
import com.yundao.tenant.app.service.login.LoginlogService;
import com.yundao.tenant.app.service.product.impl.ProductNoticeServiceImpl;
import com.yundao.tenant.app.service.resource.ResourceService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.ObjectAndByteUtils;
import com.yundao.tenant.app.util.ThreadContextUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.UserInfoView;

/**
 * Function: Reason: Date: 2017年7月25日 下午6:09:01
 * 
 * @author wucq
 * @version
 */
@Service
public class LoginServiceImpl implements LoginService {
	private Log logger = LogFactory.getLog(LoginServiceImpl.class);
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private LoginlogService loginlogService;

	@Override
	public Result<RsaPublicResDto> selectKeyPairPublic() throws Exception {
		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("tenantId", "-1");
		methodParams.put("area", KeyPairAreaEnum.TRAN.getValue());
		Result<KeyPairResDto> result = HttpUtils.get(ScmUrl.KEY_PAIR_GET_BY_TENANT_ID, methodParams,
				new BaseTypeReference<Result<KeyPairResDto>>() {
				});
		KeyPairResDto keyPairResDto = result.getResult();

		KeyPair keyPair = (KeyPair) ObjectAndByteUtils.toObject(keyPairResDto.getKeyPair());
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String uuid = UUIDUtils.getUUID();
		RsaPublicResDto dto = new RsaPublicResDto();
		dto.setExponent(new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
		dto.setModulus(new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
		dto.setRandom(uuid);

		return Result.newSuccessResult(dto);
	}

	@Override
	public Result<Map<String, Object>> refreshTicket(String ticket) throws Exception {
		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("ticket", ticket);
		Result<String> result = HttpUtils.post(ScmUrl.TICKET_REFRESH, methodParams,
				new BaseTypeReference<Result<String>>() {
				});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		Map<String, Object> ticketMap = new HashMap<>();

		Result<List<String>> codesResult = resourceService.getsByUserId();// 获取客户资源权限
		if (codesResult.getResult() != null) {
			ticketMap.put("funPermissionList", codesResult.getResult());
		}
		ticketMap.put("ticket", result.getResult());
		// 分享所需要的C端（服务号）域名
		ticketMap.put("hasServiceNo", false);// 默认为false
		UserBaseData user = UserUtils.getCurrentUser();

		Map<String, Object> demainParams = new HashMap<String, Object>();
		demainParams.put("code", user.getTenantCode());
		demainParams.put("platformCode", DomainNamePlatformCodeEnum.SERVICE_NUMBER.getValue());
		Result<DomainNameModel> tenantResult = HttpUtils.get(ScmUrl.GETS_DOMAIN_BY_CODE_AND_TYPE, demainParams,
				new BaseTypeReference<Result<DomainNameModel>>() {
				});
		logger.info("获取服务号域名为：" + JsonUtils.objectToJson(tenantResult));

		if (tenantResult.getResult() != null && StringUtils.isNotBlank(tenantResult.getResult().getUrl())) {
			ticketMap.put("hasServiceNo", true);
		}

		return Result.newSuccessResult(ticketMap);
	}

	@Override
	public Result<UserInfoView> login(UserloginReqDto loginReqDto, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		List<UserAppLoginResDto> resDtoList = new ArrayList<>();

		// 获取用户所属的租户列表信息
		Map<String, Object> tenantMethodParams = new HashMap<>();
		tenantMethodParams.put("account", loginReqDto.getUserName());
		Result<List<TenantModel>> tenantResult = HttpUtils.get(ScmUrl.GETS_BY_ACCOUNT, tenantMethodParams,
				new BaseTypeReference<Result<List<TenantModel>>>() {
				});
		if (!tenantResult.getSuccess() || tenantResult.getResult() == null || tenantResult.getResult().isEmpty()) {
			throw new BaseException(CodeConstant.CODE_1300014);
		}

		// 登录系统
		// 调用公共库校验用户名和密码
		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("userName", loginReqDto.getUserName());
		methodParams.put("password", loginReqDto.getPassword());
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(HeaderConstant.HEADER_APP_TYPE, AppTypeEnum.B_APP.getType());// pc端登录

		Result<UserLoginResDto> result = HttpUtils.post(ScmUrl.USER_LOGIN, headerParams, methodParams,
				new BaseTypeReference<Result<UserLoginResDto>>() {
				});

		if (result.getResult() == null || !result.getSuccess()) {
			// 1000019或1250001：帐号不存在
			// 1000037 :您的连续5次密码错误, 请找回密码！
			if (result.getCode() != 1000019 && result.getCode() != 1250001 && result.getCode() != 1000037) {
				loginlogService.logError(loginReqDto, request, result.getMessage(), tenantResult.getResult());
			}

			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		UserLoginResDto loginDto = result.getResult();

		Boolean isOriginalPass = loginDto.getOnceEditPwd() != null && loginDto.getOnceEditPwd() == 1 ? false : true;// 默认是未修改过，原始密码

		for (TenantModel tenantModel : tenantResult.getResult()) {
			UserAppLoginResDto dto = new UserAppLoginResDto();
			resDtoList.add(dto);

			dto.setMobile(loginDto.getMobile());
			dto.setUserId(loginDto.getId());
			dto.setTicket(loginDto.getTicket());
			dto.setTenantCode(tenantModel.getCode());
			dto.setCompanyName(tenantModel.getName());

			Map<String, Object> userHeaderParams = new HashMap<>();
			userHeaderParams.put(HeaderConstant.HEADER_USER_ID, loginDto.getId() + "");
			userHeaderParams.put(HeaderConstant.HEADER_TENANT_ID, tenantModel.getId() + "");
			Map<String, Object> userMethodParams = new HashMap<>();
			userMethodParams.put("id", loginDto.getId() + "");
			// 获取用户在各租户中详情信息
			Result<UserDetailResDto> userDetail = HttpUtils.get(TenantUrl.GET_USER_BY_ID, userHeaderParams,
					userMethodParams, new BaseTypeReference<Result<UserDetailResDto>>() {
					});
			if (userDetail.getSuccess() && userDetail.getResult() != null) {
				UserDetailResDto userDetailDto = userDetail.getResult();
				dto.setRoleList(userDetailDto.getRoleTexts());
				dto.setName(userDetailDto.getRealName());
				dto.setDepartment(userDetailDto.getDepartmentName());
				dto.setCardIntroduce(userDetailDto.getCardIntroduce());
				dto.setCardHeaderBit(userDetailDto.getCardHeaderBit());
				dto.setEmail(userDetailDto.getEmail());
				if(StringUtils.isBlank(userDetailDto.getAvatar())){
					// 用户默认头像
					dto.setAvatar(ConfigUtils.getValue("user.default.avater.url"));
				}else{
					dto.setAvatar(userDetailDto.getAvatar());
				}
				dto.setAddress(userDetailDto.getAddress());
				if (userDetailDto.getIsEnabled() != null
						&& EnabledEnum.NOT_ENABLED.getValue().equals(userDetailDto.getIsEnabled())) {
					dto.setEnableEnter(false);
					dto.setTipText("用户已被停用");
				} else {
					dto.setEnableEnter(true);
				}
			}
			
		}

		return Result.newSuccessResult(new UserInfoView(isOriginalPass, resDtoList));

	}

	@Override
	public Result<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		// 调用公共库校验用户名和密码
		Result<Boolean> result = HttpUtils.post(ScmUrl.USER_LOGINOUT, null, new BaseTypeReference<Result<Boolean>>() {
		});
		return result;
	}

	@Override
	public Result<Boolean> doSend(String mobile) throws BaseException {

		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("mobile", mobile);

		Result<Boolean> result = HttpUtils.post(ScmUrl.USER_SEND_RETRIEVE_CAPTCHA, methodParams,
				new BaseTypeReference<Result<Boolean>>() {
				});

		return result;

	}

	@Override
	public Result<Integer> updatePwdByUser(UserUpdatePasswordDto dto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		methodParams.put("userName", ThreadContextUtils.getUserBaseData().getMobile());
		Result<Integer> result = HttpUtils.post(ScmUrl.USER_UPDATE_PASSWORD, methodParams,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;

	}

	@Override
	public Result<Boolean> checkUserPassword(String ciphertext) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("userName", ThreadContextUtils.getUserBaseData().getMobile());
		methodParams.put("password", ciphertext);
		Result<Boolean> result = HttpUtils.post(ScmUrl.USER_CHECK_PASSWORD, methodParams,
				new BaseTypeReference<Result<Boolean>>() {
				});
		return result;

	}

	@Override
	public Result<Boolean> processRetrievePassword(RetrievePasswordDto dto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		Result<Boolean> result = HttpUtils.post(ScmUrl.USER_RETRIEVE_PASSWORD, methodParams,
				new BaseTypeReference<Result<Boolean>>() {
				});
		return result;

	}

	@Override
	public Result<Boolean> checkByMobile(String mobile) throws BaseException {

		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("mobile", mobile);

		Result<UserAccountDetailDto> result = HttpUtils.post(ScmUrl.USER_CHECK_MOBILE, methodParams,
				new BaseTypeReference<Result<UserAccountDetailDto>>() {
				});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		return Result.newSuccessResult(true);

	}

	@Override
	public Result<Boolean> addLoginLog(HttpServletRequest request) throws BaseException {
		loginlogService.logSuccess(request);
		return Result.newSuccessResult(true);
	}

}
