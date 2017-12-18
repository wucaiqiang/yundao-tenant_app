
package com.yundao.tenant.app.controller.customer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.utils.RequestUtils;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.CustomerAddReqDto;
import com.yundao.tenant.app.dto.customer.CustomerListQueryReqDto;
import com.yundao.tenant.app.dto.customer.CustomerSaleQueryReqDto;
import com.yundao.tenant.app.dto.customer.bank.CustomerBankReqDto;
import com.yundao.tenant.app.service.customer.CustomerService;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;
import com.yundao.tenant.app.view.customer.CustomerImportBookView;
import com.yundao.tenant.app.view.customer.CustomerListColumnView;
import com.yundao.tenant.app.view.customer.CustomerQueryConditionView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月16日 下午3:34:10
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/customer")
@Api("客户管理")
@ResponseBody
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加客户")
	public Result<Map<String, Object>> add(@ModelAttribute CustomerAddReqDto reqDto) throws Exception {
		return customerService.add(reqDto);
	}

	@RequestMapping(value = "/add_from_book", method = RequestMethod.POST)
	@ApiOperation(value = "通讯录导入", notes = "通讯录导入")
	public Result<CustomerImportBookView> addFromBook(@RequestParam String customerList) throws Exception {
		return customerService.addFromBook(customerList);
	}

	/**
	 * 
	 * getColumns:
	 * 
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	@RequestMapping(value = "/get_columns", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表栏目")
	public Result<CustomerListColumnView> getColumns() throws BaseException {
		return customerService.getColumns();
	}

	/**
	 * 
	 * getColumns:
	 * 
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	@RequestMapping(value = "/v2/get_columns", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表栏目")
	public Result<CustomerListColumnView> getColumnsV2() throws BaseException {
		return customerService.getColumnsV2();
	}

	@RequestMapping(value = "/get_query_condition", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表栏目")
	public Result<CustomerQueryConditionView> getQueryCondition() throws BaseException {
		return customerService.getQueryCondition();
	}

	@RequestMapping(value = "/v2/get_query_condition", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表栏目")
	public Result<CustomerQueryConditionView> getQueryConditionV2() throws BaseException {
		return customerService.getQueryConditionV2();
	}

	@RequestMapping(value = "/get_my_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> getMyPage(@ModelAttribute CustomerListQueryReqDto reqDto) throws BaseException {
		return customerService.getMyPage(reqDto);
	}

	@RequestMapping(value = "/v2/get_my_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> getMyPageV2(@ModelAttribute CustomerListQueryReqDto reqDto) throws BaseException {
		return customerService.getMyPage(reqDto);
	}

	@RequestMapping(value = "/v3/get_my_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> getMyPageV3(@ModelAttribute CustomerListQueryReqDto reqDto) throws BaseException {
		return customerService.getMyPageV3(reqDto);
	}

	@RequestMapping(value = "/get_sale_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户交易信息")
	public Result<ItemListDTO> getSalePage(@ModelAttribute CustomerSaleQueryReqDto reqDto) throws BaseException {
		return customerService.getSalePage(reqDto);
	}
	@RequestMapping(value = "/opensea/get_sale_page", method = RequestMethod.POST)
	@ApiOperation(value = "公海客户详情获取客户交易信息")
	public Result<ItemListDTO> getSalePageForOpenSea(@ModelAttribute CustomerSaleQueryReqDto reqDto) throws BaseException {
		return customerService.getSalePage(reqDto);
	}
	@RequestMapping(value = "/get_my_page/search", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> getMyPageSearch(@ModelAttribute BasePageDto reqDto,
			@RequestParam(required = false) String keyword) throws BaseException {
		return customerService.getMyPageSearch(reqDto, keyword);
	}

	@RequestMapping(value = "/get_search_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> getSearchPage(@ModelAttribute BasePageDto reqDto) throws BaseException {
		return customerService.getSearchPage(reqDto, null);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的客户列表数据")
	public Result<ItemListDTO> search(@ModelAttribute BasePageDto reqDto,
			@RequestParam(required = false) String keyword) throws BaseException {
		return customerService.getSearchPage(reqDto, keyword);
	}

	@RequestMapping(value = "/get_detail_header", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户详情头部信息")
	public Result<CustomerDetailHeaderView> getDetailHeader(@RequestParam Long customerId,
			@RequestParam(required = false) Integer from) throws BaseException {
		return customerService.getDetailHeader(customerId, from);
	}

	@RequestMapping(value = "/v2/get_detail_header", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户详情头部信息")
	public Result<CustomerDetailHeaderView> getDetailHeaderV2(@RequestParam Long customerId) throws BaseException {
		return customerService.getDetailHeaderV2(customerId);
	}

	@RequestMapping(value = "/get_detail", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户详情信息")
	public Result<CustomerDetailView> getDetail(@RequestParam Long customerId) throws BaseException {
		return customerService.getDetail(customerId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户资料")
	public Result<Boolean> update(HttpServletRequest request) throws BaseException {
		Map<String, String> parm = RequestUtils.getParameterMap(request);
		return customerService.update(parm);
	}

	@RequestMapping(value = "/update_bank", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户银行卡信息")
	public Result<Map<String, Object>> updateBank(@ModelAttribute CustomerBankReqDto customerBankReqDto)
			throws BaseException {
		return customerService.updateBank(customerBankReqDto);
	}

	@RequestMapping(value = "/validate_mobile", method = RequestMethod.POST)
	@ApiOperation(value = "验证客户手机号是否可用", notes = "验证客户手机号是否可用")
	public Result<Boolean> validateMobile(@RequestParam String mobile, @RequestParam(required = false) Long id)
			throws Exception {
		return customerService.validateMobile(mobile, id);
	}

}
