
package com.yundao.tenant.app.service.share.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.ScmUrl;
import com.yundao.tenant.app.dto.UserBaseData;
import com.yundao.tenant.app.enums.domain.DomainNamePlatformCodeEnum;
import com.yundao.tenant.app.model.tenant.DomainNameModel;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.ThreadContextUtils;

/**
 * Function: Reason: Date: 2017年9月29日 下午5:52:34
 * 
 * @author wucq
 * @version
 */
@Service
public class ShareServiceImpl extends AbstractService implements ShareService {

	@Override
	public String getCCShareDomainUrl() throws BaseException {
		UserBaseData userBaseData = ThreadContextUtils.getUserBaseData();// 保存线程数据

		Map<String, Object> methodParams = new HashMap<String, Object>();
		methodParams.put("code", userBaseData.getTenantCode());
		methodParams.put("platformCode", DomainNamePlatformCodeEnum.SERVICE_NUMBER.getValue());
		Result<DomainNameModel> tenantResult = HttpUtils.get(ScmUrl.GETS_DOMAIN_BY_CODE_AND_TYPE, methodParams,
				new BaseTypeReference<Result<DomainNameModel>>() {
				});
		if (tenantResult.getResult() != null) {
			DomainNameModel domainNameModel = tenantResult.getResult();
			return domainNameModel.getUrl();
		}
		return "";

	}

}
