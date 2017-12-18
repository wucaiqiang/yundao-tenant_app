
package com.yundao.tenant.app.service.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ListUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.DictionaryCodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.DataAndPermissionDto;
import com.yundao.tenant.app.dto.common.DataPermissionDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.LabelValueDto;
import com.yundao.tenant.app.dto.common.PermissionUrlDto;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.customer.CustomerContactDto;
import com.yundao.tenant.app.dto.customer.CustomerCredentialsDto;
import com.yundao.tenant.app.dto.customer.CustomerDetailResDto;
import com.yundao.tenant.app.dto.customer.CustomerInfoDto;
import com.yundao.tenant.app.dto.customer.CustomerInvestTypeDto;
import com.yundao.tenant.app.dto.customer.attach.CustomerAttachDto;
import com.yundao.tenant.app.dto.customer.opensea.CustomerOpenSeaPageReqDto;
import com.yundao.tenant.app.dto.customer.opensea.CustomerOpenSeaPageResDto;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.dto.fieldgroup.FieldGroupDto;
import com.yundao.tenant.app.dto.permission.PermissionMultiCodeDto;
import com.yundao.tenant.app.dto.tag.TagDto;
import com.yundao.tenant.app.enums.customer.CustomerAttachTypeEnum;
import com.yundao.tenant.app.enums.customer.CustomerListColumnEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.model.customer.bank.CustomerBankModel;
import com.yundao.tenant.app.service.customer.CustomerOpenSeaService;
import com.yundao.tenant.app.service.dictionary.DictionaryService;
import com.yundao.tenant.app.service.fieldgroup.FieldGroupService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.sale.declaration.DeclarationService;
import com.yundao.tenant.app.service.sale.reservation.ReservationService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.FormatContactUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.MobileUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.customer.CustomerBankView;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;
import com.yundao.tenant.app.view.customer.Structure3014View;

/**
 * Function: Reason: Date: 2017年9月20日 下午5:00:32
 * 
 * @author 欧阳利
 * @version
 */
@Service
public class CustomerOpenSeaServiceImpl implements CustomerOpenSeaService {

	private static Log log = LogFactory.getLog(CustomerOpenSeaServiceImpl.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private FieldGroupService fieldGroupService;

	@Override
	public Result<CustomerDetailView> getDetail(Long customerId) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", customerId);
		Result<CustomerDetailResDto> dtoResult = HttpUtils.get(TenantUrl.GET_CUSTOMER_DETAIL, methodMap,
				new BaseTypeReference<Result<CustomerDetailResDto>>() {
				});
		CustomerDetailView view = new CustomerDetailView();

		if (dtoResult != null && dtoResult.getResult() != null) {
			CustomerDetailResDto dto = dtoResult.getResult();

			view.setCustomerCode(dto.getNumber());
			// 客户基本信息
			DataAndPermissionDto<CustomerInfoDto> customerInfoDto = dto.getInfo();
			if (customerInfoDto != null) {
				CustomerInfoDto infoDto = customerInfoDto.getData();
				if (infoDto != null) {
					view.setGender(new NameIdView(infoDto.getSexText(), String.valueOf(infoDto.getSex())));
					view.setCustomerLevel(new NameIdView(infoDto.getLevelText(), String.valueOf(infoDto.getLevel())));
					view.setBirthday(infoDto.getBirthday());
					view.setFrom(new NameIdView(infoDto.getSourceText(), String.valueOf(infoDto.getSource())));
					view.setRemark(infoDto.getRemark());

					if (StringUtils.isNotBlank(infoDto.getProvinceCode())) {
						String province = dictionaryService.getRegionText(infoDto.getProvinceCode()).getResult();
						view.setProvince(new NameIdView(province, infoDto.getProvinceCode()));
					}
					if (StringUtils.isNotBlank(infoDto.getCityCode())) {
						String province = dictionaryService.getRegionText(infoDto.getCityCode()).getResult();
						view.setCity(new NameIdView(province, infoDto.getCityCode()));
					}
					view.setAddress(infoDto.getAddress());
					view.setIndustry(infoDto.getTrade());
					view.setOrganization(infoDto.getOrganization());
					view.setJob(infoDto.getPosition());
					/*
					 * // 客户证件 CustomerCredentialsDto credentialsDto =
					 * infoDto.getCredential(); if (credentialsDto != null) {
					 * view.setCertificateType( new
					 * NameIdView(credentialsDto.getTypeText(),
					 * String.valueOf(credentialsDto.getType())));
					 * view.setCertificateNumber(credentialsDto.getNumber());
					 * view.setIdentityFrontCardUrl(credentialsDto.getFront());
					 * view.setIdentityBackCardUrl(credentialsDto.getBack());
					 * 
					 * }
					 */

					// 资产证明
					List<String> assets = new ArrayList<>();
					List<CustomerAttachDto> attachDtos = infoDto.getAttachDtos();// 资产证明
					if (attachDtos != null && !attachDtos.isEmpty()) {
						for (CustomerAttachDto attachDto : attachDtos) {
							if (attachDto.getType() != null
									&& attachDto.getType() == CustomerAttachTypeEnum.ASSET.getType()) {
								assets.add(attachDto.getUrl());
							}
						}
					}
					view.setAssetsCertificateUrlList(assets);

					// 投资偏好
					List<NameIdView> investLikeList = new ArrayList<>();

					List<CustomerInvestTypeDto> investTypeDtos = infoDto.getInvestTypes();
					if (investTypeDtos != null && !investTypeDtos.isEmpty()) {
						for (CustomerInvestTypeDto investTypeDto : investTypeDtos) {
							investLikeList.add(new NameIdView(investTypeDto.getProductTypeIdText(),
									String.valueOf(investTypeDto.getProductTypeId())));
						}
						view.setInvestLikeSelectList(investLikeList);
					}

					// 投资人类型
					view.setInvestManType(new NameIdView(infoDto.getTypeText(), String.valueOf(infoDto.getType())));

					// 风险特征
					view.setRiskType(
							new NameIdView(infoDto.getRiskRatingText(), String.valueOf(infoDto.getRiskRating())));
					// 银行卡信息
					/*
					 * List<CustomerBankView> bankList = new ArrayList<>();
					 * 
					 * List<CustomerBankModel> bankModels = infoDto.getBanks();
					 * if (bankModels != null && !bankModels.isEmpty()) { for
					 * (CustomerBankModel model : bankModels) { CustomerBankView
					 * bankView = new CustomerBankView();
					 * bankView.setBankId(String.valueOf(model.getId()));
					 * bankView.setBankBackCardUrl(model.getBack());
					 * bankView.setBankFontCardUrl(model.getFront());
					 * bankView.setBankName(model.getBankName());
					 * bankView.setBankNumber(model.getAccount());
					 * 
					 * bankList.add(bankView); } view.setBankList(bankList); }
					 */
				}
			}

			// 客户联系信息
			view.setCustomerName(dto.getName());
			view.setMobile(FormatContactUtils.formatMobile(true, false, dto.getMobile()));
			DataAndPermissionDto<CustomerContactDto> contactDto = dto.getContact();
			if (contactDto != null) {
				CustomerContactDto contact = contactDto.getData();
				if (contact != null) {
					// 没有权限加密
					view.setWeChat("***");
					view.setQq("***");
					view.setMail("***");
				}
			}

			// 下拉列表项目
			String[] codes = new String[6];
			codes[0] = DictionaryCodeConstant.SEX;// 姓别
			codes[1] = DictionaryCodeConstant.CUSTOMER_LEVEL;// 客户级别
			codes[2] = DictionaryCodeConstant.CUSTOMER_SOURCE;// 客户来源
			codes[3] = DictionaryCodeConstant.CUSTOMER_CREDENTIALS;// 客户证件类型
			codes[4] = DictionaryCodeConstant.CUSTOMER_TYPE;// 客户投资类型
			codes[5] = DictionaryCodeConstant.CUSTOMER_RIST_RATING;// 客户风险特征
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
							if (dictionaryDto.getValue().equals(DictionaryCodeConstant.SEX)) {// 客户性别下拉选项
								view.setGenderList(idViews);
							} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_LEVEL)) {// 客户级别下拉选项
								view.setCustomerLevelList(idViews);
							} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_SOURCE)) {// 客户来源下拉选项
								view.setFromList(idViews);
							} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_CREDENTIALS)) {// 客户证件下拉选项
								view.setCertificateTypeList(idViews);
							} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_TYPE)) {// 客户投资类型
								view.setInvestManTypeList(idViews);
							} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_RIST_RATING)) {// 客户级风险特征
								view.setRiskTypeList(idViews);
							}
						}
					}
				}
			}

			// 投资偏好
			List<FieldGroupDto> fieldGroupDtos = fieldGroupService.getAll();
			if (fieldGroupDtos != null && !fieldGroupDtos.isEmpty()) {
				List<NameIdView> idViews = new ArrayList<>();
				for (FieldGroupDto fieldGroupDto : fieldGroupDtos) {
					idViews.add(new NameIdView(fieldGroupDto.getName(), String.valueOf(fieldGroupDto.getId())));
				}
				view.setInvestLikeList(idViews);
			}

		}

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<CustomerDetailHeaderView> getDetailHeader(Long customerId) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", customerId);
		Result<CustomerDetailResDto> dtoResult = HttpUtils.get(TenantUrl.GET_CUSTOMER_DETAIL, methodMap,
				new BaseTypeReference<Result<CustomerDetailResDto>>() {
				});

		CustomerDetailHeaderView view = new CustomerDetailHeaderView();

		if (dtoResult != null && dtoResult.getResult() != null) {
			CustomerDetailResDto dto = dtoResult.getResult();

			// 客户编辑，联系信息相关权限设置为false
			view.setReadCustomerPermission(true);
			view.setEditCustomerPermission(false);
			view.setReadCustomerContactInfoPermission(false);
			view.setEditCustomerContactInfoPermission(false);

			// 名称与手机号码加密
			view.setName(dto.getName());
			view.setMobile("");
			view.setMobileStr(FormatContactUtils.formatMobile(true, false, dto.getMobile()));

			DataAndPermissionDto<CustomerInfoDto> customerInfo = dto.getInfo();
			if (customerInfo != null) {
				CustomerInfoDto infoDto = customerInfo.getData();
				String levelText = "无";
				String riskRatingText = "无";
				if (infoDto != null) {

					if (StringUtils.isNotBlank(infoDto.getLevelText())) {
						levelText = infoDto.getLevelText();
					}
					view.setCustomerLevel(levelText);// 客户级别
					if (StringUtils.isNotBlank(infoDto.getRiskRatingText())) {
						riskRatingText = infoDto.getRiskRatingText();
					}
					view.setRiskAspect(riskRatingText);// 风险特征

					List<CustomerInvestTypeDto> investTypeDtos = infoDto.getInvestTypes();// 投资偏好
					if (investTypeDtos != null && !investTypeDtos.isEmpty()) {
						List<String> invests = new ArrayList<>();
						for (CustomerInvestTypeDto investTypeDto : investTypeDtos) {
							invests.add(investTypeDto.getProductTypeIdText());
						}
						view.setInvestLikeList(invests);
					}
				} else {
					view.setCustomerLevel(levelText);// 客户级别
					view.setRiskAspect(riskRatingText);// 风险特征
				}
			}

			// 客户标签
			List<TagDto> tagDtos = dto.getTags();
			List<NameIdView> nameIdViews = new ArrayList<>();
			if (tagDtos != null && !tagDtos.isEmpty()) {
				for (TagDto tag : tagDtos) {
					NameIdView idView = new NameIdView();
					idView.setId(tag.getId() + "");
					idView.setName(tag.getName());
					// BeanUtils.copyProperties(tag, idView);
					nameIdViews.add(idView);
				}
				view.setTagList(nameIdViews);
			}
			view.setHasFocus(dto.isFocus());
			view.setIsBelongTo(UserUtils.getUserId() == dto.getUserId());
			view.setAfpName(dto.getAfpName());

		}

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {
		if (StringUtils.isBlank(keyword)) {
			return Result.newResult(true);
		}

		CustomerOpenSeaPageReqDto dto = new CustomerOpenSeaPageReqDto();
		dto.setPageSize(Integer.MAX_VALUE);
		dto.setPage(1);

		Result<PaginationSupport<CustomerOpenSeaPageResDto>> result = getOpenSeaPage(dto);
		if (result == null) {
			return Result.newSuccessResult();
		}
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		if (result.getResult() == null) {
			return Result.newSuccessResult();
		}

		List<CustomerOpenSeaPageResDto> datas = result.getResult().getDatas();
		if (ListUtils.getSize(datas) == 0) {
			return Result.newSuccessResult();
		}
		List<String> keywordList = new ArrayList<>();
		for (CustomerOpenSeaPageResDto d : datas) {
			String customerName = d.getName();
			if (customerName.contains(keyword)) {
				keywordList.add(customerName);
			}
		}

		SearchView searchView = new SearchView(keywordList);

		return Result.newSuccessResult(searchView);

	}

	/**
	 * 获取公海分页数据
	 */
	public Result<PaginationSupport<CustomerOpenSeaPageResDto>> getOpenSeaPage(CustomerOpenSeaPageReqDto dto)
			throws BaseException {
		log.begin(dto);
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		if (StringUtils.isNotBlank(dto.getKeyword())) {
			methodParams.put("name", dto.getKeyword());
		}
		if (StringUtils.isNotBlank(dto.getLevel())) {
			methodParams.put("levels", dto.getLevel());
		}
		if (StringUtils.isNotBlank(dto.getInvestorTypes())) {
			methodParams.put("customerTypes", dto.getInvestorTypes());
		}
		if (StringUtils.isNotBlank(dto.getGender())) {
			methodParams.put("sexs", dto.getGender());
		}
		if (dto.getCreateDateBegin() != null) {
			methodParams.put("createDateBegin",
					DateFormatUtils.format(new Date(dto.getCreateDateBegin()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getCreateDateEnd() != null) {
			methodParams.put("createDateEnd",
					DateFormatUtils.format(new Date(dto.getCreateDateEnd()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getBirthDayBegin() != null) {
			methodParams.put("birthDayBegin",
					DateFormatUtils.format(new Date(dto.getBirthDayBegin()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getBirthDayEnd() != null) {
			methodParams.put("birthDayEnd",
					DateFormatUtils.format(new Date(dto.getBirthDayEnd()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		// 获取对客户资料的读取权限
		// Integer permission =
		// permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		// PermissionUrlDto urlDto = new PermissionUrlDto();
		// urlDto.setPersonalUrl(TenantUrl.GET_CUSTOMER_OPENSEA_PERSONAL);
		// urlDto.setDepartmentUrl(TenantUrl.GET_CUSTOMER_OPENSEA_DEPARTMENT);
		// urlDto.setPublicUrl(TenantUrl.GET_CUSTOMER_OPENSEA_PUBLIC);
		String url = TenantUrl.GET_CUSTOMER_OPENSEA_PUBLIC;
		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url))
			return Result.newSuccessResult(new PaginationSupport<CustomerOpenSeaPageResDto>());

		Result<PaginationSupport<CustomerOpenSeaPageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<CustomerOpenSeaPageResDto>>>() {
				});
		if (!result.getSuccess())
			return result;
		if (BooleanUtils.isEmpty(result.getResult().getDatas())) {
			return Result.newSuccessResult(new PaginationSupport<CustomerOpenSeaPageResDto>());
		}
		// return ResultAccess.newSuccessResultPage(result);
		return result;
	}

	/**
	 * 查询客户 getItemListDTO:
	 * 
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<ItemListDTO> getItemListDTO(CustomerOpenSeaPageReqDto reqDto) throws BaseException {
		Result<PaginationSupport<CustomerOpenSeaPageResDto>> result = getOpenSeaPage(reqDto);
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		PaginationSupport<CustomerOpenSeaPageResDto> pager = result.getResult();
		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<CustomerOpenSeaPageResDto> myCustomerPageResDtos = pager.getDatas();
		if (!BooleanUtils.isEmpty(myCustomerPageResDtos)) {
			for (CustomerOpenSeaPageResDto resDto : myCustomerPageResDtos) {
				String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, resDto.getId())
						+ "&from=1";
				Structure3014View view3014 = new Structure3014View();
				view3014.setCustomerId(resDto.getId() + "");
				view3014.setCustomerName(resDto.getName());
				view3014.setCustomerNo(resDto.getNumber());
				view3014.setCanGet(true);
				viewItems.add(new ViewItem(3014, jumpUrl, view3014));
			}
		}
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);
	}

}
