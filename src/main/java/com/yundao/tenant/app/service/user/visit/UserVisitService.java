

package com.yundao.tenant.app.service.user.visit;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.user.visit.UserVisitPageReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月23日 下午8:29:15 
 * @author   wucq
 * @version   
 */
public interface UserVisitService {
	Result<ItemListDTO> getPage(@ModelAttribute UserVisitPageReqDto dto) throws BaseException;
}

