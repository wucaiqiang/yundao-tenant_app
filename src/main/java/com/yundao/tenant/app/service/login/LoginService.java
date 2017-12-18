

package com.yundao.tenant.app.service.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.login.RetrievePasswordDto;
import com.yundao.tenant.app.dto.login.RsaPublicResDto;
import com.yundao.tenant.app.dto.login.UserUpdatePasswordDto;
import com.yundao.tenant.app.dto.login.UserloginReqDto;
import com.yundao.tenant.app.view.UserInfoView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年7月25日 下午6:04:22 
 * @author   wucq
 * @version   
 */
public interface LoginService {
	public Result<RsaPublicResDto> selectKeyPairPublic() throws Exception ;
	public Result<Map<String,Object>> refreshTicket(String ticket) throws Exception ;
	public Result<UserInfoView> login(@ModelAttribute  UserloginReqDto loginReqDto,HttpServletRequest request,HttpServletResponse response)throws BaseException;
	public Result<Boolean> logout( HttpServletRequest request, HttpServletResponse response)throws BaseException;
	public Result<Boolean> doSend(String mobile)throws BaseException;
	public Result<Boolean> processRetrievePassword(RetrievePasswordDto dto)throws BaseException;
	public Result<Integer> updatePwdByUser(UserUpdatePasswordDto dto)throws BaseException;
	public Result<Boolean> checkUserPassword(String ciphertext)throws BaseException;
	public Result<Boolean> checkByMobile(String mobile)throws BaseException;
	public Result<Boolean> addLoginLog(HttpServletRequest request)throws BaseException;
}

