
package com.yundao.tenant.app.service.sale.declaration.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.MapUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.DictionaryCodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.DataPermissionDto;
import com.yundao.tenant.app.dto.common.FileDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.LabelValueDto;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.dto.permission.PermissionMultiCodeDto;
import com.yundao.tenant.app.dto.roadshow.BaseVideoTranscode;
import com.yundao.tenant.app.dto.roadshow.VideoDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddAndResubmitReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddDirectReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddInReservationReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddResDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationApplyRefundReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationBankReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationPageReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationResDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationSelectListQueryDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationUpdateBankDto;
import com.yundao.tenant.app.dto.sale.reservation.BaseReservationDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.customer.CustomerCredentialsEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.enums.roadshow.VideoTranscodeDefinitionEnum;
import com.yundao.tenant.app.enums.sale.declaration.RefundStatusEnum;
import com.yundao.tenant.app.enums.workflow.DeclarationStatusEnum;
import com.yundao.tenant.app.model.customer.bank.CustomerBankModel;
import com.yundao.tenant.app.model.customer.credential.CustomerCredentialsModel;
import com.yundao.tenant.app.model.sale.declaration.BaseDeclarationCompliance;
import com.yundao.tenant.app.model.sale.declaration.BaseDeclarationRefund;
import com.yundao.tenant.app.model.sale.declaration.BaseReservation;
import com.yundao.tenant.app.service.dictionary.DictionaryService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.sale.declaration.DeclarationService;
import com.yundao.tenant.app.util.AESUtils;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.customer.CustomerBankView;
import com.yundao.tenant.app.view.sale.declaration.ComplianceView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationCustomerView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationDetailHeaderView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationListColumnView;
import com.yundao.tenant.app.view.sale.declaration.Structure4003View;
import com.yundao.tenant.app.view.sale.declaration.SubscribeView;
import com.yundao.tenant.app.view.sale.declaration.VideoView;

/**
 * Function: Reason: Date: 2017年9月2日 下午1:54:29
 * 
 * @author wucq
 * @version
 */
@Service
public class DeclarationServiceImpl extends AbstractService implements DeclarationService {
	private Log logger = LogFactory.getLog(DeclarationServiceImpl.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public Result<DeclarationAddResDto> addDirect(DeclarationAddDirectReqDto reqDto) throws BaseException {

		Map<String, Object> params = new HashMap<>();
		params.put("productId", reqDto.getProductId());
		params.put("customerId", reqDto.getCustomerId());
		if (reqDto.getGiveAmountDate() != null) {
			params.put("payDate", DateFormatUtils.format(new Date(reqDto.getGiveAmountDate()), "yyyy-MM-dd HH:mm:ss"));
		}
		params.put("dealAmount", NumberUtils.toDouble(reqDto.getRenGouAmount()));
		params.put("remark", reqDto.getRemark());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_ADD, params, new BaseTypeReference<Result<Long>>() {
		});
		if (!result.getSuccess()) {
			return Result.newResult(result.getCode(), result.getMessage(), null);
		}
		if (result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300024);
		}

		DeclarationAddResDto dto = new DeclarationAddResDto();
		dto.setText("前往完善资料");
		String jumpUrl = Schema.DECLARATION_DETAIL
				+ String.format(Schema.DECLARATION_DETAIL_PARAMS, result.getResult());

		dto.setAction(new ActionDTO(jumpUrl));
		return Result.newSuccessResult(dto);

	}

	@Override
	public Result<DeclarationAddResDto> addPatch(DeclarationAddDirectReqDto reqDto) throws BaseException {

		Map<String, Object> params = new HashMap<>();
		params.put("productId", reqDto.getProductId());
		params.put("customerId", reqDto.getCustomerId());
		if (reqDto.getGiveAmountDate() != null) {
			params.put("payDate", DateFormatUtils.format(new Date(reqDto.getGiveAmountDate()), "yyyy-MM-dd HH:mm:ss"));
		}
		params.put("dealAmount", NumberUtils.toDouble(reqDto.getRenGouAmount()));
		params.put("remark", reqDto.getRemark());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_PATCH_ADD, params,
				new BaseTypeReference<Result<Long>>() {
				});
		if (!result.getSuccess()) {
			return Result.newResult(result.getCode(), result.getMessage(), null);
		}
		if (result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300024);
		}

		DeclarationAddResDto dto = new DeclarationAddResDto();
		dto.setText("前往完善资料");
		String jumpUrl = Schema.DECLARATION_DETAIL
				+ String.format(Schema.DECLARATION_DETAIL_PARAMS, result.getResult());

		dto.setAction(new ActionDTO(jumpUrl));
		return Result.newSuccessResult(dto);

	}

	@Override
	public Result<DeclarationAddResDto> add(DeclarationAddAndResubmitReqDto reqDto) throws BaseException {
		Map<String, Object> reservationMap = new HashMap<>();
		reservationMap.put("id", reqDto.getAppointId());
		Result<BaseReservationDto> reservation = HttpUtils.get(TenantUrl.RESERVATION_GET, reservationMap,
				new BaseTypeReference<Result<BaseReservationDto>>() {
				});
		if (reservation.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300030);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("productId", reservation.getResult().getProductId());
		params.put("customerId", reservation.getResult().getCustomerId());
		params.put("reservationId", reqDto.getAppointId());
		if (reqDto.getGiveAmountDate() != null) {
			params.put("payDate", DateFormatUtils.format(new Date(reqDto.getGiveAmountDate()), "yyyy-MM-dd HH:mm:ss"));
		}
		params.put("dealAmount", NumberUtils.toDouble(reqDto.getRenGouAmount()));
		params.put("remark", reqDto.getRemark());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_ADD, params, new BaseTypeReference<Result<Long>>() {
		});
		if (!result.getSuccess()) {
			return Result.newResult(result.getCode(), result.getMessage(), null);
		}
		if (result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300024);
		}

		DeclarationAddResDto dto = new DeclarationAddResDto();
		dto.setText("前往完善资料");
		String jumpUrl = Schema.DECLARATION_DETAIL
				+ String.format(Schema.DECLARATION_DETAIL_PARAMS, result.getResult());

		dto.setAction(new ActionDTO(jumpUrl));
		return Result.newSuccessResult(dto);

	}

	@Override
	public Result<DeclarationAddResDto> addInReservation(DeclarationAddInReservationReqDto reqDto)
			throws BaseException {

		Map<String, Object> params = new HashMap<>();
		params.put("reservationId", reqDto.getAppointId());
		if (reqDto.getGiveAmountDate() != null) {
			params.put("payDate", DateFormatUtils.format(new Date(reqDto.getGiveAmountDate()), "yyyy-MM-dd HH:mm:ss"));
		}
		params.put("dealAmount", NumberUtils.toDouble(reqDto.getRenGouAmount()));
		params.put("remark", reqDto.getRemark());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_ADD_IN_RESERVATION, params,
				new BaseTypeReference<Result<Long>>() {
				});
		if (!result.getSuccess()) {
			return Result.newResult(result.getCode(), result.getMessage(), null);
		}
		if (result.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1300024);
		}

		DeclarationAddResDto dto = new DeclarationAddResDto();
		dto.setText("前往完善资料");
		String jumpUrl = Schema.DECLARATION_DETAIL
				+ String.format(Schema.DECLARATION_DETAIL_PARAMS, result.getResult());

		dto.setAction(new ActionDTO(jumpUrl));
		return Result.newSuccessResult(dto);

	}

	@Override
	public Result<Long> submit(Long id) throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_SUBMIT, params,
				new BaseTypeReference<Result<Long>>() {
				});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage() + "不能为空", null);
		}

		return result;

	}

	@Override
	public Result<Long> resubmit(DeclarationAddAndResubmitReqDto reqDto) throws BaseException {

		Map<String, Object> params = new HashMap<>();
		params.put("id", reqDto.getOrderId());
		if (reqDto.getGiveAmountDate() != null) {
			params.put("payDate", DateFormatUtils.format(new Date(reqDto.getGiveAmountDate()), "yyyy-MM-dd HH:mm:ss"));
		}
		params.put("dealAmount", NumberUtils.toDouble(reqDto.getRenGouAmount()));
		params.put("remark", reqDto.getRemark());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_RESUBMIT, params,
				new BaseTypeReference<Result<Long>>() {
				});

		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage() + "不能为空", null);
		}
		return result;

	}

	@Override
	public Result<Boolean> update(Map<String, String> params) throws BaseException {

		if (StringUtils.isBlank(params.get("orderId"))) {
			throw new BaseException(CodeConstant.CODE_1300022);
		}
		validateUpdatePermission(NumberUtils.toLong(params.get("orderId")));

		Result<Boolean> result = HttpUtils.post(TenantUrl.DECLARATION_UPDATE, MapUtils.cast(params),
				new BaseTypeReference<Result<Boolean>>() {
				});

		return result;

	}

	@Override
	public Result<Long> doCancel(Long orderId, String reason) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", orderId);
		methodParams.put("reason", reason);
		return HttpUtils.post(TenantUrl.DECLARATION_CANCEL, methodParams, new BaseTypeReference<Result<Long>>() {
		});
	}

	@Override
	public Result<ItemListDTO> getSearchPage(BasePageDto pageDto, String keyword) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(pageDto);
		if (StringUtils.isNotBlank(keyword)) {
			methodParams.put("keyword", keyword);
		}
		Result<PaginationSupport<DeclarationDto>> result = HttpUtils.get(TenantUrl.DECLARATION_SEARCH, methodParams,
				new BaseTypeReference<Result<PaginationSupport<DeclarationDto>>>() {
				});

		ItemListDTO itemListDTO = new ItemListDTO();

		if (result != null && result.getResult() != null) {
			PaginationSupport<DeclarationDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);

			if (pager != null) {
				List<ViewItem> viewItems = new ArrayList<>();
				List<DeclarationDto> declarationDtos = pager.getDatas();
				for (DeclarationDto dto : declarationDtos) {
					Structure4003View view4003 = new Structure4003View();
					view4003.setOrderId(String.valueOf(dto.getId()));
					view4003.setCustomerName(dto.getCustomerName());
					view4003.setProductName(dto.getProductName());
					view4003.setStateText(dto.getStatusName());
					view4003.setLeftName("认购金额");
					view4003.setLeftValue(
							String.valueOf(dto.getDealAmount() == null ? 0 : dto.getDealAmount().intValue()));
					view4003.setRightName("打款日期");
					view4003.setRightValue(dto.getPayDate());
					view4003.setOrderDateText(dto.getCreateDate());
					view4003.setOrderCertificateText("打款凭证");
					view4003.setOrderCertificateState(dto.getHasPayEvidence() == 1 ? true : false);
					String jumpUrl = "";
					viewItems.add(new ViewItem(4003, jumpUrl, view4003));
				}
				itemListDTO.setViewItems(viewItems);
			}
		}

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<Map<String, Object>> updateBank(DeclarationBankReqDto bankReqDto) throws BaseException {

		if (bankReqDto.getOrderId() == null) {
			throw new BaseException(CodeConstant.CODE_1300022);
		}

		validateUpdatePermission(bankReqDto.getOrderId());// 校验是否有修改数据权限

		String url = TenantUrl.DECLARATION_UPDATE_BANK;

		DeclarationUpdateBankDto bankDto = new DeclarationUpdateBankDto();
		bankDto.setId(bankReqDto.getOrderId());
		bankDto.setAccount(bankReqDto.getBankNumber());
		bankDto.setBankName(bankReqDto.getBankName());
		bankDto.setBack(bankReqDto.getBankBackCardUrl());
		bankDto.setFront(bankReqDto.getBankFontCardUrl());

		Result<String> result = HttpUtils.post(url, ArgsUtils.toMap(bankDto), new BaseTypeReference<Result<String>>() {
		});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		Map<String, Object> reMap = new HashMap<>();
		reMap.put("bankId", result.getResult());
		return Result.newSuccessResult(reMap);

	}

	private void validateUpdatePermission(Long declarationId) throws BaseException {
		Result<DeclarationResDto> declarationResDtoResult = HttpUtils.get(TenantUrl.DECLARATION_GET,
				ArgsUtils.toIdMap(declarationId), new BaseTypeReference<Result<DeclarationResDto>>() {
				});
		if (declarationResDtoResult.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}
		Result<Boolean> permissionResult = permissionService.checkUpdate(DataObjectEnum.DECLARATION.getCode(),
				declarationResDtoResult.getResult().getCreateUserId());
		if (!permissionResult.getResult()) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}
	}

	@Override
	public Result<DeclarationListColumnView> getColumns() throws BaseException {
		List<NameIdView> nameIdViews = new ArrayList<>();
		nameIdViews.add(new NameIdView("全部", ""));
		nameIdViews.add(new NameIdView("未提交", String.valueOf(0)));
		nameIdViews.add(new NameIdView("待审批", String.valueOf(1)));
		nameIdViews.add(new NameIdView("已通过", String.valueOf(2)));
		nameIdViews.add(new NameIdView("已驳回", String.valueOf(3)));
		nameIdViews.add(new NameIdView("已取消", String.valueOf(4)));
		nameIdViews.add(new NameIdView("已退款", String.valueOf(6)));
		return Result.newSuccessResult(new DeclarationListColumnView(nameIdViews));

	}

	private DeclarationResDto getById(Long declarationId) throws BaseException {
		Result<DeclarationResDto> declarationResDtoResult = HttpUtils.get(TenantUrl.DECLARATION_GET,
				ArgsUtils.toIdMap(declarationId), new BaseTypeReference<Result<DeclarationResDto>>() {
				});
		if (declarationResDtoResult != null && declarationResDtoResult.getResult() != null) {
			DeclarationResDto dto = declarationResDtoResult.getResult();

			// 数据权限校验
			Result<Boolean> read = permissionService.checkRead(DataObjectEnum.DECLARATION.getCode(), dto.getUserId());// 鉴权

			if (read == null || read.getResult() == null || !read.getSuccess() || !read.getResult()) {
				throw new BaseException(CodeConstant.CODE_1220015);
			}
			return dto;

		}
		return null;
	}

	@Override
	public Result<Long> refundCancel(DeclarationApplyRefundReqDto dto) throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("declarationId", dto.getId());
		params.put("reason", dto.getReason());
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_REFUND_CANCEL_BY_DECLARATION_ID, params,
				new BaseTypeReference<Result<Long>>() {
				});

		return result;

	}

	@Override
	public Result<Long> applyRefund(DeclarationApplyRefundReqDto dto) throws BaseException {

		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_APPLY_REFUND, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Long>>() {
				});

		return result;

	}

	@Override
	public Result<Long> resubmitRefund(Long declarationId) throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("declarationId", declarationId);
		Result<Long> result = HttpUtils.post(TenantUrl.DECLARATION_RESUBMIT_REFUND, params,
				new BaseTypeReference<Result<Long>>() {
				});

		return result;

	}

	@Override
	public Result<ComplianceView> getCompliance(Long declarationId) throws BaseException {
		ComplianceView view = new ComplianceView();
		DeclarationResDto dto = this.getById(declarationId);
		if (dto != null) {
			// 投资者基本信息表(自然人)
			List<BaseDeclarationCompliance> baseInfos = dto.getBaseInfo();
			if (baseInfos != null && !baseInfos.isEmpty()) {
				List<String> urls = new ArrayList<>();
				for (BaseDeclarationCompliance compliance : baseInfos) {
					urls.add(compliance.getUrl());
				}
				view.setInvestorBaseInfoFormUrlList(urls);
			}
			// 投资者风险匹配告知书及投资者确认函
			List<BaseDeclarationCompliance> riskNotifys = dto.getRiskNotify();
			if (riskNotifys != null && !riskNotifys.isEmpty()) {
				List<String> urls = new ArrayList<>();
				for (BaseDeclarationCompliance compliance : riskNotifys) {
					urls.add(compliance.getUrl());
				}
				view.setInvestorRiskConfirmationUrlList(urls);
			}
			// 风险承受能力调查问卷(自然人)
			List<BaseDeclarationCompliance> riskQuesses = dto.getRiskQuesstionnaire();
			if (riskQuesses != null && !riskQuesses.isEmpty()) {
				List<String> urls = new ArrayList<>();
				for (BaseDeclarationCompliance compliance : riskQuesses) {
					urls.add(compliance.getUrl());
				}
				view.setRiskToleranceQuestionnaireUrlList(urls);
			}
			// 基金回访确认书
			List<BaseDeclarationCompliance> confirmation = dto.getReturnConfirmation();
			if (confirmation != null && !confirmation.isEmpty()) {
				List<String> urls = new ArrayList<>();
				for (BaseDeclarationCompliance compliance : confirmation) {
					urls.add(compliance.getUrl());
				}
				view.setFundCallbackUrlList(urls);
			}
			// 其他合规文件
			List<BaseDeclarationCompliance> others = dto.getOtherFile();
			if (others != null && !others.isEmpty()) {
				List<String> urls = new ArrayList<>();
				for (BaseDeclarationCompliance compliance : others) {
					urls.add(compliance.getUrl());
				}
				view.setOtherContractUrlList(urls);
			}
			List<VideoDto> records = dto.getComplianceRecord();
			if (records != null && !records.isEmpty()) {
				VideoView videoView = new VideoView();
				for (VideoDto videoDto : records) {
					videoView.setCoverUrl(videoDto.getCoverUrl());
					List<BaseVideoTranscode> transcodes = videoDto.getBaseVideoTranscodes();
					String url = "";
					if (transcodes != null && !transcodes.isEmpty()) {
						for (BaseVideoTranscode transcode : transcodes) {
							if (VideoTranscodeDefinitionEnum.HIGH.getValue().equals(transcode.getDefinition())) {
								url = transcode.getUrl();
							}
						}
					} else {
						url = videoDto.getSourceUrl();
					}
					if (StringUtils.isNotBlank(url)) {
						try {
							videoView.setVideoUrl(AESUtils.encrypt(url));
						} catch (Exception e) {
							logger.error("视频url加密出现异常，异常信息为：", e);
						}
					}
				}
				view.setOrderComplianceMultimediaFile(videoView);
			}
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<SubscribeView> getSubscribe(Long declarationId) throws BaseException {
		SubscribeView view = new SubscribeView();

		DeclarationResDto dto = this.getById(declarationId);
		if (dto != null) {
			view.setRenGouAmountText(String.valueOf(dto.getDealAmount().intValue()) + "万");
			view.setRenGouAmount(String.valueOf(dto.getDealAmount().intValue()));
			view.setGiveAmountDate(dto.getPayDate());
			List<FileDto> vouchers = dto.getVoucher();
			if (vouchers != null && !vouchers.isEmpty()) {
				List<String> voucherUrls = new ArrayList<>();
				for (FileDto file : vouchers) {
					voucherUrls.add(file.getFileUrl());
				}
				view.setGiveAmountCertificateUrlList(voucherUrls);
			}
			List<FileDto> contracts = dto.getContractFile();
			if (contracts != null && !contracts.isEmpty()) {
				List<String> contractUrls = new ArrayList<>();
				for (FileDto contract : contracts) {
					contractUrls.add(contract.getFileUrl());
				}
				view.setContractAttachUrlList(contractUrls);
			}
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<Map<String, Object>> getBank(Long declarationId) throws BaseException {

		Map<String, Object> result = new HashMap<>();
		CustomerBankView view = new CustomerBankView();

		DeclarationResDto dto = this.getById(declarationId);

		if (dto != null) {
			CustomerBankModel model = dto.getCustomerBank();
			if (model != null) {
				view.setBankId(String.valueOf(model.getId().intValue()));
				view.setBankName(model.getBankName());
				view.setBankNumber(model.getAccount());
				view.setBankFontCardUrl(model.getFront());
				view.setBankBackCardUrl(model.getBack());
			}
		}
		result.put("bank", view);
		return Result.newSuccessResult(result);

	}

	@Override
	public Result<DeclarationCustomerView> getCustomerDetail(Long declarationId) throws BaseException {

		DeclarationCustomerView view = new DeclarationCustomerView();

		DeclarationResDto dto = this.getById(declarationId);

		if (dto != null) {
			view.setCustomerName(dto.getCustomerName());
			view.setDoneOrderManagerName(dto.getUserName());

			// 客户证件
			CustomerCredentialsModel credentialsModel = dto.getCustomerCredentials();
			if (credentialsModel != null) {
				if (credentialsModel.getType() != null) {
					view.setCertificateType(
							new NameIdView(CustomerCredentialsEnum.getEnumName(credentialsModel.getType().intValue()),
									String.valueOf(credentialsModel.getType())));
				}
				view.setCertificateNumber(credentialsModel.getNumber());
				view.setIdentityFrontCardUrl(credentialsModel.getFront());
				view.setIdentityBackCardUrl(credentialsModel.getBack());
			}
			// 证件类型集合
			// 下拉列表项目
			String[] codes = new String[1];
			codes[0] = DictionaryCodeConstant.CUSTOMER_CREDENTIALS;// 客户证件类型
			Result<List<DictionaryDto>> dictionaryDtos = dictionaryService.gets(StringUtils.join(codes, ","));
			if (dictionaryDtos != null) {
				List<DictionaryDto> dictDtos = dictionaryDtos.getResult();
				if (dictDtos != null && !dictDtos.isEmpty()) {
					for (DictionaryDto dictionaryDto : dictDtos) {
						List<LabelValueDto> labelValueDtos = dictionaryDto.getSelections();
						if (labelValueDtos != null && !labelValueDtos.isEmpty()) {
							List<NameIdView> idViews = new ArrayList<>();
							for (LabelValueDto labelValueDto : labelValueDtos) {
								idViews.add(new NameIdView(labelValueDto.getLabel(), labelValueDto.getValue()));
							}
							if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_CREDENTIALS)) {// 客户证件下拉选项
								view.setCertificateTypeList(idViews);
							}
						}
					}
				}
			}

			// 客户资产证明
			List<FileDto> aFileDtos = dto.getDeclarationAttachs();
			if (aFileDtos != null && !aFileDtos.isEmpty()) {
				List<String> assets = new ArrayList<>();
				for (FileDto attachDto : aFileDtos) {
					assets.add(attachDto.getFileUrl());
				}
				view.setAssetsCertificateUrlList(assets);
			}

		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<DeclarationDetailHeaderView> getDetailHeader(Long declarationId) throws BaseException {

		DeclarationDetailHeaderView resultData = new DeclarationDetailHeaderView();

		DeclarationResDto dto = this.getById(declarationId);
		// 运行到这里，代表已经有报单读权限

		if (dto != null) {
			// 查询当前用户是否可读客户联系信息权限
			List<PermissionMultiCodeDto> reqDtos = new ArrayList<PermissionMultiCodeDto>(2);
			reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CUSTOMER.getCode(), dto.getUserId()));
			reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.DECLARATION.getCode(), dto.getUserId()));
			Result<List<DataPermissionDto>> peResult = permissionService.checkRead(reqDtos);

			if (peResult != null && peResult.getResult() != null) {
				List<DataPermissionDto> dataPermissionDtos = peResult.getResult();
				if (dataPermissionDtos != null && !dataPermissionDtos.isEmpty()) {
					for (DataPermissionDto permissionDto : dataPermissionDtos) {
						if (DataObjectEnum.CUSTOMER.getCode().equals(permissionDto.getDataObjectCode())) {
							resultData.setCustomerReadPermission(permissionDto.getReadPermission());
							resultData.setCustomerEditPermission(permissionDto.getEditPermission());
						} else if (DataObjectEnum.DECLARATION.getCode().equals(permissionDto.getDataObjectCode())) {
							resultData.setOrderEditPermission(permissionDto.getEditPermission());
						}
					}
				}
			}
			resultData.setOrderNOText(dto.getNumber());
			resultData.setRenGouAmountText(
					String.valueOf(dto.getDealAmount() == null ? 0 : dto.getDealAmount().intValue()) + "万");
			resultData.setStateText(dto.getStatusText());
			resultData.setState(dto.getStatus());
			resultData.setCustomerName(dto.getCustomerName());
			resultData.setProductName(dto.getProductName());
			BaseReservation reservation = dto.getReservation();
			if (reservation != null) {
				resultData.setAppointAmountText(
						(reservation.getReservationAmount() == null ? 0 : reservation.getReservationAmount().intValue())
								+ "万");
			}
			// 产品详情动作
			String jumpUrl = Schema.PRODUCT_DETAIL
					+ String.format(Schema.PRODUCT_DETAIL_PARAMS, dto.getProductId(), dto.getProductName());

			resultData.setProductAction(new ActionDTO(jumpUrl));

			resultData.setCancelReson(dto.getReason());

			// 设置申请退款中的状态值
			if (dto.getStatus() == DeclarationStatusEnum.PASS.getValue()) {
				// 查询该报单是否在申请退款中
				Map<String, Object> refundMap = new HashMap<>();
				refundMap.put("declarationId", declarationId);
				Result<List<BaseDeclarationRefund>> refundResult = HttpUtils.get(TenantUrl.REFUND_GET_BY_DECLARATION_ID,
						refundMap, new BaseTypeReference<Result<List<BaseDeclarationRefund>>>() {
						});
				if (refundResult.getResult() != null && !refundResult.getResult().isEmpty()) {
					resultData.setApplyRefundStatus(0);
					BaseDeclarationRefund refund = refundResult.getResult().get(0);
					if (StringUtils.isNotBlank(refund.getReason())) {
						// 取最新退款原因
						resultData.setApplyRefundReason(refund.getReason());
					}
					if (refund.getStatus() != null && (refund.getStatus() == RefundStatusEnum.APPROVALING.getValue())) {
						resultData.setApplyRefundStatus(1);
						if (refund.getUserId() != null) {
							resultData.setCanCancelRefund(UserUtils.getUserId() == refund.getUserId());
						}
					} else if (refund.getStatus() != null
							&& (refund.getStatus() == RefundStatusEnum.REJECT.getValue())) {
						resultData.setApplyRefundStatus(2);
						resultData.setApplyRefundRejectReason(refund.getAuditReason());
						if (refund.getUserId() != null) {
							resultData.setCanCancelRefund(UserUtils.getUserId() == refund.getUserId());
						}
					} 
				}
			}
		}
		return Result.newSuccessResult(resultData);

	}

	@Override
	public Result<PaginationSupport<DeclarationDto>> getsByCustomerId(DeclarationSelectListQueryDto reqDto)
			throws BaseException {
		Map<String, Object> params = ArgsUtils.toMap(reqDto);
		Result<PaginationSupport<DeclarationDto>> result = HttpUtils.get(TenantUrl.CUSTOMER_SALE_GET_DECLARATION,
				params, new BaseTypeReference<Result<PaginationSupport<DeclarationDto>>>() {
				});

		return result;

	}

	@Override
	public Result<ItemListDTO> getPage(DeclarationPageReqDto declarationPageReqDto) throws BaseException {

		Map<String, Object> params = ArgsUtils.toMap(declarationPageReqDto);
		if (StringUtils.isNotBlank(declarationPageReqDto.getFilterType())) {
			params.put("statuss", declarationPageReqDto.getFilterType());
		}
		Result<PaginationSupport<DeclarationDto>> result = HttpUtils.get(TenantUrl.DECLARATION_GET_PAGE, params,
				new BaseTypeReference<Result<PaginationSupport<DeclarationDto>>>() {
				});

		ItemListDTO itemListDTO = new ItemListDTO();

		if (result != null && result.getResult() != null) {
			PaginationSupport<DeclarationDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);

			if (pager != null && pager.getDatas() != null && !pager.getDatas().isEmpty()) {
				List<ViewItem> viewItems = new ArrayList<>();
				List<DeclarationDto> declarationDtos = pager.getDatas();
				for (DeclarationDto dto : declarationDtos) {
					Structure4003View view4003 = new Structure4003View();
					view4003.setOrderId(String.valueOf(dto.getId()));
					view4003.setCustomerName(dto.getCustomerName());
					view4003.setProductName(dto.getProductName());
					view4003.setStateText(dto.getStatusName());
					view4003.setLeftName("认购金额");
					view4003.setLeftValue(
							dto.getDealAmount() == null ? "0" : String.valueOf(dto.getDealAmount().intValue()) + "万");
					view4003.setRightName("打款日期");
					view4003.setRightValue(dto.getPayDate());
					view4003.setOrderDateText(dto.getCreateDate());
					view4003.setOrderCertificateText("打款凭证");
					view4003.setOrderCertificateState(
							(dto.getHasPayEvidence() != null && dto.getHasPayEvidence() == 1) ? true : false);
					String jumpUrl = Schema.DECLARATION_DETAIL
							+ String.format(Schema.DECLARATION_DETAIL_PARAMS, dto.getId());
					viewItems.add(new ViewItem(4003, jumpUrl, view4003));
				}
				itemListDTO.setViewItems(viewItems);
			}
		}

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.DECLARATION.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new SearchView());
		}

		List<String> names = new ArrayList<>();

		// 按产品名来搜索
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("productName", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);
		Result<PaginationSupport<DeclarationDto>> productNameResult = HttpUtils.get(TenantUrl.DECLARATION_GET_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<DeclarationDto>>>() {
				});

		if (productNameResult == null || productNameResult.getResult() == null) {
			return Result.newSuccessResult(new SearchView());
		}
		PaginationSupport<DeclarationDto> paSupport = productNameResult.getResult();
		if (paSupport != null) {
			List<DeclarationDto> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (DeclarationDto dto : dtos) {
					names.add(dto.getProductName());
				}
			}
		}

		// 按客户名来搜索
		methodParams = new HashMap<>();
		methodParams.put("customerName", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);
		Result<PaginationSupport<DeclarationDto>> customerNameResult = HttpUtils.get(TenantUrl.DECLARATION_GET_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<DeclarationDto>>>() {
				});

		if (customerNameResult == null || customerNameResult.getResult() == null) {
			return Result.newSuccessResult(new SearchView());
		}
		PaginationSupport<DeclarationDto> paginationSupport = customerNameResult.getResult();
		if (paginationSupport != null) {
			List<DeclarationDto> dtos = paginationSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (DeclarationDto dto : dtos) {
					names.add(dto.getCustomerName());
				}
			}
		}

		return Result.newSuccessResult(new SearchView(names));

	}

}
