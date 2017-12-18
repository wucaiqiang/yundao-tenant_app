

package com.yundao.tenant.app.service.customer.distribution;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.customer.CustomerMultiIdReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotToCsReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月13日 上午10:14:03 
 * @author   wucq
 * @version   
 */
public interface CustomerDistributionService {
	public Result<PermissionResultDto> doAllotToFp(CustomerAllotReqDto dto) throws BaseException ;
	public Result<PermissionResultDto> doRecycle(CustomerMultiIdReqDto dto) throws BaseException ;
	public Result<Boolean> doAllotToCs(CustomerAllotToCsReqDto dto) throws Exception ;
}

