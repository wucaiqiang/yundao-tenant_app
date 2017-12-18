

package com.yundao.tenant.app.service.index;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.view.report.Structure1View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月8日 下午5:00:01 
 * @author   wucq
 * @version   
 */
public interface IndexService {
	public Result<ItemListDTO> index() throws BaseException ;
	public Result<Structure1View> getByTimeType(String timeType) throws BaseException ;
}

