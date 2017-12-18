
package com.yundao.tenant.app.service.customer.followup;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpPageDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpReqDto;

/**
 * Function: Reason: Date: 2017年8月24日 下午2:10:51
 * 
 * @author wucq
 * @version
 */
public interface CustomerFollowUpService {
	public Result<Long> add(CustomerFollowUpReqDto reqDto) throws BaseException;

	public Result<ItemListDTO> getPage(CustomerFollowUpPageDto reqDto)
			throws BaseException;
}
