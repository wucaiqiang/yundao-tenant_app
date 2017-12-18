package com.yundao.tenant.app.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.HeaderConstant;
import com.yundao.tenant.app.dto.login.RetrievePasswordDto;
import com.yundao.tenant.app.dto.login.RsaPublicResDto;
import com.yundao.tenant.app.dto.login.UserUpdatePasswordDto;
import com.yundao.tenant.app.dto.login.UserloginReqDto;
import com.yundao.tenant.app.service.login.LoginService;
import com.yundao.tenant.app.view.UserInfoView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理
 * 
 * @author 欧阳利 2017年6月22日
 */
@Controller
@ResponseBody
@RequestMapping("/user")
@Api("登录相关")
public class LoginController {
	@Autowired
	private LoginService loginService;

	/**
	 * 获取公钥 getKeyPairPublic:
	 * 
	 * @author: wucq
	 * @return
	 * @throws Exception
	 * @description:
	 */
	@RequestMapping(value = "/get_key_public", method = RequestMethod.POST)
	@ApiOperation(value = "获取密钥对", notes = "获取密钥对")
	public Result<RsaPublicResDto> getKeyPairPublic(String mobile) throws Exception {
		return loginService.selectKeyPairPublic();
	}

	@RequestMapping(value = "/refresh_ticket", method = RequestMethod.POST)
	@ApiOperation(value = "重新刷新ticket", notes = "重新刷新ticket")
	public Result<Map<String, Object>> refreshTicket(HttpServletRequest request) throws Exception {
		String ticket = request.getHeader(HeaderConstant.X_REQUEST_CERTIFICATE);
		if (StringUtils.isBlank(ticket)) {
			throw new BaseException(CodeConstant.CODE_1300005);
		}
		return loginService.refreshTicket(ticket);
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "登录", notes = "登录")
	public Result<UserInfoView> login(@ModelAttribute UserloginReqDto loginReqDto, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		return loginService.login(loginReqDto, request, response);
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiOperation(value = "退出", notes = "退出")
	public Result<Boolean> logout(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		return loginService.logout(request, response);
	}

	@RequestMapping(value = "/find_pwd_get", method = RequestMethod.POST)
	@ApiOperation(value = "发送找回密码验证码", notes = "发送找回密码验证码")
	public Result<Boolean> sendRetrieveCaptcha(@RequestParam String mobile) throws Exception {
		return loginService.doSend(mobile);
	}

	@RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
	@ApiOperation(value = "找回密码", notes = "找回密码")
	public Result<Boolean> retrievePassword(@ModelAttribute RetrievePasswordDto passwordDto) throws Exception {
		return loginService.processRetrievePassword(passwordDto);
	}

	@RequestMapping(value = "/update_password", method = RequestMethod.POST)
	@ApiOperation(value = "修改登录用户的密码", notes = "修改登陆用户的密码")
	public Result<Integer> updateUserPassword(@ModelAttribute UserUpdatePasswordDto reqDto) throws Exception {
		return loginService.updatePwdByUser(reqDto);
	}

	@RequestMapping(value = "/check_password", method = RequestMethod.POST)
	@ApiOperation(value = "校验用户的密码", notes = "校验用户的密码")
	public Result<Boolean> checkUserPassword(@RequestParam String ciphertext) throws Exception {
		return loginService.checkUserPassword(ciphertext);
	}

	@RequestMapping(value = "/add_login_log", method = RequestMethod.POST)
	@ApiOperation(value = "校验用户的密码", notes = "校验用户的密码")
	public Result<Boolean> addLoginLog(HttpServletRequest request) throws Exception {
		return loginService.addLoginLog(request);
	}

	/**
	 * 校验手机号码是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check_mobile_exist", method = RequestMethod.POST)
	@ApiOperation(value = "检查手机号码是否存在", notes = "检查手机号码是否存在")
	public Result<Boolean> checkMobile(@RequestParam String mobile) throws Exception {
		return loginService.checkByMobile(mobile);
	}
}
