

package com.yundao.tenant.app.service.product;

import java.util.List;
import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.model.product.BaseProductNoticeType;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年10月18日 下午4:42:01 
 * @author   wucq
 * @version   
 */
public interface ProductNoticeTypeService {
	public Result<Map<String, Object>> getAll() throws BaseException ;
}

