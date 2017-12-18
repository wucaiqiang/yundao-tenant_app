

package com.yundao.tenant.app.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.user.UserBaseUpdateDto;
import com.yundao.tenant.app.dto.user.UserOptionResDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月13日 上午10:48:32 
 * @author   wucq
 * @version   
 */
public interface UserService {
	public Result<ItemListDTO> getManagerByRealName(String realName) throws BaseException ;
	public Result<ItemListDTO> getCustomerServerByRealName(String realName) throws BaseException ;
	public Result<List<UserOptionResDto>> getUsersByRealName(String realName) throws BaseException ;
	public Result<Long> updateBaseInfo(Map<String, String> params) throws BaseException ;
}

