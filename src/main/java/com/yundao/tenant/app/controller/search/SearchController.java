
package com.yundao.tenant.app.controller.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.enums.search.SearchTypeEnum;
import com.yundao.tenant.app.service.customer.CustomerOpenSeaService;
import com.yundao.tenant.app.service.customer.CustomerService;
import com.yundao.tenant.app.service.product.ProductNoticeService;
import com.yundao.tenant.app.service.product.ProductService;
import com.yundao.tenant.app.service.sale.declaration.DeclarationService;
import com.yundao.tenant.app.service.sale.leads.LeadsService;
import com.yundao.tenant.app.service.sale.reservation.ReservationService;
import com.yundao.tenant.app.view.SearchView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月1日 下午3:59:26
 * 
 * @author wucq
 * @version
 */
@Controller
@Api("搜索相关")
@ResponseBody
public class SearchController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CustomerOpenSeaService customerOpenSeaService;
	@Autowired
	private DeclarationService declarationService;
	@Autowired
	private ProductNoticeService productNoticeService;
	@Autowired
	private LeadsService leadsService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ApiOperation(value = "关键字搜索", notes = "关键字搜索")
	public Result<SearchView> search(@RequestParam(required = false) String keyword, @RequestParam String searchType)
			throws BaseException {
		if (StringUtils.isBlank(searchType)) {
			throw new BaseException(CodeConstant.CODE_1300015);
		}
		if (SearchTypeEnum.PRODUCT.getValue().equals(searchType)) {
			return productService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.CUSTOMER.getValue().equals(searchType)) {
			return customerService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.RESERVATION.getValue().equals(searchType)) {
			return reservationService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.CUSTOMER_OPENSEA.getValue().equals(searchType)) {
			return customerOpenSeaService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.DECLARATION.getValue().equals(searchType)) {
			return declarationService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.PRODUCT_NOTICE_MANAGER.getValue().equals(searchType)) {
			return productNoticeService.searchByKeyword(keyword);
		}
		if (SearchTypeEnum.PRODUCT_MANAGER.getValue().equals(searchType)) {
			return productService.searchProductManagerByKeyword(keyword);
		}
		if (SearchTypeEnum.SALE_LEADS.getValue().equals(searchType)) {
			return productService.searchProductManagerByKeyword(keyword);
		}
		return null;
	}
}
