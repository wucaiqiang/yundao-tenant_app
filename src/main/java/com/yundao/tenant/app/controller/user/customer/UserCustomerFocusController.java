package com.yundao.tenant.app.controller.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.service.user.customer.UserCustomerFocusService;
import com.yundao.tenant.app.view.customer.UserCustomerFocusView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户关注
 *
 * @author jan
 * @create 2017-08-10 PM2:04
 **/
@Controller
@RequestMapping("/user/customer")
@ResponseBody
@Api("客户关注")
public class UserCustomerFocusController {
	@Autowired
	private UserCustomerFocusService userCustomerFocusService;

	@RequestMapping(value = "/focus", method = RequestMethod.POST)
	@ApiOperation("关注与消息关注")
	public Result<UserCustomerFocusView> focus(@RequestParam String customerId,@RequestParam Boolean requestConcern) throws BaseException {
		return userCustomerFocusService.focus(customerId,requestConcern);
	}

}
