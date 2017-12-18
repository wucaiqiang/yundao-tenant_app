

package com.yundao.tenant.app.service.resource;

import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.model.resource.BaseResource;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月14日 下午4:54:58 
 * @author   wucq
 * @version   
 */
public interface ResourceService {
	public Result<List<String>> getsByUserId() throws BaseException ;
}

