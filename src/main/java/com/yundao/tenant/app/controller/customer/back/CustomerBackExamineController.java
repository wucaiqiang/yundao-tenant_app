
package com.yundao.tenant.app.controller.customer.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.customer.back.CustomerBackApplyReqDto;
import com.yundao.tenant.app.service.customer.back.CustomerBackExamineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月29日 下午8:14:09
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/customer/back")
@ResponseBody
@Api("客户回退")
public class CustomerBackExamineController {
	@Autowired
	private CustomerBackExamineService customerBackExamineService;
	
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ApiOperation(value = "申请回退")
	public Result<Boolean> apply(@ModelAttribute CustomerBackApplyReqDto dto) throws BaseException {
		return customerBackExamineService.apply(dto);
	}
}
