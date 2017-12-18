package com.yundao.tenant.app.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.customer.CustomerMultiIdReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotReqDto;
import com.yundao.tenant.app.dto.customer.distribution.CustomerAllotToCsReqDto;
import com.yundao.tenant.app.service.customer.distribution.CustomerDistributionService;
import com.yundao.tenant.app.util.ArgsUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户分配控制器
 *
 * @author jan
 * @create 2017-08-14 PM4:22
 **/
@Controller
@ResponseBody
@RequestMapping("/customer/distribution")
@Api("客户调配")
public class CustomerDistributionV2Controller {
	@Autowired
	private CustomerDistributionService customerDistributionService;

	@RequestMapping(value = "/allot_to_fp", method = RequestMethod.POST)
	@ApiOperation("分配客户给理财师")
	public Result<PermissionResultDto> doAllotToFp(@ModelAttribute CustomerAllotReqDto dto) throws Exception {
		return customerDistributionService.doAllotToFp(dto);
	}

	@RequestMapping(value = "/recycle", method = RequestMethod.POST)
	@ApiOperation("回收客户")
	public Result<PermissionResultDto> doRecycle(@ModelAttribute CustomerMultiIdReqDto dto) throws Exception {
		return customerDistributionService.doRecycle(dto);
	}

	@RequestMapping(value = "/allot_to_cs", method = RequestMethod.POST)
	@ApiOperation("分配回访")
	public Result<Boolean> doAllotToCs(@ModelAttribute CustomerAllotToCsReqDto dto) throws Exception {
		return customerDistributionService.doAllotToCs(dto);
	}
}
