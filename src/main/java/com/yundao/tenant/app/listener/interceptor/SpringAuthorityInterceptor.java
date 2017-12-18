package com.yundao.tenant.app.listener.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.method.HandlerMethod;

import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.exception.BaseException;
import com.yundao.core.interceptor.spring.AbstractSpringInterceptor;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.threadlocal.config.ThreadLocalConfigEnum;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.annotation.CommonField;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.HeaderConstant;
import com.yundao.tenant.app.constant.url.ScmUrl;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.UserBaseData;
import com.yundao.tenant.app.dto.user.UserAccountResDto;
import com.yundao.tenant.app.dto.user.UserDetailResDto;
import com.yundao.tenant.app.enums.user.EnabledEnum;
import com.yundao.tenant.app.model.tenant.TenantModel;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.ThreadContextUtils;

/**
 * spring权限拦截器
 * 
 * @author xubingsong
 * 
 */
public class SpringAuthorityInterceptor extends AbstractSpringInterceptor {

	private static final Log logger = LogFactory.getLog(SpringAuthorityInterceptor.class);
	private static final Class<CommonField> annotationClass = CommonField.class;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		CommonField commonField = null;

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Class<?> clazz = handlerMethod.getBeanType();// 获取被拦截的类
			if (null != clazz && clazz.isAnnotationPresent(annotationClass)) { // 如果类已经被注解,
				commonField = clazz.getAnnotation(annotationClass);

			} else {
				Method method = handlerMethod.getMethod();
				if (null != method && method.isAnnotationPresent(annotationClass)) {
					commonField = method.getAnnotation(annotationClass);
				}
			}
		}
		if (null == commonField) {
			// 按默认检查,只检查前端所传ticket是否有效,及租户code是否有效
			validateTicketAndTenantCode(request);
		} else {
			boolean needCertificate = commonField.certificate();
			boolean needTenantCode = commonField.tenantCode();
			if (needCertificate || needTenantCode) {
				validateTicketAndTenantCode(request);
			}
			boolean needAppId = commonField.appId();
			if (needAppId) {
				validateAppId(request);
			}
			boolean needDeviceId = commonField.deviceId();
			if (needDeviceId) {
				validateDeviceId(request);
			}
			boolean needRequestId = commonField.requestId();
			if (needRequestId) {
				validateRequestId(request);
			}
			boolean needVersion = commonField.version();
			if (needVersion) {
				validateVersion(request);
			}
			boolean needRequestTime = commonField.requestTime();
			if (needRequestTime) {
				validateRequestTime(request);
			}
		}
		return true;
	}

	public void validateRequestTime(HttpServletRequest request) throws Exception {
		String requestTime = request.getHeader(HeaderConstant.REQUEST_TIME);
		if (StringUtils.isBlank(requestTime)) {
			throw new BaseException(CodeConstant.CODE_1300013);
		}
		Long time = NumberUtils.toLong(requestTime);
		int expire = NumberUtils.toInt(ConfigUtils.getValue(ThreadLocalConfigEnum.REQUEST_TIME_EXPIRE.getKey(), "180"));
		if (time == null || time + expire * 1000 < System.currentTimeMillis()) {
			throw new BaseException(CodeConstant.CODE_1300013);
		}
	}

	public void validateVersion(HttpServletRequest request) throws Exception {
		String version = request.getHeader(HeaderConstant.REQUEST_VERSION);
		if (StringUtils.isBlank(version)) {
			throw new BaseException(CodeConstant.CODE_1300012);
		}
	}

	public void validateRequestId(HttpServletRequest request) throws Exception {
		String requestId = request.getHeader(HeaderConstant.REQUEST_ID);
		if (StringUtils.isBlank(requestId)) {
			throw new BaseException(CodeConstant.CODE_1300011);
		}
	}

	public void validateDeviceId(HttpServletRequest request) throws Exception {
		String deviceId = request.getHeader(HeaderConstant.X_REQUEST_DEVICE_ID);
		if (StringUtils.isBlank(deviceId)) {
			throw new BaseException(CodeConstant.CODE_1300010);
		}
	}

	public void validateAppId(HttpServletRequest request) throws Exception {
		String appId = request.getHeader(HeaderConstant.X_REQUEST_APPID);
		if (StringUtils.isBlank(appId)) {
			throw new BaseException(CodeConstant.CODE_1300009);
		}
	}

	public void validateTicketAndTenantCode(HttpServletRequest request) throws Exception {

		String ticket = request.getHeader(HeaderConstant.X_REQUEST_CERTIFICATE);
		if (StringUtils.isBlank(ticket)) {
			throw new BaseException(CodeConstant.CODE_1300005);
		}
		String tenantCode = request.getHeader(HeaderConstant.X_REQUEST_TENANTCODE);
		if (StringUtils.isBlank(tenantCode)) {
			throw new BaseException(CodeConstant.CODE_1300008);
		}
		logger.info("ticket:%s,tenantCode:%s", ticket, tenantCode);

		Map<String, Object> ticketParams = new HashMap<String, Object>();
		ticketParams.put(CommonConstant.TICKET, ticket);
		Result<Boolean> result = HttpUtils.post(ScmUrl.TICKET_VALIDATE, ticketParams,
				new BaseTypeReference<Result<Boolean>>() {
				});
		if (!result.getSuccess()) {
			logger.info("登录已过期,ticket=" + ticket);
			throw new BaseException(CodeConstant.CODE_1300007);
		}

		Result<UserAccountResDto> userAccountResult = HttpUtils.post(ScmUrl.GET_USER_ACCOUNT_BY_TICKET, ticketParams,
				new BaseTypeReference<Result<UserAccountResDto>>() {
				});

		if (!userAccountResult.getSuccess() || userAccountResult.getResult() == null) {
			logger.info("获取用户信息失败,ticket=" + ticket);
			throw new BaseException(CodeConstant.CODE_1300027);
		}

		// 获取用户所属的租户列表信息
		Map<String, Object> tenantMethodParams = new HashMap<>();
		tenantMethodParams.put("accountId", userAccountResult.getResult().getId());
		Result<List<TenantModel>> tenantAllResult = HttpUtils.get(ScmUrl.GETS_BY_ACCOUNT_ID, tenantMethodParams,
				new BaseTypeReference<Result<List<TenantModel>>>() {
				});
		if (!tenantAllResult.getSuccess() || tenantAllResult.getResult() == null
				|| tenantAllResult.getResult().isEmpty()) {
			throw new BaseException(CodeConstant.CODE_1300014);
		}
		// 验证传入的租户是否属于当前用户的信息
		List<TenantModel> tenantModels = tenantAllResult.getResult();
		TenantModel tenantModel = null;
		for (TenantModel tenantModel2 : tenantModels) {
			if (tenantModel2 == null || StringUtils.isBlank(tenantModel2.getCode())) {
				continue;
			}
			if (tenantModel2.getCode().equals(tenantCode)) {
				tenantModel = tenantModel2;
			}
		}
		if (tenantModel == null) {
			throw new BaseException(CodeConstant.CODE_1300014);
		}
		// 封装线程基本数据
		UserBaseData userBaseData = ThreadContextUtils.getUserBaseData();// 保存线程数据

		UserAccountResDto userAccountResDto = userAccountResult.getResult();

		userBaseData.setTicket(ticket);
		userBaseData.setMobile(userAccountResDto.getMobile());
		userBaseData.setHeaderUserId(userAccountResDto.getId());

		userBaseData.setTenantCode(tenantCode);
		userBaseData.setHeaderTenantId(tenantModel.getId());

		// 获取用户在当前租户中的数据
		Map<String, Object> headerParams = new HashMap<>();
		headerParams.put(HeaderConstant.HEADER_USER_ID, userBaseData.getHeaderUserId() + "");
		headerParams.put(HeaderConstant.HEADER_TENANT_ID, tenantModel.getId() + "");
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("id", userAccountResDto.getId() + "");
		Result<UserDetailResDto> userDetailResult = HttpUtils.get(TenantUrl.GET_USER_BY_ID, headerParams, userMap,
				new BaseTypeReference<Result<UserDetailResDto>>() {
				});
		if (userDetailResult == null || userDetailResult.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300014);
		}
		UserDetailResDto userDetailResDto = userDetailResult.getResult();
		userBaseData.setHeaderRealName(userDetailResDto.getRealName());

		if (userDetailResDto.getIsEnabled() != null
				&& EnabledEnum.NOT_ENABLED.getValue() == userDetailResDto.getIsEnabled()) {
			throw new BaseException(CodeConstant.CODE_1300035);
		}

	}

}
