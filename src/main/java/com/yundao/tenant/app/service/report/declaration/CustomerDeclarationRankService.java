

package com.yundao.tenant.app.service.report.declaration;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationListReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationRankReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 下午3:32:54 
 * @author   欧阳利
 * @version   
 */
public interface CustomerDeclarationRankService {
	
	public Result<ItemListDTO> getCustomerRankPage(DeclarationRankReqDto reqDto)throws BaseException;
	
	
	public Result<ItemListDTO> getCustomerListPage(DeclarationListReqDto reqDto)throws BaseException;
}

