

package com.yundao.tenant.app.service.report.customer;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.report.customer.UserAddCustomerRankReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年12月5日 上午9:51:38 
 * @author   欧阳利
 * @version   
 */
public interface UserAddCustomerRankService {
	public Result<ItemListDTO> getUserRankPage(UserAddCustomerRankReqDto reqDto)throws BaseException;
}

