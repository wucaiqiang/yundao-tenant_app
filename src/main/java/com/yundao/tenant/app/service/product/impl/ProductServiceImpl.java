
package com.yundao.tenant.app.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.FailListDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.product.BaseObjectDto;
import com.yundao.tenant.app.dto.product.BaseProduct;
import com.yundao.tenant.app.dto.product.BaseProductSale;
import com.yundao.tenant.app.dto.product.ProductActionSelectDto;
import com.yundao.tenant.app.dto.product.ProductAttachListResDto;
import com.yundao.tenant.app.dto.product.ProductBaseQueryReqDto;
import com.yundao.tenant.app.dto.product.ProductBaseResDto;
import com.yundao.tenant.app.dto.product.ProductCommissionDto;
import com.yundao.tenant.app.dto.product.ProductCommissionRuleDto;
import com.yundao.tenant.app.dto.product.ProductIncomeDto;
import com.yundao.tenant.app.dto.product.ProductIncomeRuleDto;
import com.yundao.tenant.app.dto.product.ProductManagerListResDto;
import com.yundao.tenant.app.dto.product.ProductManagerReqDto;
import com.yundao.tenant.app.dto.product.ProductNoticeDto;
import com.yundao.tenant.app.dto.product.ProductPartDetailDto;
import com.yundao.tenant.app.dto.product.UpdateProductStatusReqDto;
import com.yundao.tenant.app.dto.report.ProductReservationDeclarationReportDto;
import com.yundao.tenant.app.dto.roadshow.RoadshowResDto;
import com.yundao.tenant.app.dto.roadshow.VideoDetailResDto;
import com.yundao.tenant.app.dto.user.UserCardResDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.enums.product.MoneyTypeEnum;
import com.yundao.tenant.app.enums.product.ProductAttachShowTypeEnum;
import com.yundao.tenant.app.enums.product.ProductIncomeTypeEnum;
import com.yundao.tenant.app.enums.product.ProductIssuedStatusEnum;
import com.yundao.tenant.app.enums.product.ProductManagerActionEnum;
import com.yundao.tenant.app.enums.workflow.ProductExamineStatusEnum;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.product.ProductService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.FileSupport;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.util.VideoDurationUtils;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.LeftMiddleRightView;
import com.yundao.tenant.app.view.NameValueView;
import com.yundao.tenant.app.view.PropertyListView;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.product.CommissionRuleView;
import com.yundao.tenant.app.view.product.IncomeRuleView;
import com.yundao.tenant.app.view.product.IncomeValueView;
import com.yundao.tenant.app.view.product.OperateView;
import com.yundao.tenant.app.view.product.ProductDetailView;
import com.yundao.tenant.app.view.product.ProductListView;
import com.yundao.tenant.app.view.product.Structure1002View;
import com.yundao.tenant.app.view.product.Structure1004View;
import com.yundao.tenant.app.view.product.Structure1005View;
import com.yundao.tenant.app.view.product.Structure1007View;
import com.yundao.tenant.app.view.product.Structure1008View;
import com.yundao.tenant.app.view.product.Structure1009View;
import com.yundao.tenant.app.view.product.Structure1010View;
import com.yundao.tenant.app.view.product.Structure1012View;
import com.yundao.tenant.app.view.product.Structure1013View;
import com.yundao.tenant.app.view.product.Structure1016View;
import com.yundao.tenant.app.view.product.Structure2002View;
import com.yundao.tenant.app.view.roadshow.Structure1015View;
import com.yundao.tenant.app.view.user.Structure1017View;

/**
 * Function: Reason: Date: 2017年8月1日 下午4:03:28
 *
 * @author wucq
 */
@Service
public class ProductServiceImpl extends AbstractService implements ProductService {
	private Log logger = LogFactory.getLog(ProductServiceImpl.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ShareService shareService;

	@Override
	public Result<ItemListDTO> getManagerList(ProductManagerReqDto reqDto) throws BaseException {
		Map<String, Object> methodMap = ArgsUtils.toMap(reqDto);
		if (StringUtils.isNotBlank(reqDto.getKeyword())) {
			methodMap.put("name", reqDto.getKeyword());
		}
		Result<PaginationSupport<ProductManagerListResDto>> result = HttpUtils.get(TenantUrl.PRODUCT_GET_MANAGER_LIST,
				methodMap, new BaseTypeReference<Result<PaginationSupport<ProductManagerListResDto>>>() {
				});
		ItemListDTO itemListDTO = new ItemListDTO();

		if (result.getSuccess() && result.getResult() != null) {
			PaginationSupport<ProductManagerListResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);// 设置返回值分页信息

			List<ProductManagerListResDto> datas = pager.getDatas();
			if (datas == null || datas.isEmpty()) {
				return Result.newSuccessResult(itemListDTO);
			}

			Integer permission = permissionService.getUpdate(DataObjectEnum.PRODUCT.getCode()).getResult();
			//
			List<FailListDto> failListDtos = null;

			if (!(DataObjectPermissionEnum.NONE.getValue() == permission
					|| DataObjectPermissionEnum.PUBLIC.getValue() == permission)) {
				List<BaseObjectDto> baseObjets = getUpdateBaseObjectDtos(datas);
				Result<PermissionResultDto> permissionResult = permissionService.checkUpdateListForOwnerIds(baseObjets,
						DataObjectEnum.PRODUCT.getCode());
				PermissionResultDto permissionResultDto = permissionResult.getResult();
				failListDtos = permissionResultDto.getFailList();
			}
			List<ViewItem> viewItems = new ArrayList<>();
			StringBuilder ids = new StringBuilder();
			Map<Long, Structure1013View> viewMap = new HashMap<>();

			for (ProductManagerListResDto productDto : datas) {
				if (ids.length() > 0) {
					ids.append(",");
				}
				ids.append(productDto.getId());

				Structure1013View view1013 = new Structure1013View();
				view1013.setProductName(productDto.getName());
				view1013.setProductId(String.valueOf(productDto.getId()));
				view1013.setProductTypeText(productDto.getTypeName());
				view1013.setProductDockMan(productDto.getReceiverRealName() + "/" + productDto.getAssistantRealName());
				if (StringUtils.isBlank(productDto.getLevelText())) {
					view1013.setProductLevelText("");
				} else {
					view1013.setProductLevelText("产品等级：" + productDto.getLevelText());
				}
				view1013.setScope("产品规模：" + ((productDto.getProductScale() == null || productDto.getProductScale() == 0)
						? "" : NumberUtil.trimDoubleZero(String.valueOf(productDto.getProductScale())) + "万"));
				view1013.setSale("");// 设置预约，报单情况
				StringBuilder content = new StringBuilder();
				if (productDto.getIssuedStatus() == null
						|| productDto.getIssuedStatus() == ProductIssuedStatusEnum.NOT_ON_LINE.getValue()) {
					content.append("未上线");
					if (productDto.getExamineStatus() != null && (productDto.getExamineStatus()
							.equals(ProductExamineStatusEnum.APPROVALING.getValue())
							|| productDto.getExamineStatus().equals(ProductExamineStatusEnum.REJECT.getValue())
							|| productDto.getExamineStatus().equals(ProductExamineStatusEnum.CANCEL.getValue()))) {
						content.append(" ").append("申请上线");
						ProductExamineStatusEnum examineStatusEnum = ProductExamineStatusEnum
								.getEnum(productDto.getExamineStatus());
						if (examineStatusEnum != null) {
							content.append(examineStatusEnum.getName());

							if (examineStatusEnum == ProductExamineStatusEnum.REJECT
									|| examineStatusEnum == ProductExamineStatusEnum.CANCEL) {
								view1013.setReason(productDto.getReason());
							}
						}
					}
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.ON_LINE_PRE.getValue()) {
					content.append("上线准备中");
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.PRE_SAISE.getValue()) {
					Date onLineTime = productDto.getOnLineTime();
					content.append(DateFormatUtils.format(onLineTime, "yyyy-MM-dd") + "预售");
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.TO_RAISE.getValue()) {
					Date onLineTime = productDto.getOnLineTime();
					content.append(DateFormatUtils.format(onLineTime, "yyyy-MM-dd") + "募集中");
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.RAISE_END.getValue()) {
					content.append("募集结束");
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.SURVIVING.getValue()) {
					content.append("存续/封闭中");
				} else if (productDto.getIssuedStatus() == ProductIssuedStatusEnum.CLEAR_OUT.getValue()) {
					content.append("清算退出");
				}
				view1013.setContent("发行状态：" + content.toString());

				// 操作功能设置
				List<OperateView> operateList = new ArrayList<>();

				if (productDto.getActionDto() != null && productDto.getActionDto().getSelectDtos() != null) {
					List<ProductActionSelectDto> actionSelectDtos = productDto.getActionDto().getSelectDtos();
					for (ProductActionSelectDto action : actionSelectDtos) {
						if (action.getValue() == null) {
							continue;
						}
						OperateView operateView = new OperateView();
						operateView.setText(action.getLable());
						operateView.setType(action.getValue());
						operateView.setEnable(true);// 默认可操作

						// 这里的审核有申请上线，取消申请，重新申请，结束募集，启动预售，终止发行等，而通过或驳回操作，在产品审核列表中再进行
						if (productDto.getExamineStatus() != null && (productDto
								.getExamineStatus() == ProductExamineStatusEnum.REJECT.getValue()
								|| productDto.getExamineStatus() == ProductExamineStatusEnum.APPROVALING.getValue())) {
							if (!(productDto.getApplyUserId() != null
									&& productDto.getApplyUserId().equals(UserUtils.getUserId()))) {
								operateView.setEnable(false);// 取消操作只能流程发起人操作
								operateView.setTip("审批进行中，仅限申请发起人可执行此操作");
							}
						} else if (DataObjectPermissionEnum.PUBLIC.getValue() == permission) {
							operateView.setEnable(true);// 默认为true
						} else if (DataObjectPermissionEnum.NONE.getValue() == permission) {
							operateView.setEnable(false);
							operateView.setTip("您无操作产品的权限");
						} else if (BooleanUtils.isEmpty(failListDtos)) {
							operateView.setEnable(true);
						} else {
							for (FailListDto failListDto : failListDtos) {
								if (productDto.getId().equals(failListDto.getId())) {
									operateView.setEnable(false);

									if (DataObjectPermissionEnum.NONE.getValue() == permission) {
										operateView.setTip("您无操作产品的权限");
									} else if (DataObjectPermissionEnum.PERSONAL.getValue() == permission) {
										operateView.setTip("您的权限仅限于操作您对接或助理的产品");
									} else if (DataObjectPermissionEnum.DEPARTMENT.getValue() == permission) {
										operateView.setTip("您的权限仅限于操作您部门员工对接或助理的产品");
									} else {
										operateView.setTip("您无操作产品的权限");
									}

								}
							}
						}

						if (ProductManagerActionEnum.APPLY_ON_LINE.getCode() == action.getValue()) {
							operateView.setTitle("确定申请产品上线？");
							operateView.setContent("申请上线后，请留意审批结果，及时进行后续操作");
						} else if (ProductManagerActionEnum.CANCEL_APPLY_ON_LINE.getCode() == action.getValue()) {
							operateView.setTitle("确定取消上线申请？");
							operateView.setContent("取消后，产品将变为未上线状态");
						} else if (ProductManagerActionEnum.AGAIN_APPLY_ON_LINE.getCode() == action.getValue()) {
							operateView.setTitle("确定重新申请上线？");
							operateView.setContent("申请上线后，请留意审批结果，及时进行后续操作");
						} else if (ProductManagerActionEnum.START_UP_PRE_SAISE.getCode() == action.getValue()) {
							operateView.setTitle("确定启动产品预售？");
							operateView.setContent("启动预售后，产品可以开始接受预约");
						} else if (ProductManagerActionEnum.START_RAISE.getCode() == action.getValue()
								|| ProductManagerActionEnum.START_ON_LINE_RAISE_ING.getCode() == action.getValue()) {
							operateView.setTitle("确定启动产品募集？");
							operateView.setContent("启动募集后，产品正式进入募集中状态，接受打款报单");
						} else if (ProductManagerActionEnum.STOP_ISSUED.getCode() == action.getValue()) {
							operateView.setTitle("确定终止产品发行？");
							operateView.setContent("终止发行后，产品将不可进行预约和报单");
						} else if (ProductManagerActionEnum.RETURN_ON_LINE_READY.getCode() == action.getValue()) {
							operateView.setTitle("确定暂停预售？");
							operateView.setContent("暂停预售后，产品将返回到上线准备中状态，暂不接受预约和报单");
						} else if (ProductManagerActionEnum.RETURN_ON_LINE_READY_FROM_RAISE.getCode() == action
								.getValue()) {
							operateView.setTitle("确定暂停募集？");
							operateView.setContent("暂停预售后，产品将返回到上线准备中状态，暂不接受预约和报单");
						} else if (ProductManagerActionEnum.SET_RAISE_END.getCode() == action.getValue()) {
							operateView.setTitle("确定结束募集？");
							operateView.setContent("结束募集后，产品将进入募集结束状态，等待产品成立后可设定为封闭中状态");
						} else if (ProductManagerActionEnum.SET_SURVIVING.getCode() == action.getValue()) {
							operateView.setTitle("确定设为存续／封闭中？");
							operateView.setContent("产品成立进入存续期，可设为存续／封闭中状态");
						} else if (ProductManagerActionEnum.SET_CLEAR_OUT.getCode() == action.getValue()) {
							operateView.setTitle("确定设为清算退出？");
							operateView.setContent("设定为清算退出后产品将不可重新开放申购");
						} else if (ProductManagerActionEnum.OPEN_PURCHASE.getCode() == action.getValue()) {
							operateView.setTitle("确定重新开放申购？");
							operateView.setContent("重新开放申购后，产品重新进入募集中状态，可继续接受预约或报单");
						}

						operateList.add(operateView);
					}
				}

				view1013.setOperateList(operateList);
				viewMap.put(productDto.getId(), view1013);

				String jumpUrl = Schema.PRODUCT_DETAIL
						+ String.format(Schema.PRODUCT_DETAIL_PARAMS, productDto.getId(), productDto.getName());

				viewItems.add(new ViewItem(1013, jumpUrl, view1013));
			}
			// 获取产品预约与报单统计
			Map<String, Object> methodParams = new HashMap<>();
			methodParams.put("productIds", ids.toString());
			Result<List<ProductReservationDeclarationReportDto>> reportResult = HttpUtils.get(
					TenantUrl.PRODUCT_GET_RESERVATION_DECLARATION_TOTAL, methodParams,
					new BaseTypeReference<Result<List<ProductReservationDeclarationReportDto>>>() {
					});
			if (reportResult.getResult() != null && !reportResult.getResult().isEmpty()) {
				List<ProductReservationDeclarationReportDto> reportDtos = reportResult.getResult();
				for (ProductReservationDeclarationReportDto reportDto : reportDtos) {
					if (viewMap.containsKey(reportDto.getProductId())) {
						StringBuilder saleStr = new StringBuilder();
						saleStr.append("预约 ");
						saleStr.append(
								NumberUtil.trimDoubleZero(String.valueOf(reportDto.getReservationTotal())) + "万");
						saleStr.append(" / ");
						saleStr.append("报单 ");
						saleStr.append(
								NumberUtil.trimDoubleZero(String.valueOf(reportDto.getDeclarationTotal())) + "万");

						String saleStrResult = saleStr.toString();

						if ((reportDto.getReservationTotal() == null || reportDto.getReservationTotal() == 0)
								&& (reportDto.getDeclarationTotal() == null || reportDto.getDeclarationTotal() == 0)) {
							saleStrResult = "";
						}
						Structure1013View view = viewMap.get(reportDto.getProductId());

						view.setSale(saleStrResult);
					}
				}
			}
			itemListDTO.setViewItems(viewItems);

		}

		return Result.newSuccessResult(itemListDTO);

	}

	private void setNotCanAction(List<ProductActionSelectDto> actionDto, String title) {
		if (!BooleanUtils.isEmpty(actionDto)) {
			for (ProductActionSelectDto dto : actionDto) {
				dto.setCanAction(false);
				dto.setTitle(title);
			}
		}
	}

	private List<BaseObjectDto> getUpdateBaseObjectDtos(List<ProductManagerListResDto> baseProducts) {
		if (BooleanUtils.isEmpty(baseProducts)) {
			return null;
		}
		List<BaseObjectDto> baseObjets = new ArrayList<BaseObjectDto>();
		for (ProductManagerListResDto base : baseProducts) {
			BaseObjectDto baseObjet = new BaseObjectDto();
			BeanUtils.copyProperties(base, baseObjet);
			List<Long> ownerIds = new ArrayList<Long>();
			if (base.getAssistantId() != null || base.getReceiverId() != null) {
				if (base.getAssistantId() != null) {
					ownerIds.add(base.getAssistantId());
				}
				if (base.getReceiverId() != null) {
					ownerIds.add(base.getReceiverId());
				}
			}

			if (BooleanUtils.isEmpty(ownerIds)) {
				ownerIds.add(-1l);
			}
			baseObjet.setOwnerIds(ownerIds);
			baseObjets.add(baseObjet);
		}
		return baseObjets;
	}

	@Override
	public Result<ItemListDTO> getSelectedList(ProductBaseQueryReqDto reqDto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(reqDto);

		String url = TenantUrl.GET_LIST;

		if (StringUtils.isNotBlank(reqDto.getFilterType()) && "filter_order".equals(reqDto.getFilterType())) {

			url = TenantUrl.GET_DIRECT_MODEL_RESERVATION_LIST;// 1、募集中（可直接报单）的产品
																// 2、募集中（先预约后报单）且该员工有审批通过的预约的产品
																// 3、预售，且该员工有审批通过的预约的产品
		} else if (StringUtils.isNotBlank(reqDto.getFilterType()) && "filter_0_1".equals(reqDto.getFilterType())) {
			// 过虑掉0：未上线 1：上线准备中
			String issuedStatuses = ProductIssuedStatusEnum.PRE_SAISE.getValue() + ","
					+ ProductIssuedStatusEnum.TO_RAISE.getValue() + "," + ProductIssuedStatusEnum.RAISE_END.getValue()
					+ "," + ProductIssuedStatusEnum.SURVIVING.getValue() + ","
					+ ProductIssuedStatusEnum.CLEAR_OUT.getValue();
			methodParams.put("issuedStatuses", issuedStatuses);
		} else if (StringUtils.isNotBlank(reqDto.getFilterType()) && "filter_none".equals(reqDto.getFilterType())) {
			methodParams.put("issuedStatuses", "");
		} else {
			// 默认只查询预售和募集中
			String issuedStatuses = ProductIssuedStatusEnum.PRE_SAISE.getValue() + ","
					+ ProductIssuedStatusEnum.TO_RAISE.getValue();
			methodParams.put("issuedStatuses", issuedStatuses);
		}
		// 获取用户在各租户中详情信息
		Result<PaginationSupport<ProductBaseResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<ProductBaseResDto>>>() {
				});
		if (result == null || result.getResult() == null) {
			return Result.newFailureResult();
		}
		PaginationSupport<ProductBaseResDto> pager = result.getResult();

		ItemListDTO itemListDTO = new ItemListDTO(pager);

		// 获取产品列表
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		List<ProductBaseResDto> products = pager.getDatas();
		if (products != null && !products.isEmpty()) {
			for (ProductBaseResDto dto : products) {
				Structure1012View view1012 = new Structure1012View();
				view1012.setProductId(String.valueOf(dto.getId()));
				view1012.setProductName(dto.getName());
				view1012.setMinAppointAmount(NumberUtil.trimDoubleZero(String.valueOf(dto.getBuyStartPoint())));
				view1012.setIncreaseAppointAmount(
						NumberUtil.trimDoubleZero(String.valueOf(dto.getIncrementalAmount())));
				view1012.setStartAmountAppointText(
						NumberUtil.trimDoubleZero(String.valueOf(dto.getBuyStartPoint())) + "万起投");
				view1012.setAddOrderMode(dto.getDeclarationModel() == null ? 0 : dto.getDeclarationModel());
				view1012.setProductStatus(dto.getIssuedStatus());
				view1012.setProductStatusText(ProductIssuedStatusEnum.getEnumName(dto.getIssuedStatus()));
				view1012.setProductAppointNum(dto.getReservationNum());
				viewItems.add(new ViewItem(1012, view1012));
			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public List<ViewItem> getRecommendedList(Integer limit) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("limit", limit == null ? 3 : limit);
		// 获取用户在各租户中详情信息
		Result<List<ProductBaseResDto>> result = HttpUtils.get(TenantUrl.GET_RECOMMENDED_LIST, methodParams,
				new BaseTypeReference<Result<List<ProductBaseResDto>>>() {
				});
		List<ViewItem> viewItems = new ArrayList<>();

		if (result.getResult() != null && !result.getResult().isEmpty()) {
			for (ProductBaseResDto dto : result.getResult()) {
				List<ProductIncomeRuleDto> rules = dto.getPrIncomeRuleDtos();
				ProductListView view = new ProductListView();
				// 获取收益率区间
				String leftValue = this.getIncomeInterval(rules);

				view.setProductName(dto.getName());
				view.setLeftName("收益率");
				view.setLeftValue(leftValue);
				view.setMiddleName("投资期限");
				view.setMiddleValue(dto.getTimeLimit());
				view.setRightName("销售佣金");
				Boolean isPass = permissionService.checkRead(DataObjectEnum.COMMISSION.getCode(), dto.getCreateUserId())
						.getResult();
				if (!isPass) {
					view.setRightValue("无权限查看");
				} else {
					List<ProductCommissionRuleDto> ruleDtos = dto.getCommissionRuleDtos();
					if (ruleDtos != null && !ruleDtos.isEmpty()) {
						String ruleStr = getCommissionInterval(ruleDtos);
						view.setRightValue(ruleStr);
					}
				}
				view.setProductTypeText(dto.getTypeName());
				if (dto.getIsScale() != null && dto.getIsScale() == 1) {
					String moneyUnit = "万";
					MoneyTypeEnum moneyEnum = MoneyTypeEnum
							.getMoneyTypeEnum(String.valueOf(dto.getCurrencyCode().intValue()));
					if (moneyEnum != null) {
						moneyUnit = moneyEnum.getUnit();
					}
					view.setScope("规模 " + NumberUtil.trimDoubleZero(String.valueOf(dto.getProductScale())) + moneyUnit);
				}
				StringBuilder saleStr = new StringBuilder();
				saleStr.append("预约 ");
				saleStr.append(NumberUtil.trimDoubleZero(String.valueOf(dto.getReservationTotal())) + "万");
				saleStr.append(" / ");
				saleStr.append("报单 ");
				saleStr.append(NumberUtil.trimDoubleZero(String.valueOf(dto.getDeclarationTotal())) + "万");

				String saleStrResult = saleStr.toString();

				if ((dto.getReservationTotal() == null || dto.getReservationTotal() == 0)
						&& (dto.getDeclarationTotal() == null || dto.getDeclarationTotal() == 0)) {
					saleStrResult = "";
				}
				view.setSale(saleStrResult);

				String jumpUrl = Schema.PRODUCT_DETAIL
						+ String.format(Schema.PRODUCT_DETAIL_PARAMS, dto.getId(), dto.getName());
				viewItems.add(new ViewItem(1001, jumpUrl, view));
			}
		}
		return viewItems;
	}

	@Override
	public Result<ItemListDTO> getList(ProductBaseQueryReqDto reqDto) throws BaseException {
		reqDto.setOrderColumn("on_line_time");
		reqDto.setSort("desc");// 按上线时间倒序
		Map<String, Object> methodParams = ArgsUtils.toMap(reqDto);
		String issuedStatuses = ProductIssuedStatusEnum.PRE_SAISE.getValue() + ","
				+ ProductIssuedStatusEnum.TO_RAISE.getValue();// 只查询预售与募集中的
		methodParams.put("issuedStatuses", issuedStatuses);
		// 获取用户在各租户中详情信息
		Result<PaginationSupport<ProductBaseResDto>> result = HttpUtils.get(TenantUrl.GET_LIST, methodParams,
				new BaseTypeReference<Result<PaginationSupport<ProductBaseResDto>>>() {
				});
		if (result == null || result.getResult() == null) {
			return Result.newFailureResult();
		}

		ItemListDTO itemListDTO = new ItemListDTO();
		// 获取产品列表
		List<ViewItem> viewItems = new ArrayList<ViewItem>();

		PaginationSupport<ProductBaseResDto> pager = result.getResult();

		List<ProductBaseResDto> products = pager.getDatas();
		if (products != null && !products.isEmpty()) {
			for (ProductBaseResDto dto : products) {
				List<ProductIncomeRuleDto> rules = dto.getPrIncomeRuleDtos();
				ProductListView view = new ProductListView();
				// 获取收益率区间
				String leftValue = this.getIncomeInterval(rules);

				view.setProductName(dto.getName());
				view.setLeftName("收益率");
				view.setLeftValue(leftValue);
				view.setMiddleName("投资期限");
				view.setMiddleValue(dto.getTimeLimit());
				view.setRightName("销售佣金");
				Boolean isPass = permissionService.checkRead(DataObjectEnum.COMMISSION.getCode(), dto.getCreateUserId())
						.getResult();
				if (!isPass) {
					view.setRightValue("无权限查看");
				} else {
					List<ProductCommissionRuleDto> ruleDtos = dto.getCommissionRuleDtos();
					if (ruleDtos != null && !ruleDtos.isEmpty()) {
						String ruleStr = getCommissionInterval(ruleDtos);
						view.setRightValue(ruleStr);
					}
				}
				view.setProductTypeText(dto.getTypeName());
				if (dto.getIsScale() != null && dto.getIsScale() == 1) {
					String moneyUnit = "万";
					MoneyTypeEnum moneyEnum = MoneyTypeEnum
							.getMoneyTypeEnum(String.valueOf(dto.getCurrencyCode().intValue()));
					if (moneyEnum != null) {
						moneyUnit = moneyEnum.getUnit();
					}
					view.setScope("规模 " + NumberUtil.trimDoubleZero(String.valueOf(dto.getProductScale())) + moneyUnit);
				}

				StringBuilder saleStr = new StringBuilder();
				saleStr.append("预约 ");
				saleStr.append(NumberUtil.trimDoubleZero(String.valueOf(dto.getReservationTotal())) + "万");
				saleStr.append(" / ");
				saleStr.append("报单 ");
				saleStr.append(NumberUtil.trimDoubleZero(String.valueOf(dto.getDeclarationTotal())) + "万");

				String saleStrResult = saleStr.toString();

				if ((dto.getReservationTotal() == null || dto.getReservationTotal() == 0)
						&& (dto.getDeclarationTotal() == null || dto.getDeclarationTotal() == 0)) {
					saleStrResult = "";
				}

				view.setSale(saleStrResult);
				String jumpUrl = Schema.PRODUCT_DETAIL
						+ String.format(Schema.PRODUCT_DETAIL_PARAMS, dto.getId(), dto.getName());
				viewItems.add(new ViewItem(1001, jumpUrl, view));
			}
		}

		itemListDTO.setCurrentPage(pager.getCurrentPage());
		itemListDTO.setHasNext(pager.getCurrentPage() < pager.getTotalPage());
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);

	}

	private String getCommissionInterval(List<ProductCommissionRuleDto> rules) throws BaseException {
		Double minRate = 0d;
		Double maxRate = 0d;
		if (rules != null && !rules.isEmpty()) {
			for (ProductCommissionRuleDto rule : rules) {
				List<ProductCommissionDto> commissionDtos = rule.getProductCommissionDtos();
				if (commissionDtos == null || commissionDtos.isEmpty()) {
					continue;
				}
				for (ProductCommissionDto commissionDto : commissionDtos) {
					Double front = commissionDto.getFrontCommission() == null ? 0 : commissionDto.getFrontCommission();
					if (front > 0) {
						if (minRate == 0) {
							minRate = front;
						} else if (minRate > front) {
							minRate = front;
						}
						if (maxRate == 0) {
							maxRate = front;
						} else if (maxRate < front) {
							maxRate = front;
						}
					}

				}
			}
			StringBuilder builder = new StringBuilder();
			if (minRate > 0) {
				builder.append(NumberUtil.trimDoubleZero(String.valueOf(minRate)));
			}
			if (maxRate > 0 && maxRate != minRate) {
				if (builder.length() > 0) {
					builder.append("~");
				}
				builder.append(NumberUtil.trimDoubleZero(String.valueOf(maxRate)));
			}
			if (builder.length() > 0) {
				builder.append("%");
			}
			return builder.toString();
		}
		return "";
	}

	private String getIncomeInterval(List<ProductIncomeRuleDto> rules) throws BaseException {
		String typeValue = "";
		Double minRate = 0d;
		Double maxRate = 0d;
		if (rules != null && !rules.isEmpty()) {
			boolean flag = true;
			for (ProductIncomeRuleDto rule : rules) {
				List<ProductIncomeDto> incomes = rule.getProductIncomeDtos();
				if (incomes == null || incomes.isEmpty()) {
					continue;
				}
				for (ProductIncomeDto income : incomes) {
					Double fixIncomeRate = income.getFixIncomeRate() == null ? 0 : income.getFixIncomeRate();
					if (fixIncomeRate > 0) {
						if (minRate == 0) {
							minRate = fixIncomeRate;
						} else if (minRate > fixIncomeRate) {
							minRate = fixIncomeRate;
						}
						if (maxRate == 0) {
							maxRate = fixIncomeRate;
						} else if (maxRate < fixIncomeRate) {
							maxRate = fixIncomeRate;
						}
					}

					String type = income.getIncomeType();
					if (flag) {
						if (ProductIncomeTypeEnum.FLOAT.getValue().equals(type)
								|| ProductIncomeTypeEnum.FIXED_FLOAT.getValue().equals(type)) {
							typeValue = "浮动";
							flag = false;
						}
					}
				}
			}
			StringBuilder builder = new StringBuilder();
			if (minRate > 0) {
				builder.append(NumberUtil.trimDoubleZero(String.valueOf(minRate)));
			}
			if (maxRate > 0 && maxRate > minRate) {
				if (builder.length() > 0) {
					builder.append("~");
				}

				builder.append(NumberUtil.trimDoubleZero(String.valueOf(maxRate)));
			}
			if (builder.length() > 0) {
				builder.append("%");
			}
			if (StringUtils.isNotBlank(typeValue)) {
				builder.append("+").append(typeValue);
			}
			return builder.toString();
		}
		return "";
	}

	@Override
	public Result<ProductDetailView> getDetailByIdForShare(Long id) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取用户在各租户中详情信息
		Result<ProductPartDetailDto> result = HttpUtils.get(TenantUrl.GET_DETAIL_BY_ID, methodParams,
				new BaseTypeReference<Result<ProductPartDetailDto>>() {
				});

		Result<UserCardResDto> userDtoResult = HttpUtils.get(TenantUrl.USER_DETAIL_GET_CARD, null,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});

		ProductDetailView view = new ProductDetailView();
		List<ViewItem> viewItems = new ArrayList<>();

		UserCardResDto userCardResDto = null;
		if (userDtoResult.getResult() != null) {
			userCardResDto = userDtoResult.getResult();
			Structure1017View structure1017View = new Structure1017View();
			structure1017View.setAvatar(userCardResDto.getAvatar());
			structure1017View.setCardHeaderBit(userCardResDto.getCardHeaderBit());
			structure1017View.setCompany(userCardResDto.getTenantName());
			structure1017View.setMobile(userCardResDto.getMobile());
			structure1017View.setName(userCardResDto.getRealName());
			viewItems.add(new ViewItem(1017, structure1017View));
		}

		if (result != null && result.getResult() != null) {
			ProductPartDetailDto detailDto = result.getResult();
			// 设置产品分享信息
			ShareInfo shareInfo = new ShareInfo();
			shareInfo.setTitle(detailDto.getName());
			shareInfo.setContent(detailDto.getHighlight());
			shareInfo.setImgUrl(ConfigUtils.getValue("product.detail.share.url"));
			String url = String.format(H5Constant.PRODUCT_DETAIL_SHARE, shareService.getCCShareDomainUrl(),
					detailDto.getId(), userCardResDto == null ? "" : userCardResDto.getCardUuid());
			shareInfo.setUrl(url);
			view.setShareInfo(shareInfo);

			// 产品销售信息
			BaseProductSale productSale = detailDto.getProductSale();
			String moneyUnit = "万";
			if (productSale != null && StringUtils.isNotBlank(productSale.getCurrencyCode())) {
				MoneyTypeEnum moneyEnum = MoneyTypeEnum.getMoneyTypeEnum(productSale.getCurrencyCode());
				if (moneyEnum != null) {
					moneyUnit = moneyEnum.getUnit();
				}
				view.setAmountUnit(moneyEnum.getUnit());
				view.setStartAmountAppointText(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint()))
						+ moneyEnum.getUnit() + "起投");
				view.setMinAppointAmount(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())));
				view.setIncreaseAppointAmount(
						NumberUtil.trimDoubleZero(String.valueOf(detailDto.getIncrementalAmount())));
			}
			view.setProductStatus(detailDto.getIssuedStatus());
			view.setProductStatusText(detailDto.getIssuedStatusText());
			view.setAddOrderMode(detailDto.getDeclarationModel() == null ? 0 : detailDto.getDeclarationModel());
			view.setProductAppointNum(detailDto.getReservationNum());
			view.setAccessMan(detailDto.getReceiverName());
			view.setAccessManMobile(detailDto.getReceiverMobile());
			view.setWxInfo(detailDto.getWxContent());

			// 收益率
			List<ProductIncomeRuleDto> incomeRules = detailDto.getIncomeDtos();
			// 获取收益率区间
			String incomeinterval = this.getIncomeInterval(incomeRules);

			// 详情头
			Structure1002View view1002 = new Structure1002View();
			view1002.setLeftName("预期收益率");
			view1002.setLeftValue(incomeinterval);
			view1002.setRightName("投资期限");
			view1002.setRightValue(detailDto.getTimeLimit());
			view1002.setTopValue(detailDto.getName());
			List<String> tags = new ArrayList<>();
			tags.add(detailDto.getTypeName());
			tags.add(detailDto.getIssuedChannelText());
			tags.add(detailDto.getInvestDomainText());
			view1002.setTags(tags);
			view1002.setBottomValue(detailDto.getHighlight());
			viewItems.add(new ViewItem(1002, view1002));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 滑动视图
			String[] titles = { "产品要素", "项目附件", "信息披露" };
			Structure1004View view1004 = new Structure1004View();
			view1004.setTitles(titles);
			viewItems.add(new ViewItem(1004, view1004));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 设置路演信息
			List<RoadshowResDto> roadshows = detailDto.getRoadshows();
			if (roadshows != null && !roadshows.isEmpty()) {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("相关路演", true)));

				for (RoadshowResDto roadshow : roadshows) {
					String duration = "";
					VideoDetailResDto videoDetailResDto = roadshow.getVideo();
					if (videoDetailResDto != null) {
						duration = VideoDurationUtils.changeToText(
								videoDetailResDto.getDuration() == null ? 0 : videoDetailResDto.getDuration());
					}
					String jumpUrl = Schema.ROADSHOW_DETAIL
							+ String.format(Schema.ROADSHOW_DETAIL_PARAMS, roadshow.getId());
					viewItems.add(new ViewItem(1015, jumpUrl,
							new Structure1015View("路演", roadshow.getTitle(), roadshow.getCoverUrl(), duration)));
				}

				viewItems.add(new ViewItem(2001, new DividingLineView()));

				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("产品信息", false)));
			} else {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("产品信息", true)));
			}

			List<NameValueView> nameValue = new ArrayList<>();
			nameValue.add(new NameValueView("对接人", detailDto.getReceiverName()));
			nameValue.add(new NameValueView("产品类别", detailDto.getTypeName()));
			nameValue.add(new NameValueView("管理机构", detailDto.getMechanism()));
			nameValue.add(new NameValueView("发行通道", detailDto.getIssuedChannelText()));
			nameValue.add(new NameValueView("投资标的", detailDto.getInvestDomainText()));
			nameValue.add(new NameValueView("产品规模",
					(NumberUtil.trimDoubleZero(String.valueOf(productSale.getProductScale())) + moneyUnit)));
			nameValue.add(new NameValueView("起投金额",
					(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())) + moneyUnit)));
			nameValue.add(new NameValueView("投资币种", detailDto.getCurrencyCodeText()));
			nameValue.add(new NameValueView("投资期限", detailDto.getTimeLimit()));
			if (productSale.getSaleStartDate() != null && productSale.getSaleEndDate() != null) {
				nameValue.add(
						new NameValueView("募集期", DateFormatUtils.format(productSale.getSaleStartDate(), "yyyy年MM月dd日")
								+ "-" + DateFormatUtils.format(productSale.getSaleEndDate(), "yyyy年MM月dd日")));
			}
			nameValue.add(new NameValueView("认购费", detailDto.getProBuyFee()));
			nameValue.add(new NameValueView("管理费", detailDto.getProManageFee()));
			viewItems.add(new ViewItem(1006, new PropertyListView(nameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("募集帐号", false)));

			List<NameValueView> bankNameValue = new ArrayList<>();
			bankNameValue.add(new NameValueView("开户名", productSale.getAccountName()));
			bankNameValue.add(new NameValueView("募集银行", productSale.getRaiseBank()));
			bankNameValue.add(new NameValueView("打款帐号", productSale.getPayAccount()));
			bankNameValue.add(new NameValueView("打款备注", productSale.getPayRemark()));

			viewItems.add(new ViewItem(1006, new PropertyListView(bankNameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("收益模式", false)));

			List<IncomeRuleView> incomeRuleViews = new ArrayList<>();

			if (incomeRules != null && !incomeRules.isEmpty()) {
				for (ProductIncomeRuleDto rule : incomeRules) {

					IncomeRuleView inRuleView = new IncomeRuleView();
					inRuleView.setAmountTitle("认购金额");
					inRuleView.setTimeLimitTitle("认购期限");
					inRuleView.setFixedRateTitle("收益率");
					inRuleView.setFloatRateTitle("浮动收益");
					inRuleView.setClassName(rule.getRuleName());

					List<IncomeValueView> incomeValuesViews = new ArrayList<>();
					inRuleView.setValues(incomeValuesViews);

					List<ProductIncomeDto> incomes = rule.getProductIncomeDtos();
					if (incomes == null || incomes.isEmpty()) {
						continue;
					}

					for (ProductIncomeDto income : incomes) {
						String buyTimeLimit = (income.getBuyTimeLimit() == null ? 0 : income.getBuyTimeLimit()) + "个月";

						incomeValuesViews.add(new IncomeValueView(
								income.getBuyMin() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMin())),
								income.getBuyMax() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMax())),
								buyTimeLimit, income.getFixIncomeRate(), income.getFloatIncomeRate()));
					}

					incomeRuleViews.add(inRuleView);

				}
			}
			if (incomeRuleViews == null || incomeRuleViews.isEmpty()) {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无产品收益")));
			} else {
				viewItems.add(new ViewItem(1007, new Structure1007View(incomeRuleViews)));
			}

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(1005, new Structure1005View("项目附件", true)));
			/**
			 * 产品附件
			 */
			List<ProductAttachListResDto> attachDtos = detailDto.getAttachDtos();
			if (attachDtos != null && !attachDtos.isEmpty()) {
				int count = 0;
				List<ProductAttachListResDto> insideList = new ArrayList<>();
				List<ProductAttachListResDto> outsideList = new ArrayList<>();
				for (ProductAttachListResDto attach : attachDtos) {
					String fileFormat = FileSupport.getFileFormat(attach.getUrl());
					if (StringUtils.isBlank(fileFormat) || attach.getShowType() == null) {
						count++;
						logger.info("产品附件，ID为：%s，文件格式为：%s,手机端不支持。。。", attach.getId(), fileFormat);
						continue;
					}
					if (attach.getShowType() == ProductAttachShowTypeEnum.ALL.getValue()) {
						outsideList.add(attach);
					} else if (attach.getShowType() == ProductAttachShowTypeEnum.OUTSIDE.getValue()) {
						outsideList.add(attach);
					}
				}
				if (count == attachDtos.size()) {
					viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
				}
				if (outsideList != null && !outsideList.isEmpty()) {
					viewItems.add(new ViewItem(1016, new Structure1016View("客户可见", "outside")));
					for (ProductAttachListResDto attach : outsideList) {
						String fileFormat = FileSupport.getFileFormat(attach.getUrl());
						Structure1009View attachView = new Structure1009View();
						attachView.setTitle(attach.getSourceName());
						attachView.setFileType(fileFormat);
						attachView.setFileUrl(attach.getUrl());
						attachView.setVisiable("outside");
						ShareInfo attShare = new ShareInfo();
						attShare.setTitle(attach.getSourceName());
						attShare.setContent(userCardResDto == null ? "" : userCardResDto.getTenantName());
						attShare.setImgUrl(ConfigUtils.getValue("product.attach.share.url"));
						attShare.setUrl(attach.getUrl());
						attachView.setShareInfo(attShare);

						viewItems.add(new ViewItem(1009, attachView));
					}
				}

			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
			}

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(1005, new Structure1005View("信息披露", true)));

			List<ProductNoticeDto> noticeDtos = detailDto.getNoticeModels();
			if (noticeDtos != null && !noticeDtos.isEmpty()) {
				for (ProductNoticeDto notice : noticeDtos) {
					Structure1010View noticeView = new Structure1010View();
					noticeView.setTitle(notice.getTitle());
					noticeView.setType(notice.getNoticeTypeName());
					noticeView.setUpdateTime(DateFormatUtils.format(notice.getSendTime(), "MM月dd日"));
					String jumpUrl = Schema.PRODUCT_NOTICE_DETAIL
							+ String.format(Schema.PRODUCT_NOTICE_DETAIL_PARAMS, notice.getId(), "prodcutDetail");
					viewItems.add(new ViewItem(1010, jumpUrl, noticeView));
				}
			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无信息披露")));
			}

		} else {
			return Result.newFailureResult(CodeConstant.CODE_1300023);
		}
		view.setViewItems(viewItems);

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<ProductDetailView> getDetailByIdV2(Long id) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取用户在各租户中详情信息
		Result<ProductPartDetailDto> result = HttpUtils.get(TenantUrl.GET_DETAIL_BY_ID, methodParams,
				new BaseTypeReference<Result<ProductPartDetailDto>>() {
				});

		Result<UserCardResDto> userDtoResult = HttpUtils.get(TenantUrl.USER_DETAIL_GET_CARD, null,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});
		UserCardResDto userCardResDto = null;
		if (userDtoResult.getResult() != null) {
			userCardResDto = userDtoResult.getResult();
		}
		ProductDetailView view = new ProductDetailView();
		List<ViewItem> viewItems = new ArrayList<>();

		if (result != null && result.getResult() != null) {
			ProductPartDetailDto detailDto = result.getResult();
			// 设置产品分享信息
			ShareInfo shareInfo = new ShareInfo();
			shareInfo.setTitle(detailDto.getName());
			shareInfo.setContent(detailDto.getHighlight());
			shareInfo.setImgUrl(ConfigUtils.getValue("product.detail.share.url"));
			String url = String.format(H5Constant.PRODUCT_DETAIL_SHARE, shareService.getCCShareDomainUrl(),
					detailDto.getId(), userCardResDto == null ? "" : userCardResDto.getCardUuid());
			shareInfo.setUrl(url);
			view.setShareInfo(shareInfo);

			// 产品销售信息
			BaseProductSale productSale = detailDto.getProductSale();
			String moneyUnit = "万";
			if (productSale != null && StringUtils.isNotBlank(productSale.getCurrencyCode())) {
				MoneyTypeEnum moneyEnum = MoneyTypeEnum.getMoneyTypeEnum(productSale.getCurrencyCode());
				if (moneyEnum != null) {
					moneyUnit = moneyEnum.getUnit();
				}
				view.setAmountUnit(moneyEnum.getUnit());
				view.setStartAmountAppointText(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint()))
						+ moneyEnum.getUnit() + "起投");
				view.setMinAppointAmount(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())));
				view.setIncreaseAppointAmount(
						NumberUtil.trimDoubleZero(String.valueOf(detailDto.getIncrementalAmount())));
			}
			view.setProductStatus(detailDto.getIssuedStatus());
			view.setProductStatusText(detailDto.getIssuedStatusText());
			view.setAddOrderMode(detailDto.getDeclarationModel() == null ? 0 : detailDto.getDeclarationModel());
			view.setProductAppointNum(detailDto.getReservationNum());
			view.setAccessMan(detailDto.getReceiverName());
			view.setAccessManMobile(detailDto.getReceiverMobile());
			view.setWxInfo(detailDto.getWxContent());

			// 收益率
			List<ProductIncomeRuleDto> incomeRules = detailDto.getIncomeDtos();
			// 获取收益率区间
			String incomeinterval = this.getIncomeInterval(incomeRules);

			// 详情头
			Structure1002View view1002 = new Structure1002View();
			view1002.setLeftName("预期收益率");
			view1002.setLeftValue(incomeinterval);
			view1002.setRightName("投资期限");
			view1002.setRightValue(detailDto.getTimeLimit());
			view1002.setTopValue(detailDto.getName());
			List<String> tags = new ArrayList<>();
			tags.add(detailDto.getTypeName());
			tags.add(detailDto.getIssuedChannelText());
			tags.add(detailDto.getInvestDomainText());
			view1002.setTags(tags);
			view1002.setBottomValue(detailDto.getHighlight());
			viewItems.add(new ViewItem(1002, view1002));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 滑动视图
			String[] titles = { "产品要素", "项目附件", "信息披露" };
			Structure1004View view1004 = new Structure1004View();
			view1004.setTitles(titles);
			viewItems.add(new ViewItem(1004, view1004));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 设置路演信息
			List<RoadshowResDto> roadshows = detailDto.getRoadshows();
			if (roadshows != null && !roadshows.isEmpty()) {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("相关路演", true)));

				for (RoadshowResDto roadshow : roadshows) {
					String duration = "";
					VideoDetailResDto videoDetailResDto = roadshow.getVideo();
					if (videoDetailResDto != null) {
						duration = VideoDurationUtils.changeToText(
								videoDetailResDto.getDuration() == null ? 0 : videoDetailResDto.getDuration());
					}
					String jumpUrl = Schema.ROADSHOW_DETAIL
							+ String.format(Schema.ROADSHOW_DETAIL_PARAMS, roadshow.getId());
					viewItems.add(new ViewItem(1015, jumpUrl,
							new Structure1015View("路演", roadshow.getTitle(), roadshow.getCoverUrl(), duration)));
				}

				viewItems.add(new ViewItem(2001, new DividingLineView()));

				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("产品信息", false)));
			} else {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("产品信息", true)));
			}

			List<NameValueView> nameValue = new ArrayList<>();
			nameValue.add(new NameValueView("对接人", detailDto.getReceiverName()));
			nameValue.add(new NameValueView("产品类别", detailDto.getTypeName()));
			nameValue.add(new NameValueView("管理机构", detailDto.getMechanism()));
			nameValue.add(new NameValueView("发行通道", detailDto.getIssuedChannelText()));
			nameValue.add(new NameValueView("投资标的", detailDto.getInvestDomainText()));
			nameValue.add(new NameValueView("产品规模",
					(NumberUtil.trimDoubleZero(String.valueOf(productSale.getProductScale())) + moneyUnit)));
			nameValue.add(new NameValueView("起投金额",
					(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())) + moneyUnit)));
			nameValue.add(new NameValueView("投资币种", detailDto.getCurrencyCodeText()));
			nameValue.add(new NameValueView("投资期限", detailDto.getTimeLimit()));
			if (productSale.getSaleStartDate() != null && productSale.getSaleEndDate() != null) {
				nameValue.add(
						new NameValueView("募集期", DateFormatUtils.format(productSale.getSaleStartDate(), "yyyy年MM月dd日")
								+ "-" + DateFormatUtils.format(productSale.getSaleEndDate(), "yyyy年MM月dd日")));
			}
			nameValue.add(new NameValueView("认购费", detailDto.getProBuyFee()));
			nameValue.add(new NameValueView("管理费", detailDto.getProManageFee()));
			viewItems.add(new ViewItem(1006, new PropertyListView(nameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("募集帐号", false)));

			List<NameValueView> bankNameValue = new ArrayList<>();
			bankNameValue.add(new NameValueView("开户名", productSale.getAccountName()));
			bankNameValue.add(new NameValueView("募集银行", productSale.getRaiseBank()));
			bankNameValue.add(new NameValueView("打款帐号", productSale.getPayAccount()));
			bankNameValue.add(new NameValueView("打款备注", productSale.getPayRemark()));

			viewItems.add(new ViewItem(1006, new PropertyListView(bankNameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("收益模式", false)));

			List<IncomeRuleView> incomeRuleViews = new ArrayList<>();

			if (incomeRules != null && !incomeRules.isEmpty()) {
				for (ProductIncomeRuleDto rule : incomeRules) {

					IncomeRuleView inRuleView = new IncomeRuleView();
					inRuleView.setAmountTitle("认购金额");
					inRuleView.setTimeLimitTitle("认购期限");
					inRuleView.setFixedRateTitle("收益率");
					inRuleView.setFloatRateTitle("浮动收益");
					inRuleView.setClassName(rule.getRuleName());

					List<IncomeValueView> incomeValuesViews = new ArrayList<>();
					inRuleView.setValues(incomeValuesViews);

					List<ProductIncomeDto> incomes = rule.getProductIncomeDtos();
					if (incomes == null || incomes.isEmpty()) {
						continue;
					}

					for (ProductIncomeDto income : incomes) {
						String buyTimeLimit = (income.getBuyTimeLimit() == null ? 0 : income.getBuyTimeLimit()) + "个月";

						incomeValuesViews.add(new IncomeValueView(
								income.getBuyMin() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMin())),
								income.getBuyMax() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMax())),
								buyTimeLimit, income.getFixIncomeRate(), income.getFloatIncomeRate()));
					}

					incomeRuleViews.add(inRuleView);

				}
			}
			if (incomeRuleViews == null || incomeRuleViews.isEmpty()) {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无产品收益")));
			} else {
				viewItems.add(new ViewItem(1007, new Structure1007View(incomeRuleViews)));
			}

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			Boolean isPass = permissionService
					.checkRead(DataObjectEnum.COMMISSION.getCode(), detailDto.getCreateUserId()).getResult();
			if (isPass) {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("销售佣金", false)));

				// 获取销售佣金
				List<ProductCommissionRuleDto> commissionRuleDtos = detailDto.getCommissionDtos();

				List<CommissionRuleView> commissionRuleViews = new ArrayList<>();

				if (commissionRuleDtos != null && !commissionRuleDtos.isEmpty()) {
					for (ProductCommissionRuleDto rule : commissionRuleDtos) {

						CommissionRuleView ruleView = new CommissionRuleView();
						ruleView.setLeftTitle("销售金额");
						ruleView.setMiddleTitle("前端佣金");
						ruleView.setRightTitle("后端佣金");
						ruleView.setClassName(rule.getRuleName());
						ruleView.setBottomName("佣金备注");
						ruleView.setBottomValue(rule.getRemark());

						List<LeftMiddleRightView> leftMiddleRightViews = new ArrayList<>();
						ruleView.setValues(leftMiddleRightViews);

						List<ProductCommissionDto> commissionDtos = rule.getProductCommissionDtos();
						if (commissionDtos == null || commissionDtos.isEmpty()) {
							continue;
						}

						for (ProductCommissionDto commissionDto : commissionDtos) {
							StringBuilder builder = new StringBuilder();
							if (commissionDto.getSaleMin() != null) {
								builder.append(NumberUtil.trimDoubleZero(String.valueOf(commissionDto.getSaleMin())));
							}
							if (commissionDto.getSaleMax() != null) {
								if (builder.length() > 0) {
									builder.append("-");
								}
								builder.append(NumberUtil.trimDoubleZero(String.valueOf(commissionDto.getSaleMax())));
							}

							String buy = builder.toString() + "万";

							String middleValue = "", rightValue = "";
							Double front = commissionDto.getFrontCommission();
							if (front != null) {
								middleValue = NumberUtil.trimDoubleZero(String.valueOf(front)) + "%";
							}
							Double back = commissionDto.getBackCommission();
							if (back != null) {
								rightValue = NumberUtil.trimDoubleZero(String.valueOf(back)) + "%";
							}
							leftMiddleRightViews.add(new LeftMiddleRightView(buy, middleValue, rightValue));
						}

						commissionRuleViews.add(ruleView);

					}
				}
				if (commissionRuleViews == null || commissionRuleViews.isEmpty()) {
					viewItems.add(new ViewItem(2002, new Structure2002View("暂无销售佣金")));
				} else {
					viewItems.add(new ViewItem(1008, new Structure1008View(commissionRuleViews)));
				}

				viewItems.add(new ViewItem(2001, new DividingLineView()));

			}

			viewItems.add(new ViewItem(1005, new Structure1005View("项目附件", true)));
			/**
			 * 产品附件
			 */
			List<ProductAttachListResDto> attachDtos = detailDto.getAttachDtos();
			if (attachDtos != null && !attachDtos.isEmpty()) {
				int count = 0;
				List<ProductAttachListResDto> insideList = new ArrayList<>();
				List<ProductAttachListResDto> outsideList = new ArrayList<>();
				for (ProductAttachListResDto attach : attachDtos) {
					String fileFormat = FileSupport.getFileFormat(attach.getUrl());
					if (StringUtils.isBlank(fileFormat) || attach.getShowType() == null) {
						count++;
						logger.info("产品附件，ID为：%s，文件格式为：%s,手机端不支持。。。", attach.getId(), fileFormat);
						continue;
					}
					if (attach.getShowType() == ProductAttachShowTypeEnum.ALL.getValue()) {
						insideList.add(attach);
						outsideList.add(attach);
					} else if (attach.getShowType() == ProductAttachShowTypeEnum.INSIDE.getValue()) {
						insideList.add(attach);
					} else if (attach.getShowType() == ProductAttachShowTypeEnum.OUTSIDE.getValue()) {
						outsideList.add(attach);
					}
				}
				if (count == attachDtos.size()) {
					viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
				}
				if (insideList != null && !insideList.isEmpty()) {
					viewItems.add(new ViewItem(1016, new Structure1016View("内部可见", "inside")));
					for (ProductAttachListResDto attach : insideList) {
						String fileFormat = FileSupport.getFileFormat(attach.getUrl());
						Structure1009View attachView = new Structure1009View();
						attachView.setTitle(attach.getSourceName());
						attachView.setFileType(fileFormat);
						attachView.setFileUrl(attach.getUrl());
						attachView.setVisiable("inside");
						ShareInfo attShare = new ShareInfo();
						attShare.setTitle(attach.getSourceName());
						attShare.setContent(userCardResDto == null ? "" : userCardResDto.getTenantName());
						attShare.setImgUrl(ConfigUtils.getValue("product.attach.share.url"));
						attShare.setUrl(attach.getUrl());
						attachView.setShareInfo(attShare);
						viewItems.add(new ViewItem(1009, attachView));
					}
				}
				if (outsideList != null && !outsideList.isEmpty()) {
					viewItems.add(new ViewItem(1016, new Structure1016View("客户可见", "outside")));
					for (ProductAttachListResDto attach : outsideList) {
						String fileFormat = FileSupport.getFileFormat(attach.getUrl());
						Structure1009View attachView = new Structure1009View();
						attachView.setTitle(attach.getSourceName());
						attachView.setFileType(fileFormat);
						attachView.setFileUrl(attach.getUrl());
						attachView.setVisiable("outside");
						ShareInfo attShare = new ShareInfo();
						attShare.setTitle(attach.getSourceName());
						attShare.setContent(userCardResDto == null ? "" : userCardResDto.getTenantName());
						attShare.setImgUrl(ConfigUtils.getValue("product.attach.share.url"));
						attShare.setUrl(attach.getUrl());
						attachView.setShareInfo(attShare);

						viewItems.add(new ViewItem(1009, attachView));
					}
				}

			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
			}

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(1005, new Structure1005View("信息披露", true)));

			List<ProductNoticeDto> noticeDtos = detailDto.getNoticeModels();
			if (noticeDtos != null && !noticeDtos.isEmpty()) {
				for (ProductNoticeDto notice : noticeDtos) {
					Structure1010View noticeView = new Structure1010View();
					noticeView.setTitle(notice.getTitle());
					noticeView.setType(notice.getNoticeTypeName());
					noticeView.setUpdateTime(DateFormatUtils.format(notice.getSendTime(), "MM月dd日"));
					String jumpUrl = Schema.PRODUCT_NOTICE_DETAIL
							+ String.format(Schema.PRODUCT_NOTICE_DETAIL_PARAMS, notice.getId(), "prodcutDetail");
					viewItems.add(new ViewItem(1010, jumpUrl, noticeView));
				}
			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无信息披露")));
			}

		} else {
			return Result.newFailureResult(CodeConstant.CODE_1300023);
		}
		view.setViewItems(viewItems);

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<ProductDetailView> getDetailById(Long id) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		// 获取用户在各租户中详情信息
		Result<ProductPartDetailDto> result = HttpUtils.get(TenantUrl.GET_DETAIL_BY_ID, methodParams,
				new BaseTypeReference<Result<ProductPartDetailDto>>() {
				});
		ProductDetailView view = new ProductDetailView();
		List<ViewItem> viewItems = new ArrayList<>();

		if (result != null && result.getResult() != null) {
			ProductPartDetailDto detailDto = result.getResult();
			// 产品销售信息
			BaseProductSale productSale = detailDto.getProductSale();
			String moneyUnit = "万";
			if (productSale != null && StringUtils.isNotBlank(productSale.getCurrencyCode())) {
				MoneyTypeEnum moneyEnum = MoneyTypeEnum.getMoneyTypeEnum(productSale.getCurrencyCode());
				if (moneyEnum != null) {
					moneyUnit = moneyEnum.getUnit();
				}
				view.setAmountUnit(moneyEnum.getUnit());
				view.setStartAmountAppointText(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint()))
						+ moneyEnum.getUnit() + "起投");
				view.setMinAppointAmount(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())));
				view.setIncreaseAppointAmount(
						NumberUtil.trimDoubleZero(String.valueOf(detailDto.getIncrementalAmount())));
			}
			view.setProductStatus(detailDto.getIssuedStatus());
			view.setProductStatusText(detailDto.getIssuedStatusText());
			view.setAddOrderMode(detailDto.getDeclarationModel() == null ? 0 : detailDto.getDeclarationModel());
			view.setProductAppointNum(detailDto.getReservationNum());
			view.setAccessMan(detailDto.getReceiverName());
			view.setAccessManMobile(detailDto.getReceiverMobile());
			view.setWxInfo(detailDto.getWxContent());

			// 收益率
			List<ProductIncomeRuleDto> incomeRules = detailDto.getIncomeDtos();
			// 获取收益率区间
			String incomeinterval = this.getIncomeInterval(incomeRules);

			// 详情头
			Structure1002View view1002 = new Structure1002View();
			view1002.setLeftName("预期收益率");
			view1002.setLeftValue(incomeinterval);
			view1002.setRightName("投资期限");
			view1002.setRightValue(detailDto.getTimeLimit());
			view1002.setTopValue(detailDto.getName());
			List<String> tags = new ArrayList<>();
			tags.add(detailDto.getTypeName());
			tags.add(detailDto.getIssuedChannelText());
			tags.add(detailDto.getInvestDomainText());
			view1002.setTags(tags);
			view1002.setBottomValue(detailDto.getHighlight());
			viewItems.add(new ViewItem(1002, view1002));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 滑动视图
			String[] titles = { "产品要素", "项目附件", "信息披露" };
			Structure1004View view1004 = new Structure1004View();
			view1004.setTitles(titles);
			viewItems.add(new ViewItem(1004, view1004));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题

			viewItems.add(new ViewItem(1005, new Structure1005View("产品介绍", true)));

			List<NameValueView> nameValue = new ArrayList<>();
			nameValue.add(new NameValueView("对接人", detailDto.getReceiverName()));
			nameValue.add(new NameValueView("产品类别", detailDto.getTypeName()));
			nameValue.add(new NameValueView("管理机构", detailDto.getMechanism()));
			nameValue.add(new NameValueView("发行通道", detailDto.getIssuedChannelText()));
			nameValue.add(new NameValueView("投资标的", detailDto.getInvestDomainText()));
			nameValue.add(new NameValueView("产品规模",
					(NumberUtil.trimDoubleZero(String.valueOf(productSale.getProductScale())) + moneyUnit)));
			nameValue.add(new NameValueView("起投金额",
					(NumberUtil.trimDoubleZero(String.valueOf(detailDto.getBuyStartPoint())) + moneyUnit)));
			nameValue.add(new NameValueView("投资币种", detailDto.getCurrencyCodeText()));
			nameValue.add(new NameValueView("投资期限", detailDto.getTimeLimit()));
			if (productSale.getSaleStartDate() != null && productSale.getSaleEndDate() != null) {
				nameValue.add(
						new NameValueView("募集期", DateFormatUtils.format(productSale.getSaleStartDate(), "yyyy年MM月dd日")
								+ "-" + DateFormatUtils.format(productSale.getSaleEndDate(), "yyyy年MM月dd日")));
			}
			nameValue.add(new NameValueView("认购费", detailDto.getProBuyFee()));
			nameValue.add(new NameValueView("管理费", detailDto.getProManageFee()));
			viewItems.add(new ViewItem(1006, new PropertyListView(nameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("募集帐号", false)));

			List<NameValueView> bankNameValue = new ArrayList<>();
			bankNameValue.add(new NameValueView("开户名", productSale.getAccountName()));
			bankNameValue.add(new NameValueView("募集银行", productSale.getRaiseBank()));
			bankNameValue.add(new NameValueView("打款帐号", productSale.getPayAccount()));
			bankNameValue.add(new NameValueView("打款备注", productSale.getPayRemark()));

			viewItems.add(new ViewItem(1006, new PropertyListView(bankNameValue)));

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			// 一级标题
			viewItems.add(new ViewItem(1005, new Structure1005View("收益基准", false)));

			List<IncomeRuleView> incomeRuleViews = new ArrayList<>();

			if (incomeRules != null && !incomeRules.isEmpty()) {
				for (ProductIncomeRuleDto rule : incomeRules) {

					IncomeRuleView inRuleView = new IncomeRuleView();
					inRuleView.setAmountTitle("认购金额");
					inRuleView.setTimeLimitTitle("认购期限");
					inRuleView.setFixedRateTitle("收益率");
					inRuleView.setFloatRateTitle("浮动收益");
					inRuleView.setClassName(rule.getRuleName());

					List<IncomeValueView> incomeValuesViews = new ArrayList<>();
					inRuleView.setValues(incomeValuesViews);

					List<ProductIncomeDto> incomes = rule.getProductIncomeDtos();
					if (incomes == null || incomes.isEmpty()) {
						continue;
					}

					for (ProductIncomeDto income : incomes) {
						String buyTimeLimit = (income.getBuyTimeLimit() == null ? 0 : income.getBuyTimeLimit()) + "个月";

						incomeValuesViews.add(new IncomeValueView(
								income.getBuyMin() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMin())),
								income.getBuyMax() == null ? ""
										: NumberUtil.trimDoubleZero(String.valueOf(income.getBuyMax())),
								buyTimeLimit, income.getFixIncomeRate(), income.getFloatIncomeRate()));
					}

					incomeRuleViews.add(inRuleView);

				}
			}
			if (incomeRuleViews == null || incomeRuleViews.isEmpty()) {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无产品收益")));
			} else {
				viewItems.add(new ViewItem(1007, new Structure1007View(incomeRuleViews)));
			}

			viewItems.add(new ViewItem(2001, new DividingLineView()));

			Boolean isPass = permissionService
					.checkRead(DataObjectEnum.COMMISSION.getCode(), detailDto.getCreateUserId()).getResult();
			if (isPass) {
				// 一级标题
				viewItems.add(new ViewItem(1005, new Structure1005View("销售佣金", false)));

				// 获取销售佣金
				List<ProductCommissionRuleDto> commissionRuleDtos = detailDto.getCommissionDtos();

				List<CommissionRuleView> commissionRuleViews = new ArrayList<>();

				if (commissionRuleDtos != null && !commissionRuleDtos.isEmpty()) {
					for (ProductCommissionRuleDto rule : commissionRuleDtos) {

						CommissionRuleView ruleView = new CommissionRuleView();
						ruleView.setLeftTitle("销售金额");
						ruleView.setMiddleTitle("前端佣金");
						ruleView.setRightTitle("后端佣金");
						ruleView.setClassName(rule.getRuleName());
						ruleView.setBottomName("佣金备注");
						ruleView.setBottomValue(rule.getRemark());

						List<LeftMiddleRightView> leftMiddleRightViews = new ArrayList<>();
						ruleView.setValues(leftMiddleRightViews);

						List<ProductCommissionDto> commissionDtos = rule.getProductCommissionDtos();
						if (commissionDtos == null || commissionDtos.isEmpty()) {
							continue;
						}

						for (ProductCommissionDto commissionDto : commissionDtos) {
							StringBuilder builder = new StringBuilder();
							if (commissionDto.getSaleMin() != null) {
								builder.append(NumberUtil.trimDoubleZero(String.valueOf(commissionDto.getSaleMin())));
							}
							if (commissionDto.getSaleMax() != null) {
								if (builder.length() > 0) {
									builder.append("-");
								}
								builder.append(NumberUtil.trimDoubleZero(String.valueOf(commissionDto.getSaleMax())));
							}

							String buy = builder.toString() + "万";

							String middleValue = "", rightValue = "";
							Double front = commissionDto.getFrontCommission();
							if (front != null) {
								middleValue = NumberUtil.trimDoubleZero(String.valueOf(front)) + "%";
							}
							Double back = commissionDto.getBackCommission();
							if (back != null) {
								rightValue = NumberUtil.trimDoubleZero(String.valueOf(back)) + "%";
							}
							leftMiddleRightViews.add(new LeftMiddleRightView(buy, middleValue, rightValue));
						}

						commissionRuleViews.add(ruleView);

					}
				}
				if (commissionRuleViews == null || commissionRuleViews.isEmpty()) {
					viewItems.add(new ViewItem(2002, new Structure2002View("暂无销售佣金")));
				} else {
					viewItems.add(new ViewItem(1008, new Structure1008View(commissionRuleViews)));
				}

				viewItems.add(new ViewItem(2001, new DividingLineView()));

			}

			viewItems.add(new ViewItem(1005, new Structure1005View("项目附件", true)));
			/**
			 * 产品附件
			 */
			List<ProductAttachListResDto> attachDtos = detailDto.getAttachDtos();
			if (attachDtos != null && !attachDtos.isEmpty()) {
				int count = 0;
				for (ProductAttachListResDto attach : attachDtos) {
					String fileFormat = FileSupport.getFileFormat(attach.getUrl());
					if (StringUtils.isBlank(fileFormat)) {
						count++;
						logger.info("产品附件，ID为：%s，文件格式为：%s,手机端不支持。。。", attach.getId(), fileFormat);
						continue;
					}
					Structure1009View attachView = new Structure1009View();
					attachView.setTitle(attach.getSourceName());
					attachView.setFileType(fileFormat);
					attachView.setFileUrl(attach.getUrl());
					viewItems.add(new ViewItem(1009, attachView));
				}
				if (count == attachDtos.size()) {
					viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
				}
			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无项目附件")));
			}
			viewItems.add(new ViewItem(2001, new DividingLineView()));

			viewItems.add(new ViewItem(1005, new Structure1005View("信息披露", true)));

			List<ProductNoticeDto> noticeDtos = detailDto.getNoticeModels();
			if (noticeDtos != null && !noticeDtos.isEmpty()) {
				for (ProductNoticeDto notice : noticeDtos) {
					Structure1010View noticeView = new Structure1010View();
					noticeView.setTitle(notice.getTitle());
					noticeView.setType(notice.getNoticeTypeName());
					noticeView.setUpdateTime(DateFormatUtils.format(notice.getSendTime(), "MM月dd日"));
					String jumpUrl = Schema.PRODUCT_NOTICE_DETAIL
							+ String.format(Schema.PRODUCT_NOTICE_DETAIL_PARAMS, notice.getId(), "prodcutDetail");
					viewItems.add(new ViewItem(1010, jumpUrl, noticeView));
				}
			} else {
				viewItems.add(new ViewItem(2002, new Structure2002View("暂无信息披露")));
			}

		} else {
			return Result.newFailureResult(CodeConstant.CODE_1300023);
		}
		view.setViewItems(viewItems);

		return Result.newSuccessResult(view);
	}

	@Override
	public Result<ItemListDTO> search(String keyword, Integer page, Integer pageSize) throws BaseException {
		ProductBaseQueryReqDto reqDto = new ProductBaseQueryReqDto();
		reqDto.setName(keyword);
		reqDto.setPage(page);
		reqDto.setPageSize(pageSize);
		return this.getList(reqDto);
	}

	@Override
	public Result<SearchView> searchProductManagerByKeyword(String keyword) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("name", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);
		// 获取用户在各租户中详情信息
		Result<PaginationSupport<ProductManagerListResDto>> result = HttpUtils.get(TenantUrl.PRODUCT_GET_MANAGER_LIST,
				methodParams, new BaseTypeReference<Result<PaginationSupport<ProductManagerListResDto>>>() {
				});
		if (result == null || result.getResult() == null) {
			return Result.newFailureResult();
		}
		List<String> names = new ArrayList<>();
		PaginationSupport<ProductManagerListResDto> paSupport = result.getResult();
		if (paSupport != null) {
			List<ProductManagerListResDto> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (ProductManagerListResDto dto : dtos) {
					names.add(dto.getName());
				}
			}
		}
		return Result.newSuccessResult(new SearchView(names));

	}

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("name", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);
		String issuedStatuses = ProductIssuedStatusEnum.PRE_SAISE.getValue() + ","
				+ ProductIssuedStatusEnum.TO_RAISE.getValue();
		methodParams.put("issuedStatuses", issuedStatuses);// 只查询预售与募集中的产品
		// 获取用户在各租户中详情信息
		Result<PaginationSupport<ProductBaseResDto>> result = HttpUtils.get(TenantUrl.GET_LIST, methodParams,
				new BaseTypeReference<Result<PaginationSupport<ProductBaseResDto>>>() {
				});
		if (result == null || result.getResult() == null) {
			return Result.newFailureResult();
		}
		List<String> names = new ArrayList<>();
		PaginationSupport<ProductBaseResDto> paSupport = result.getResult();
		if (paSupport != null) {
			List<ProductBaseResDto> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (ProductBaseResDto dto : dtos) {
					names.add(dto.getName());
				}
			}
		}
		return Result.newSuccessResult(new SearchView(names));

	}

	@Override
	public Result<Integer> updateProductStatus(UpdateProductStatusReqDto dto) throws BaseException {
		// 重新提交或者取消申请不需要校验数据权限
		// CANCEL_APPLY_ON_LINE(2,"取消申请上线"),
		// AGAIN_APPLY_ON_LINE(4,"重置申请上线"),
		if (dto.getType() != ProductManagerActionEnum.CANCEL_APPLY_ON_LINE.getCode()
				&& dto.getType() != ProductManagerActionEnum.AGAIN_APPLY_ON_LINE.getCode()) {
			Boolean isPass = this.updatePermission(dto.getProductId(), DataObjectEnum.PRODUCT.getCode());
			if (!isPass) {
				throw new BaseException(CodeConstant.CODE_1220016);
			}
		}
		if (dto.getType() == ProductManagerActionEnum.CANCEL_APPLY_ON_LINE.getCode()) {
			if (StringUtils.isBlank(dto.getReason())) {
				throw new BaseException(CodeConstant.CODE_1300034);
			}
		}
		Map<String, Object> mapParmas = new HashMap<String, Object>();
		mapParmas.put("productId", dto.getProductId());
		mapParmas.put("productManagerAction", dto.getType());
		mapParmas.put("declaractionModel", dto.getDeclarationModel());
		mapParmas.put("reason", dto.getReason());
		return HttpUtils.post(TenantUrl.UPDATE_PRODUCT_STATUS, mapParmas, new BaseTypeReference<Result<Integer>>() {
		});

	}

	public Boolean updatePermission(Long id, String dataObjectCode) throws BaseException {
		List<Long> ownerIds = getReceiverAndAssistantId(id);
		if (BooleanUtils.isEmpty(ownerIds)) {
			Boolean isPass = permissionService.checkUpdate(dataObjectCode, null).getResult();
			return isPass;
		}
		for (Long ownerId : ownerIds) {
			Boolean isPass = permissionService.checkUpdate(dataObjectCode, ownerId).getResult();
			if (isPass) {
				return isPass;
			}
		}
		return false;
	}

	private List<Long> getReceiverAndAssistantId(Long id) throws BaseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Result<BaseProduct> result = HttpUtils.get(TenantUrl.GET_PRODUCT_BASE_INFO, map,
				new BaseTypeReference<Result<BaseProduct>>() {
				});
		if (!result.getSuccess()) {
			throw new BaseException(result.getCode(), result.getMessage());
		}
		BaseProduct base = result.getResult();
		List<Long> ownerIds = new ArrayList<Long>();
		if (base.getAssistantId() != null || base.getReceiverId() != null) {
			if (base.getAssistantId() != null) {
				ownerIds.add(base.getAssistantId());
			}
			if (base.getReceiverId() != null && !ownerIds.contains(base.getReceiverId())) {
				ownerIds.add(base.getReceiverId());
			}
		}
		return ownerIds;
	}
}
