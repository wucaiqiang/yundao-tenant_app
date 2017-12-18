
package com.yundao.tenant.app.controller.sale.leads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotToFpReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsPageReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsQueryReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsSearchReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsUpdateStatusReqDto;
import com.yundao.tenant.app.service.sale.leads.LeadsService;
import com.yundao.tenant.app.view.sale.leads.LeadsQueryConditionView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月11日 下午3:13:16
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/leads")
@ResponseBody
@Api("销售线索")
public class LeadsController {
	@Autowired
	private LeadsService leadsService;

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation("客户详情中获取销售线索列表")
	public Result<ItemListDTO> getPageForCustomer(@ModelAttribute LeadsQueryReqDto dto) throws Exception {
		return leadsService.getPageForCustomer(dto);
	}

	@RequestMapping(value = "/opensea/get_page", method = RequestMethod.POST)
	@ApiOperation("公海客户详情中获取销售线索列表")
	public Result<ItemListDTO> getPageForOpenSeaCustomer(@ModelAttribute LeadsQueryReqDto dto) throws Exception {
		return leadsService.getPageForOpenSeaCustomer(dto);
	}
	@RequestMapping(value = "/v/get_page", method = RequestMethod.POST)
	@ApiOperation("获取销售线索列表")
	public Result<ItemListDTO> getPage(@ModelAttribute LeadsPageReqDto dto) throws Exception {
		return leadsService.getPage(dto);
	}
	@RequestMapping(value = "/v/get_page/search", method = RequestMethod.POST)
	@ApiOperation("获取销售线索列表")
	public Result<ItemListDTO> search(@ModelAttribute LeadsSearchReqDto dto) throws Exception {
		return leadsService.search(dto);
	}
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ApiOperation("处理销售线索")
	public Result<Long> doProcess(@ModelAttribute LeadsUpdateStatusReqDto dto) throws Exception {
		return leadsService.doProcess(dto);
	}

	@RequestMapping(value = "/get_query_condition", method = RequestMethod.POST)
	@ApiOperation(value = "获取销售线索筛选项")
	public Result<LeadsQueryConditionView> getQueryCondition() throws Exception {
		return leadsService.getQueryCondition();
	}

	@RequestMapping(value = "/allot_to_fp", method = RequestMethod.POST)
	@ApiOperation("分配给理财师")
	public Result<Boolean> allotToFp(@ModelAttribute LeadsAllotToFpReqDto dto) throws BaseException {
		return leadsService.allotToFp(dto);
	}

	@RequestMapping(value = "/try_allot", method = RequestMethod.POST)
	@ApiOperation("尝试分配回访")
	public Result<Boolean> tryAllot(@ModelAttribute LeadsAllotReqDto dto) throws BaseException {
		return leadsService.doAllot(dto);
	}

}
