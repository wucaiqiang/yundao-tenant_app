
package com.yundao.tenant.app.service.customer.back.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.customer.back.CustomerBackApplyReqDto;
import com.yundao.tenant.app.service.customer.back.CustomerBackExamineService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年8月29日 下午8:18:48
 * 
 * @author wucq
 * @version
 */
@Service
public class CustomerBackExamineServiceImpl implements CustomerBackExamineService {

	@Override
	public Result<Boolean> apply(CustomerBackApplyReqDto dto) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("customerIds", dto.getCustomerId());
		methodParams.put("reason", dto.getReason());
		return HttpUtils.post(TenantUrl.BACK_CUSTOMER_APPLY, methodParams, new BaseTypeReference<Result<Boolean>>() {
		});
	}

}
