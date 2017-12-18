
package com.yundao.tenant.app.service.customer.distribution.impl;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.customer.CustomerMultiIdReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotToCsReqDto;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年11月13日 上午10:14:48
 * 
 * @author wucq
 * @version
 */
@Service
public class CustomerDistributionService
		implements com.yundao.tenant.app.service.customer.distribution.CustomerDistributionService {

	@Override
	public Result<PermissionResultDto> doAllotToFp(CustomerAllotReqDto dto) throws BaseException {

		return HttpUtils.post(TenantUrl.CUSTOMER_DISTRIBUTION_ALLOT_TO_FP_V2, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<PermissionResultDto>>() {
				});

	}

	@Override
	public Result<PermissionResultDto> doRecycle(CustomerMultiIdReqDto dto) throws BaseException {

		return HttpUtils.post(TenantUrl.CUSTOMER_DISTRIBUTION_RECYCLE_V2, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<PermissionResultDto>>() {
				});

	}

	@Override
	public Result<Boolean> doAllotToCs(CustomerAllotToCsReqDto dto) throws Exception {

		return HttpUtils.post(TenantUrl.CUSTOMER_DISTRIBUTION_ALLOT_TO_CS_V2, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Boolean>>() {
				});

	}

}
