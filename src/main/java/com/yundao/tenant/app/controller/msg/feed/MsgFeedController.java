package com.yundao.tenant.app.controller.msg.feed;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.service.msg.MsgFeedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户动态
 *
 * @author gjl
 * @create 2017-08-16 PM3:35
 **/
@Controller
@RequestMapping("/msg/feed/")
@ResponseBody
@Api("客户动态")
public class MsgFeedController {
	@Autowired
	private MsgFeedService msgFeedService;

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询客户动态")
	public Result<ItemListDTO> getPage(@RequestParam Long customerId, @ModelAttribute BasePageDto pageDto)
			throws BaseException {
		return msgFeedService.getPage(customerId, null, pageDto);
	}

	@RequestMapping(value = "/v2/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询客户动态")
	public Result<ItemListDTO> getPageV2(@RequestParam Long customerId,
			@RequestParam(required = false) String filterType, @ModelAttribute BasePageDto pageDto)
			throws BaseException {
		return msgFeedService.getPage(customerId, filterType, pageDto);

	}
}
