

package com.yundao.tenant.app.service.report.declaration;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:58:25 
 * @author   欧阳利
 * @version   
 */
public interface IndexDeclarationReportService {

	
	 public Result<ItemListNoPageDTO> getIndexDto()throws BaseException;
	
}

