
package com.yundao.tenant.app.controller.customer;

import java.util.HashMap;
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
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.opensea.CustomerOpenSeaPageReqDto;
import com.yundao.tenant.app.service.customer.CustomerOpenSeaService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月16日 下午3:34:10
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/customer/opensea")
@Api("客户公海")
@ResponseBody
public class CustomerOpenSeaController {
	

    @Autowired
    CustomerOpenSeaService customerOpenSeaService;
    


	@RequestMapping(value = "/get_opensea_page", method = RequestMethod.POST)
	@ApiOperation(value = "客户公海搜索列表")
	public Result<ItemListDTO> getPage(@ModelAttribute CustomerOpenSeaPageReqDto dto) throws BaseException {
		return customerOpenSeaService.getItemListDTO(dto);
	}
	
	@RequestMapping(value = "/get_opensea_keyword_page", method = RequestMethod.POST)
	@ApiOperation(value = "客户公海搜索列表")
	public Result<ItemListDTO> getKeywordPage(@ModelAttribute CustomerOpenSeaPageReqDto reqDto) throws BaseException {
		return customerOpenSeaService.getItemListDTO(reqDto);
	}
    
    @ApiOperation("领取公海客户")
    @RequestMapping(value = "/receive_customer", method = RequestMethod.POST)
    public Result<Integer> receiveCustomer(@RequestParam Long customerId) throws BaseException {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("customerId", customerId);
    	return HttpUtils.post(TenantUrl.RECEIVE_CUSTOMER_OPENSEA, map, new BaseTypeReference<Result<Integer>>() {
                });
    }
    
    @RequestMapping(value = "/get_detail_header", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户详情头部信息")
	public Result<CustomerDetailHeaderView> getDetailHeader(@RequestParam Long customerId) throws BaseException {
		return customerOpenSeaService.getDetailHeader(customerId);
	}
    @RequestMapping(value = "/get_detail", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户详情信息")
	public Result<CustomerDetailView> getDetail(@RequestParam Long customerId) throws BaseException {
		return customerOpenSeaService.getDetail(customerId);
	}


}
