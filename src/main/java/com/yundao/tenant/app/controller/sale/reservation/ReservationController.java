package com.yundao.tenant.app.controller.sale.reservation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
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
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sale.reservation.ReservationAddReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationCancelReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationListQueryReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSearchListReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSelectListQueryDto;
import com.yundao.tenant.app.service.sale.reservation.ReservationService;
import com.yundao.tenant.app.view.sale.reservation.DeclarationReservationSelectedView;
import com.yundao.tenant.app.view.sale.reservation.ReservationDetailView;
import com.yundao.tenant.app.view.sale.reservation.ReservationListColumnsView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by lan on 2017/8/30.
 */
@Controller
@RequestMapping("/reservation")
@Api("预约")
@ResponseBody
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@ApiOperation(value = "新增预约")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<Long> add(@ModelAttribute ReservationAddReqDto dto) throws BaseException {
		return reservationService.add(dto);
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ApiOperation(value = "取消预约")
	public Result<Long> cancel(@ModelAttribute ReservationCancelReqDto dto) throws BaseException {
		return reservationService.cancel(dto);
	}

	@RequestMapping(value = "/get_columns", method = RequestMethod.POST)
	@ApiOperation(value = "预约列表筛选类别")
	public Result<ReservationListColumnsView> getColumns() throws BaseException {
		return reservationService.getColumns();
	}

	@RequestMapping(value = "/again_commit", method = RequestMethod.POST)
	@ApiOperation("重新提交")
	public Result<Long> againCommit(@RequestParam Long appointId) throws BaseException {
		return reservationService.againCommit(appointId);
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ApiOperation("预约详情")
	public Result<ReservationDetailView> detail(Long appointId) throws BaseException {
		return reservationService.detail(appointId);
	}

	@RequestMapping(value = "/get_my_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取我的预约列表数据")
	public Result<ItemListDTO> getMyPage(@ModelAttribute ReservationListQueryReqDto reqDto) throws BaseException {

		return reservationService.getMyPage(reqDto);
	}

	@RequestMapping(value = "/get_newest_by_product_id", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品ID获取最新预约")
	public Result<DeclarationReservationSelectedView> getNewest(@RequestParam String productId) throws BaseException {
		return reservationService.getNewest(NumberUtils.toLong(productId));
	}

	@RequestMapping(value = "/gets_by_product_id", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品ID获取最新预约")
	public Result<ItemListDTO> getsByProductId(@RequestParam String productId) throws BaseException {
		return reservationService.getsByProductId(NumberUtils.toLong(productId));
	}

	@RequestMapping(value = "/get_my_page_search", method = RequestMethod.POST)
	@ApiOperation(value = "预约搜索")
	public Result<ItemListDTO> getSearchPage(@ModelAttribute ReservationSearchListReqDto reqDto) throws BaseException {
		return reservationService.getMySearchPage(reqDto);
	}

	@RequestMapping(value = "/get_my_page_select", method = RequestMethod.POST)
	@ApiOperation(value = "选择我的预约（可报单）列表")
	public Result<ItemListDTO> getSelectCanOrderPage(@ModelAttribute ReservationSelectListQueryDto reqDto)
			throws BaseException {
		return reservationService.getSelectCanOrderPage(reqDto);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改预约资料")
	public Result<Boolean> update(HttpServletRequest request) throws BaseException {
		Map<String, String> parm = RequestUtils.getParameterMap(request);
		return reservationService.update(parm);
	}

}
