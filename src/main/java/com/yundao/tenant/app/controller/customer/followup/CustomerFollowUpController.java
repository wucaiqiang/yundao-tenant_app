
package com.yundao.tenant.app.controller.customer.followup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.validator.spring.BindingResultHandler;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpPageDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpPageResDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpReqDto;
import com.yundao.tenant.app.service.customer.followup.CustomerFollowUpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月24日 下午2:04:16
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/followup")
@Api("客户跟进")
@ResponseBody
public class CustomerFollowUpController {
	@Autowired
	private CustomerFollowUpService customerFollowUpService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加客户跟进")
	public Result<Map<String, Object>> add(@ModelAttribute CustomerFollowUpReqDto reqDto) throws BaseException {
		Result<Long> result = customerFollowUpService.add(reqDto);
		if (result != null && result.getResult() != null) {
			Long followupId = result.getResult();
			Map<String, Object> map = new HashMap<>();
			map.put("followupId", String.valueOf(followupId));

			return Result.newSuccessResult(map);
		}
		return Result.newFailureResult(result.getCode(), result.getMessage(), null);
	}

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询客户跟进 - 获取所有客户数据")
	public Result<ItemListDTO> getPage(@ModelAttribute CustomerFollowUpPageDto dto) throws Exception {
		return customerFollowUpService.getPage(dto);
	}
}
