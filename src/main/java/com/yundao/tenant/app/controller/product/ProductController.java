
package com.yundao.tenant.app.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.product.ProductBaseQueryReqDto;
import com.yundao.tenant.app.dto.product.ProductManagerReqDto;
import com.yundao.tenant.app.dto.product.UpdateProductStatusReqDto;
import com.yundao.tenant.app.service.product.ProductService;
import com.yundao.tenant.app.view.product.ProductDetailView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月1日 下午3:59:26
 *
 * @author wucq
 */
@Controller
@ResponseBody
@RequestMapping("/product")
@Api("产品相关")
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
	 * 分页产品产品管理列表
	 *
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/get_List", method = RequestMethod.POST)
	@ApiOperation(value = "分页产品产品列表", notes = "分页查询产品列表")
	public Result<ItemListDTO> getList(@ModelAttribute ProductBaseQueryReqDto reqDto) throws BaseException {
		return productService.getList(reqDto);
	}

	/**
	 * 选择产品列表 getSelectedList:
	 * 
	 * @author: wucq
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	@RequestMapping(value = "/get_selected_List", method = RequestMethod.POST)
	@ApiOperation(value = "选择产品列表分页查询", notes = "选择产品列表中搜索")
	public Result<ItemListDTO> getSelectedList(@ModelAttribute ProductBaseQueryReqDto reqDto) throws BaseException {
		return productService.getSelectedList(reqDto);
	}

	@RequestMapping(value = "/get_selected_list/search", method = RequestMethod.POST)
	@ApiOperation(value = "选择产品列表中搜索", notes = "选择产品列表中搜索")
	public Result<ItemListDTO> search(@ModelAttribute ProductBaseQueryReqDto reqDto) throws BaseException {
		reqDto.setName(reqDto.getKeyword());
		return productService.getSelectedList(reqDto);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ApiOperation(value = "产品搜索", notes = "产品搜索")
	public Result<ItemListDTO> search(String keyword, Integer page, Integer pageSize) throws BaseException {
		return productService.search(keyword, page, pageSize);
	}

	@RequestMapping(value = "/get_detail_by_id", method = RequestMethod.POST)
	@ApiOperation(value = "产品详情", notes = "产品详情")
	public Result<ProductDetailView> getDetailById(@RequestParam Long productId) throws BaseException {
		return productService.getDetailById(productId);
	}
	@RequestMapping(value = "/v2/get_detail_by_id", method = RequestMethod.POST)
	@ApiOperation(value = "产品详情", notes = "产品详情")
	public Result<ProductDetailView> getDetailByIdV2(@RequestParam Long productId) throws BaseException {
		return productService.getDetailByIdV2(productId);
	}
	@RequestMapping(value = "/share/get_detail_by_id", method = RequestMethod.POST)
	@ApiOperation(value = "分享预览中的产品详情", notes = "产品详情")
	public Result<ProductDetailView> getDetailByIdForShare(@RequestParam Long productId) throws BaseException {
		return productService.getDetailByIdForShare(productId);
	}
	/**
	 * 分页产品产品管理列表
	 *
	 * @param reqDto、
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/get_manager_List", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询产品管理列表", notes = "分页查询产品管理列表")
	public Result<ItemListDTO> getManagerList(@ModelAttribute ProductManagerReqDto reqDto) throws BaseException {
		return productService.getManagerList(reqDto);
	}

	@RequestMapping(value = "/get_manager_List/search", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询产品管理列表", notes = "分页查询产品管理列表")
	public Result<ItemListDTO> getManagerSearch(@ModelAttribute ProductManagerReqDto reqDto) throws BaseException {
		return productService.getManagerList(reqDto);
	}

	@ApiOperation(value = "修改产品状态", notes = "修改产品状态")
	@RequestMapping(value = "/update_status", method = RequestMethod.POST)
	public Result<Integer> updateProductStatus(@ModelAttribute UpdateProductStatusReqDto dto) throws Exception {
		return productService.updateProductStatus(dto);
	}
}
