
package com.yundao.tenant.app.controller.customer.tag;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.tag.TagReqDto;
import com.yundao.tenant.app.service.customer.tag.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月18日 下午3:13:16
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/tag")
@Api("客户标签管理")
@ResponseBody
public class TagController {
	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/set_tag", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户标签")
	public Result<Boolean> setTag(@RequestParam Long customerId, @RequestParam String tags) throws Exception {
		return tagService.setTag(customerId, tags);
	}

	@RequestMapping(value = "/get_top", method = RequestMethod.POST)
	@ApiOperation(value = "查询排名的标签信息")
	public Result<Map<String, Object>> getTop() throws Exception {
		return tagService.getTop();
	}

	@RequestMapping(value = "/get_all", method = RequestMethod.POST)
	@ApiOperation(value = "查询排名的标签信息")
	public Result<Map<String, Object>> getAll() throws Exception {
		return tagService.getAll();
	}

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "查询排名的标签信息")
	public Result<ItemListDTO> getPage(@ModelAttribute TagReqDto reqDto) throws Exception {
		return tagService.getPage(reqDto);
	}
}
