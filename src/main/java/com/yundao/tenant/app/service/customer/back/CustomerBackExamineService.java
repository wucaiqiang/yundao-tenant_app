

package com.yundao.tenant.app.service.customer.back;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.customer.back.CustomerBackApplyReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月29日 下午8:18:20 
 * @author   wucq
 * @version   
 */
public interface CustomerBackExamineService {
	public Result<Boolean> apply(CustomerBackApplyReqDto dto) throws BaseException ;
}

