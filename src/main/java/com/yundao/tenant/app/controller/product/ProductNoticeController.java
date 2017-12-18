
package com.yundao.tenant.app.controller.product;

import org.springframework.beans.BeanUtils;
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
import com.yundao.tenant.app.dto.product.ProductNoticeAddReqDto;
import com.yundao.tenant.app.dto.product.ProductNoticeQueryReqDto;
import com.yundao.tenant.app.service.product.ProductNoticeService;
import com.yundao.tenant.app.view.product.ProductNoticeView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月8日 上午10:20:55
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/product_notice")
@Api("产品公告相关")
@ResponseBody
public class ProductNoticeController {
	@Autowired
	private ProductNoticeService productNoticeService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增产品公告", notes = "创建产品公告")
	public Result<Integer> add(@ModelAttribute ProductNoticeAddReqDto dto) throws Exception {
		return productNoticeService.add(dto);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ApiOperation(value = "获取产品公告详细信息")
	public Result<ProductNoticeView> get(@RequestParam Long noticeId) throws Exception {
		return productNoticeService.get(noticeId);
	}

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询产品公告管理列表")
	public Result<ItemListDTO> getPage(@ModelAttribute ProductNoticeQueryReqDto pageReqDto) throws Exception {
		return productNoticeService.getPage(pageReqDto);
	}

	@RequestMapping(value = "/get_page/search", method = RequestMethod.POST)
	@ApiOperation(value = "选择产品列表中搜索", notes = "选择产品列表中搜索")
	public Result<ItemListDTO> getSelectedList(@ModelAttribute BasePageDto reqDto,
			@RequestParam(required = false) String keyword) throws BaseException {
		ProductNoticeQueryReqDto dto = new ProductNoticeQueryReqDto();
		BeanUtils.copyProperties(reqDto, dto);
		dto.setTitle(keyword);
		return productNoticeService.getPage(dto);
	}

	@RequestMapping(value = "/apply_or_resubmit", method = RequestMethod.POST)
	@ApiOperation(value = "申请发布或再次申请", notes = "申请发布或再次申请")
	public Result<Integer> applyOrResubmit(@RequestParam Long noticeId) throws Exception {
		return productNoticeService.doApplyOrResubmit(noticeId);
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ApiOperation(value = "取消申请", notes = "取消申请")
	public Result<Integer> cancelFlow(@RequestParam Long noticeId, @RequestParam String reason) throws BaseException {
		return productNoticeService.doCancelFlow(noticeId, reason);
	}
}
