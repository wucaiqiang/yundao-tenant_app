
package com.yundao.tenant.app.controller.sale.declaration;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddAndResubmitReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddDirectReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddInReservationReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddResDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationApplyRefundReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationBankReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationPageReqDto;
import com.yundao.tenant.app.service.sale.declaration.DeclarationService;
import com.yundao.tenant.app.view.sale.declaration.ComplianceView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationCustomerView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationDetailHeaderView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationListColumnView;
import com.yundao.tenant.app.view.sale.declaration.SubscribeView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月2日 下午1:40:53
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/declaration")
@ResponseBody
@Api("报单")
public class DeclarationController {
	@Autowired
	private DeclarationService declarationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增报单")
	public Result<DeclarationAddResDto> add(@ModelAttribute DeclarationAddAndResubmitReqDto reqDto) throws Exception {
		return declarationService.add(reqDto);
	}

	@RequestMapping(value = "/add_in_reservation", method = RequestMethod.POST)
	@ApiOperation(value = "预约转报单")
	public Result<DeclarationAddResDto> addInReservation(@ModelAttribute DeclarationAddInReservationReqDto reqDto)
			throws Exception {
		return declarationService.addInReservation(reqDto);
	}

	@RequestMapping(value = "/direct/add", method = RequestMethod.POST)
	@ApiOperation(value = "直接报单")
	public Result<DeclarationAddResDto> addDirect(@ModelAttribute DeclarationAddDirectReqDto reqDto) throws Exception {
		return declarationService.addDirect(reqDto);
	}

	@RequestMapping(value = "/patch/add", method = RequestMethod.POST)
	@ApiOperation(value = "补充录入报单")
	public Result<DeclarationAddResDto> addPatch(@ModelAttribute DeclarationAddDirectReqDto reqDto) throws Exception {
		return declarationService.addPatch(reqDto);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ApiOperation(value = "提交报单")
	public Result<Long> submit(@RequestParam Long orderId) throws Exception {
		return declarationService.submit(orderId);
	}

	@RequestMapping(value = "/resubmit", method = RequestMethod.POST)
	@ApiOperation(value = "重新提交报单")
	public Result<Long> resubmit(@ModelAttribute DeclarationAddAndResubmitReqDto reqDto) throws Exception {
		return declarationService.resubmit(reqDto);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改报单资料")
	public Result<Boolean> update(HttpServletRequest request) throws Exception {
		Map<String, String> parm = RequestUtils.getParameterMap(request);
		return declarationService.update(parm);
	}

	@RequestMapping(value = "/update_bank", method = RequestMethod.POST)
	@ApiOperation(value = "修改银行卡信息")
	public Result<Map<String, Object>> updateBank(@ModelAttribute DeclarationBankReqDto bankReqDto)
			throws BaseException {
		return declarationService.updateBank(bankReqDto);
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ApiOperation(value = "取消")
	public Result<Long> cancel(@RequestParam Long orderId, @RequestParam String reason) throws Exception {
		return declarationService.doCancel(orderId, reason);
	}

	@RequestMapping(value = "/get_columns", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的报单列表栏目")
	public Result<DeclarationListColumnView> getColumns() throws BaseException {
		return declarationService.getColumns();
	}

	@RequestMapping(value = "/get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询我的报单")
	public Result<ItemListDTO> getPage(@ModelAttribute DeclarationPageReqDto declarationPageReqDto)
			throws BaseException {
		return declarationService.getPage(declarationPageReqDto);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询我的报单")
	public Result<ItemListDTO> search(@ModelAttribute BasePageDto reqDto,
			@RequestParam(required = false) String keyword) throws BaseException {
		return declarationService.getSearchPage(reqDto, keyword);
	}

	@RequestMapping(value = "/get_detail_header", method = RequestMethod.POST)
	@ApiOperation(value = "我的报单头部信息")
	public Result<DeclarationDetailHeaderView> getDetailHeader(@RequestParam Long orderId) throws BaseException {
		return declarationService.getDetailHeader(orderId);
	}

	@RequestMapping(value = "/get_customer_detail", method = RequestMethod.POST)
	@ApiOperation(value = "报单详情中的客户信息")
	public Result<DeclarationCustomerView> getCustomerDetail(@RequestParam Long orderId) throws BaseException {
		return declarationService.getCustomerDetail(orderId);
	}

	@RequestMapping(value = "/get_bank", method = RequestMethod.POST)
	@ApiOperation(value = "报单详情中的客户信息")
	public Result<Map<String, Object>> getBank(@RequestParam Long orderId) throws BaseException {
		return declarationService.getBank(orderId);
	}

	@RequestMapping(value = "/get_subscribe", method = RequestMethod.POST)
	@ApiOperation(value = "报单详情中的认购资料")
	public Result<SubscribeView> getSubscribe(@RequestParam Long orderId) throws BaseException {
		return declarationService.getSubscribe(orderId);
	}

	@RequestMapping(value = "/get_compliance", method = RequestMethod.POST)
	@ApiOperation(value = "报单详情中的合规文件")
	public Result<ComplianceView> getCompliance(@RequestParam Long orderId) throws BaseException {
		return declarationService.getCompliance(orderId);
	}

	@RequestMapping(value = "/apply_refund", method = RequestMethod.POST)
	@ApiOperation(value = "申请退款")
	public Result<Long> applyRefund(@ModelAttribute DeclarationApplyRefundReqDto dto) throws Exception {
		return declarationService.applyRefund(dto);
	}

	@RequestMapping(value = "/resubmit_refund", method = RequestMethod.POST)
	@ApiOperation(value = "申请退款")
	public Result<Long> resubmitRefund(@RequestParam Long id) throws Exception {
		return declarationService.resubmitRefund(id);
	}
	@RequestMapping(value = "/refund_cancel", method = RequestMethod.POST)
	@ApiOperation(value = "申请退款")
	public Result<Long> refundCancel(@ModelAttribute DeclarationApplyRefundReqDto dto) throws Exception {
		return declarationService.refundCancel(dto);
	}

}
