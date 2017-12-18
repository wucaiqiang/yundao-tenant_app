
package com.yundao.tenant.app.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.utils.RequestUtils;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.user.UserBaseUpdateDto;
import com.yundao.tenant.app.service.user.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年11月13日 上午10:46:32
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/user/")
@ResponseBody
@Api("员工相关")
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "通过真实姓名查找在职理财师", notes = "通过真实姓名查找在职理财师")
	@RequestMapping(value = "/get_manager", method = RequestMethod.POST)
	public Result<ItemListDTO> getManagerByRealName(@RequestParam(defaultValue = "") String realName)
			throws BaseException {
		return userService.getManagerByRealName(realName);
	}

	@ApiOperation(value = "通过真实姓名查找在职客服", notes = "通过真实姓名查找在职客服")
	@RequestMapping(value = "/get_customer_server", method = RequestMethod.POST)
	public Result<ItemListDTO> getCustomerServerByRealName(@RequestParam(defaultValue = "") String realName)
			throws BaseException {
		return userService.getCustomerServerByRealName(realName);
	}

	@ApiOperation(value = "个人资料编辑", notes = "个人资料编辑")
	@RequestMapping(value = "/update_base_info", method = RequestMethod.POST)
	public Result<Long> updateBaseInfo(HttpServletRequest request) throws Exception {
		Map<String, String> params = RequestUtils.getParameterMap(request);
		return userService.updateBaseInfo(params);
	}
}
