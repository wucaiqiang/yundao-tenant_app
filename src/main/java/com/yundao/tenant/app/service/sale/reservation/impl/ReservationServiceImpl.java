package com.yundao.tenant.app.service.sale.reservation.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.TypeReference;
import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.DateUtils;
import com.yundao.core.utils.ListUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.product.BaseProductSale;
import com.yundao.tenant.app.dto.product.ProductPartDetailDto;
import com.yundao.tenant.app.dto.sale.reservation.BaseReservationDto;
import com.yundao.tenant.app.dto.sale.reservation.MyReservationListResDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationAddDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationAddReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationCancelDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationCancelReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationDetailDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationListQueryDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationListQueryReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSearchListReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSelectListQueryDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.enums.product.MoneyTypeEnum;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.sale.reservation.ReservationService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.MatcherUtil;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.TitleView;
import com.yundao.tenant.app.view.sale.reservation.DeclarationReservationSelectedView;
import com.yundao.tenant.app.view.sale.reservation.ReservationDetailView;
import com.yundao.tenant.app.view.sale.reservation.ReservationListColumnsView;
import com.yundao.tenant.app.view.sale.reservation.Struct4002View;
import com.yundao.tenant.app.view.sale.reservation.Structure4001View;

/**
 * Created by lan on 2017/8/30.
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private PermissionService permissionService;

	@Override
	public Result<Long> add(ReservationAddReqDto dto) throws BaseException {

		// TODO 权限校验

		ReservationAddDto d = new ReservationAddDto();
		d.setCustomerId(dto.getCustomerId());
		d.setProductId(dto.getProductId());
		Integer amount = dto.getAmount();

		Long appointDate = dto.getAppointDate();
		if (appointDate != null) {
			long dateLong = appointDate.longValue();
			Date date = DateUtils.toDate(dateLong);
			d.setEstimatePayDate(date);
		}

		if (amount != null) {
			Double amountDouble = new Double(amount);
			d.setReservationAmount(amountDouble);
		}

		if (StringUtils.isNotBlank(dto.getRemarks())) {
			d.setRemark(dto.getRemarks());
		}

		Result<Long> result = HttpUtils.postFastJson(TenantUrl.RESERVATION_ADD, ArgsUtils.toMap(d),
				new com.alibaba.fastjson.TypeReference<Result<Long>>() {
				});

		return result;
	}

	@Override
	public Result<Long> cancel(ReservationCancelReqDto dto) throws BaseException {
		ReservationCancelDto d = new ReservationCancelDto();

		String id = dto.getAppointId();
		Long idLong = null;
		try {
			idLong = Long.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (idLong != null) {
			d.setId(idLong);
		}

		d.setReason(dto.getReason());
		Result<Long> result = HttpUtils.postFastJson(TenantUrl.RESERVATION_CANCEL, ArgsUtils.toMap(d),
				new TypeReference<Result<Long>>() {
				});

		return result;
	}

	@Override
	public Result<ReservationListColumnsView> getColumns() throws BaseException {

		List<NameIdView> columns = new ArrayList<>();
		NameIdView id = new NameIdView("全部", "");
		columns.add(id);
		id = new NameIdView("待审批", "1");
		columns.add(id);
		id = new NameIdView("已通过", "2");
		columns.add(id);
		id = new NameIdView("已驳回", "3");
		columns.add(id);

		id = new NameIdView("已取消", "4");
		columns.add(id);

		ReservationListColumnsView listColumnsView = new ReservationListColumnsView(columns);
		return Result.newSuccessResult(listColumnsView);
	}

	@Override
	public Result<Long> againCommit(Long appointId) throws BaseException {

		Result<Long> result = HttpUtils.postFastJson(TenantUrl.RESERVATION_AGAIN_COMMIT, ArgsUtils.toIdMap(appointId),
				new TypeReference<Result<Long>>() {
				});

		return result;
	}

	@Override
	public Result<ReservationDetailView> detail(Long appointId) throws BaseException {

		Result<ReservationDetailDto> result = HttpUtils.getFastJson(TenantUrl.RESERVATION_SINGLE_DETAIL,
				ArgsUtils.toIdMap(appointId), new TypeReference<Result<ReservationDetailDto>>() {
				});

		// 权限校验
		Result<Boolean> read = permissionService.checkRead(DataObjectEnum.RESERVATION.getCode(),
				result.getResult().getUserId());// 鉴权

		if (read == null || read.getResult() == null || !read.getResult()) {
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}

		// 查询结果
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		ReservationDetailView detailView = new ReservationDetailView();
		ReservationDetailDto b = result.getResult();

		if (b == null) {
			return Result.newSuccessResult(detailView);
		}

		// 查产品详情的信息
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", b.getProductId());
		// 获取用户在各租户中详情信息
		Result<ProductPartDetailDto> productResult = HttpUtils.get(TenantUrl.GET_DETAIL_BY_ID, methodParams,
				new BaseTypeReference<Result<ProductPartDetailDto>>() {
				});

		// 起投金额
		Double buyStartPoint = null;
		// 递增金额
		Double incrementalAmount = null;
		// 单位
		String moneyUnit = "万";

		if (productResult.getResult() != null) {
			ProductPartDetailDto product=productResult.getResult();
			buyStartPoint = product.getBuyStartPoint();
			incrementalAmount = product.getIncrementalAmount();

			BaseProductSale productSale = product.getProductSale();
			MoneyTypeEnum moneyEnum = MoneyTypeEnum.getMoneyTypeEnum(productSale.getCurrencyCode());
			if (moneyEnum != null) {
				moneyUnit = moneyEnum.getUnit();
			}
			detailView.setAddOrderMode(product.getDeclarationModel()==null?0:product.getDeclarationModel());
			detailView.setProductStatus(product.getIssuedStatus());
			detailView.setProductAppointNum(product.getReservationNum());
			detailView.setProductStatusText(product.getIssuedStatusText());
		}

		detailView.setState(b.getStatus());

		detailView.setStateText(b.getStatusText());

		if (b.getDiscard()) {
			detailView.setStateText("已作废");
			detailView.setState(5);
		}

		detailView.setCancelReason(b.getReason());

		detailView.setHasOrder(b.getHasDeclaration() == null ? false : b.getHasDeclaration());

		Date date = b.getEstimatePayDate();
		String giveAmountDateText = "";
		if (date != null) {
			giveAmountDateText = DateUtils.format(date, DateUtils.YYYY_MM_DD);
		}
		detailView.setGiveAmountDateText(giveAmountDateText);

		detailView.setGiveAmountDate(date.getTime());

		detailView.setCustomerName(b.getCustomerName());

		Long customerId = b.getCustomerId();
		if (customerId != null) {
			detailView.setCustomerId(customerId.toString() + "");

			ActionDTO customerActionDTO = new ActionDTO(
					Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, customerId));
			detailView.setCustomerAction(customerActionDTO);
		}

		detailView.setProductName(b.getProductName());

		Long productId = b.getProductId();
		if (productId != null) {
			detailView.setProductId(productId.toString() + "");
		}

		String buyStartPointStr = "";
		if (buyStartPoint != null) {

			if (MatcherUtil.isIntegerForDouble(buyStartPoint)) {
				buyStartPointStr = buyStartPoint.intValue() + "";
			} else {
				buyStartPointStr = buyStartPoint.toString() + "";
			}
		}

		Double reservationAmount = b.getReservationAmount();
		String appointAmount = reservationAmount == null ? "0" : reservationAmount.intValue() + "";
		detailView.setAppointAmount(appointAmount);

		if (incrementalAmount == null || incrementalAmount == 0) {
			detailView.setStartAmountAppointText(buyStartPointStr + moneyUnit + "起投");
		} else {

			String incrementalAmountStr = "";
			if (MatcherUtil.isIntegerForDouble(incrementalAmount)) {
				incrementalAmountStr = incrementalAmount.intValue() + "";
			} else {
				incrementalAmountStr = incrementalAmount.toString();
			}

			detailView.setStartAmountAppointText(buyStartPointStr + moneyUnit + "起投");
		}

		if (b.getProductId() != null && b.getProductId() != 0) {
			ActionDTO productActionDTO = new ActionDTO(Schema.PRODUCT_DETAIL
					+ String.format(Schema.PRODUCT_DETAIL_PARAMS, b.getProductId(), b.getProductName()));
			detailView.setProductAction(productActionDTO);
		}

		detailView.setRemarks(b.getRemark());

		Date reservationDate = b.getReservationDate();
		String reservationDateStr = "";
		if (reservationDate != null) {
			reservationDateStr = DateUtils.format(reservationDate, DateUtils.YYYY_MM_DD_HH_MM);
		}

		detailView.setAppointDateText(reservationDateStr);

		detailView.setAppointNO(b.getNumber());

		return Result.newSuccessResult(detailView);
	}

	@Override
	public Result<ItemListDTO> getMyPage(ReservationListQueryReqDto reqDto) throws BaseException {

		ReservationListQueryDto dto = new ReservationListQueryDto();
		dto.setPageSize(reqDto.getPageSize());
		dto.setPage(reqDto.getPage());

		if (StringUtils.isNotBlank(reqDto.getFilterType())) {
			dto.setStatuss(reqDto.getFilterType());
		} else {
			dto.setStatuss("1,2,3,4");
		}
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}

		Result<PaginationSupport<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GET_MY_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<MyReservationListResDto>>>() {
				});

		if (result == null) {
			return Result.newSuccessResult();
		}
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode());
		}
		if (result.getResult() == null) {
			return Result.newSuccessResult();
		}

		List<MyReservationListResDto> datas = result.getResult().getDatas();
		if (ListUtils.getSize(datas) == 0) {
			return Result.newSuccessResult();
		}

		ItemListDTO itemListDTO = buildListItemView(result.getResult(), datas);

		return Result.newSuccessResult(itemListDTO);
	}

	@Override
	public Result<PaginationSupport<MyReservationListResDto>> getsByCustomerId(ReservationSelectListQueryDto reqDto)
			throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(reqDto);

		Result<PaginationSupport<MyReservationListResDto>> result = HttpUtils.get(
				TenantUrl.CUSTOMER_SALE_GET_RESERVATION, methodParams,
				new BaseTypeReference<Result<PaginationSupport<MyReservationListResDto>>>() {
				});

		return result;

	}

	@Override
	public Result<ItemListDTO> getsByProductId(Long productId) throws BaseException {
		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("productId", productId);
		Result<List<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GETS_BY_PRODUCT_ID,
				methodParams, new BaseTypeReference<Result<List<MyReservationListResDto>>>() {
				});
		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();
		viewItems.add(new ViewItem(4002, new TitleView("您可从已确认且未报单的预约中选择一个进行报单：")));
		if (result.getResult() != null && !result.getResult().isEmpty()) {
			for (MyReservationListResDto d : result.getResult()) {
				Structure4001View view = new Structure4001View();
				view.setAppointId(d.getId() + "");

				view.setCustomerName(d.getCustomerName());

				view.setProductName(d.getProductName());

				Double reservationAmount = d.getReservationAmount();
				if (reservationAmount == null) {
					view.setAppintAmountText("");
					view.setLeftValue("");
				} else {

					if (MatcherUtil.isIntegerForDouble(reservationAmount)) {
						view.setAppintAmountText(reservationAmount.intValue() + "万");
						view.setLeftValue(reservationAmount.intValue() + "万");
					} else {
						view.setAppintAmountText(reservationAmount.toString() + "万");
						view.setLeftValue(reservationAmount.toString() + "万");
					}
				}
				view.setStateText(d.getStatusText());
				view.setLeftName("预约金额");

				Date payDate = d.getEstimatePayDate();
				String payDateStr = DateUtils.format(payDate, DateUtils.YYYY_MM_DD);
				view.setRightValue(payDateStr);

				view.setRightName("预计打款日期");

				Date reservationDate = d.getReservationDate();
				String reservationDateStr = DateUtils.format(reservationDate, DateUtils.YYYY_MM_DD_HH_MM);
				view.setAppointmentDateText("预约时间 " + reservationDateStr);

				String jumpUrl = Schema.RESERVATION_DETAIL + String.format(Schema.RESERVATION_DETAIL_PARAMS, d.getId());

				viewItems.add(new ViewItem(4001, jumpUrl, view));
				viewItems.add(new ViewItem(2001, new DividingLineView()));
			}
		}
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<DeclarationReservationSelectedView> getNewest(Long productId) throws BaseException {
		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("productId", productId);
		Result<List<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GETS_BY_PRODUCT_ID,
				methodParams, new BaseTypeReference<Result<List<MyReservationListResDto>>>() {
				});

		DeclarationReservationSelectedView view = new DeclarationReservationSelectedView();

		if (result.getResult() != null && !result.getResult().isEmpty()) {
			List<MyReservationListResDto> resDtos = result.getResult();
			MyReservationListResDto dto = resDtos.get(0);

			if (dto != null) {
				view.setHasNext(resDtos.size() > 1);
				view.setAppointId(String.valueOf(dto.getId()));
				view.setCustomerName(dto.getCustomerName());
				view.setAppointTitle("["+dto.getCustomerName()+"]"+dto.getProductName());
				view.setAppointAmount(NumberUtil.trimDoubleZero(String.valueOf(dto.getReservationAmount())) + "万");
			}
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<ItemListDTO> getMySearchPage(ReservationSearchListReqDto reqDto) throws BaseException {

		String keyword = reqDto.getKeyword();

		if (StringUtils.isBlank(keyword)) {
			return Result.newResult(true);
		}

		ReservationListQueryDto dto = new ReservationListQueryDto();
		dto.setPageSize(reqDto.getPageSize());
		dto.setPage(reqDto.getPage());
		dto.setStatuss("1,2,3,4");

		dto.setProductOrCustomerName(keyword);

		Map<String, Object> methodParams = ArgsUtils.toMap(dto);

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}

		Result<PaginationSupport<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GET_MY_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<MyReservationListResDto>>>() {
				});

		if (result == null) {
			return Result.newSuccessResult();
		}
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode());
		}
		if (result.getResult() == null) {
			return Result.newSuccessResult();
		}

		List<MyReservationListResDto> datas = result.getResult().getDatas();
		if (ListUtils.getSize(datas) == 0) {
			return Result.newSuccessResult();
		}

		ItemListDTO itemListDTO = buildListItemView(result.getResult(), datas);

		return Result.newSuccessResult(itemListDTO);
	}

	@Override
	public Result<ItemListDTO> getSelectCanOrderPage(ReservationSelectListQueryDto reqDto) throws BaseException {

		ReservationListQueryDto dto = new ReservationListQueryDto();
		dto.setPageSize(reqDto.getPageSize());
		dto.setPage(reqDto.getPage());
		dto.setStatuss("2");
		dto.setIsRemoveDeclaration(1);

		Map<String, Object> methodParams = ArgsUtils.toMap(dto);

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}

		Result<PaginationSupport<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GET_MY_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<MyReservationListResDto>>>() {
				});

		if (result == null) {
			return Result.newSuccessResult();
		}
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode());
		}
		if (result.getResult() == null) {
			return Result.newSuccessResult();
		}

		List<MyReservationListResDto> datas = result.getResult().getDatas();
		if (ListUtils.getSize(datas) == 0) {
			return Result.newSuccessResult();
		}

		ItemListDTO itemListDTO = buildListItemView(result.getResult(), datas);

		if (itemListDTO.getCurrentPage() == 1 && ListUtils.getSize(itemListDTO.getViewItems()) != 0) {

			Struct4002View tipView = new Struct4002View();
			tipView.setTitle("您可从已确认且未报单的预约中选择一个进行报单：");

			List<ViewItem> itemList = new ArrayList<>();
			itemList.add(new ViewItem(4002, null, tipView));

			List<ViewItem> viewItems = itemListDTO.getViewItems();
			itemList.addAll(viewItems);

			itemListDTO.setViewItems(itemList);
		}

		return Result.newSuccessResult(itemListDTO);
	}

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {

		if (StringUtils.isBlank(keyword)) {
			return Result.newResult(true);
		}

		ReservationListQueryDto dto = new ReservationListQueryDto();
		dto.setPageSize(Integer.MAX_VALUE);
		dto.setPage(1);
		dto.setStatuss("1,2,3,4");
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.RESERVATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newFailureResult(CodeConstant.CODE_900002);
		}

		Result<PaginationSupport<MyReservationListResDto>> result = HttpUtils.get(TenantUrl.RESERVATION_GET_MY_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<MyReservationListResDto>>>() {
				});

		if (result == null) {
			return Result.newSuccessResult();
		}
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode());
		}
		if (result.getResult() == null) {
			return Result.newSuccessResult();
		}

		List<MyReservationListResDto> datas = result.getResult().getDatas();
		if (ListUtils.getSize(datas) == 0) {
			return Result.newSuccessResult();
		}
		List<String> keywordList = new ArrayList<>();
		for (MyReservationListResDto d : datas) {
			String customerName = d.getCustomerName();
			String productName = d.getProductName();
			if (customerName.contains(keyword)) {
				keywordList.add(customerName);
			}
			if (productName.contains(keyword)) {
				keywordList.add(productName);
			}
		}

		SearchView searchView = new SearchView(keywordList);

		return Result.newSuccessResult(searchView);
	}

	/**
	 * 构建预约列表和预约搜索列表视图
	 *
	 * @param page
	 * @param datas
	 * @return
	 */
	private ItemListDTO buildListItemView(PaginationSupport<MyReservationListResDto> page,
			List<MyReservationListResDto> datas) {

		ItemListDTO itemListDTO = new ItemListDTO(page);

		ArrayList<ViewItem> itemViews = new ArrayList<>();
		for (MyReservationListResDto d : datas) {

			Structure4001View view = new Structure4001View();
			view.setAppointId(d.getId() + "");

			view.setCustomerName(d.getCustomerName());

			view.setProductName(d.getProductName());

			Double reservationAmount = d.getReservationAmount();
			if (reservationAmount == null) {
				view.setAppintAmountText("");
				view.setLeftValue("");
			} else {

				if (MatcherUtil.isIntegerForDouble(reservationAmount)) {
					view.setAppintAmountText(reservationAmount.intValue() + "万");
					view.setLeftValue(reservationAmount.intValue() + "万");
				} else {
					view.setAppintAmountText(reservationAmount.toString() + "万");
					view.setLeftValue(reservationAmount.toString() + "万");
				}
			}
			/*
			 * String statusText = ""; switch (d.getStatus()) { case 0:
			 * statusText = "未提交"; break; case 1: statusText = "待审批"; break;
			 * case 2: statusText = "已审批"; break; case 3: statusText = "已驳回";
			 * break; case 4: statusText = "已取消"; break; default: break; }
			 */

			view.setStateText(d.getStatusText());
			view.setLeftName("预约金额");

			Date payDate = d.getEstimatePayDate();
			String payDateStr = DateUtils.format(payDate, DateUtils.YYYY_MM_DD);
			view.setRightValue(payDateStr);

			view.setRightName("预计打款日期");

			Date reservationDate = d.getReservationDate();
			String reservationDateStr = DateUtils.format(reservationDate, DateUtils.YYYY_MM_DD_HH_MM);
			view.setAppointmentDateText("预约时间 " + reservationDateStr);

			String jumpUrl = Schema.RESERVATION_DETAIL + String.format(Schema.RESERVATION_DETAIL_PARAMS, d.getId());

			itemViews.add(new ViewItem(4001, jumpUrl, view));
			itemViews.add(new ViewItem(2001, new DividingLineView()));
		}

		itemListDTO.setViewItems(itemViews);
		return itemListDTO;
	}

	@Override
	public Result<Boolean> update(Map<String, String> params) throws BaseException {

		String appointId = params.get("appointId");

		if (StringUtils.isBlank(appointId)) {
			throw new BaseException(CodeConstant.CODE_1300020);
		}
		Long id = null;
		try {
			id = Long.valueOf(appointId);
		} catch (Exception e) {
			throw new BaseException(CodeConstant.CODE_1200000);
		}

		Map<String, Object> idMap = new HashMap<>();
		idMap.put("id", id);
		Result<BaseReservationDto> queryResult = HttpUtils.get(TenantUrl.RESERVATION_GET, idMap,
				new BaseTypeReference<Result<BaseReservationDto>>() {
				});

		if (!queryResult.getSuccess() || queryResult.getResult() == null) {
			throw new BaseException(queryResult.getCode(), queryResult.getMessage());
		}

		BaseReservationDto dto = queryResult.getResult();

		String appointAmount = params.get("appointAmount");
		String giveAmountDate = params.get("giveAmountDate");
		String remarks = params.get("remarks");

		Double amount;
		if (StringUtils.isNotBlank(appointAmount)) {
			try {
				amount = Double.valueOf(appointAmount);
				dto.setReservationAmount(amount);
			} catch (Exception e) {
				throw new BaseException(CodeConstant.CODE_1300018);
			}
		}

		Long date;
		if (StringUtils.isNotBlank(giveAmountDate)) {
			try {
				date = Long.valueOf(giveAmountDate);
				dto.setEstimatePayDate(DateUtils.toDate(date));
			} catch (Exception e) {
				throw new BaseException(CodeConstant.CODE_1300021);
			}
		}

		dto.setRemark(remarks);

		Result<Boolean> result = HttpUtils.post(TenantUrl.RESERVATION_UPATE, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Boolean>>() {
				});

		return result;
	}

}
