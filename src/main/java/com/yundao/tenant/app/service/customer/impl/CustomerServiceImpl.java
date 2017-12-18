
package com.yundao.tenant.app.service.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.MapUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.DictionaryCodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.DataAndPermissionDto;
import com.yundao.tenant.app.dto.common.DataPermissionDto;
import com.yundao.tenant.app.dto.common.FailListDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.LabelValueDto;
import com.yundao.tenant.app.dto.common.PassListDto;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.common.PermissionUrlDto;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.customer.CustomerAddDto;
import com.yundao.tenant.app.dto.customer.CustomerAddReqDto;
import com.yundao.tenant.app.dto.customer.CustomerContactDto;
import com.yundao.tenant.app.dto.customer.CustomerCredentialsDto;
import com.yundao.tenant.app.dto.customer.CustomerDetailResDto;
import com.yundao.tenant.app.dto.customer.CustomerInfoDto;
import com.yundao.tenant.app.dto.customer.CustomerInvestTypeDto;
import com.yundao.tenant.app.dto.customer.CustomerListQueryReqDto;
import com.yundao.tenant.app.dto.customer.CustomerSaleQueryReqDto;
import com.yundao.tenant.app.dto.customer.CustomerV2PageResDto;
import com.yundao.tenant.app.dto.customer.MyCustomerPageResDto;
import com.yundao.tenant.app.dto.customer.attach.CustomerAttachDto;
import com.yundao.tenant.app.dto.customer.bank.CustomerBankDto;
import com.yundao.tenant.app.dto.customer.bank.CustomerBankReqDto;
import com.yundao.tenant.app.dto.customer.tag.TagResDto;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.dto.fieldgroup.FieldGroupDto;
import com.yundao.tenant.app.dto.permission.PermissionMultiCodeDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationSelectListQueryDto;
import com.yundao.tenant.app.dto.sale.reservation.MyReservationListResDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSelectListQueryDto;
import com.yundao.tenant.app.dto.tag.TagDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.customer.CustomerAddWayEnum;
import com.yundao.tenant.app.enums.customer.CustomerAttachTypeEnum;
import com.yundao.tenant.app.enums.customer.CustomerListColumnEnum;
import com.yundao.tenant.app.enums.customer.CustomerListColumnV2Enum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.model.customer.BaseCustomer;
import com.yundao.tenant.app.model.customer.bank.CustomerBankModel;
import com.yundao.tenant.app.service.customer.CustomerService;
import com.yundao.tenant.app.service.customer.tag.TagService;
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
import com.yundao.tenant.app.view.TextView;
import com.yundao.tenant.app.view.customer.CustomerBankView;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;
import com.yundao.tenant.app.view.customer.CustomerImportBookView;
import com.yundao.tenant.app.view.customer.CustomerListColumnView;
import com.yundao.tenant.app.view.customer.CustomerQueryConditionView;
import com.yundao.tenant.app.view.customer.NameFailReasonView;
import com.yundao.tenant.app.view.customer.Structure3001View;
import com.yundao.tenant.app.view.customer.Structure3005View;
import com.yundao.tenant.app.view.customer.Structure3008View;
import com.yundao.tenant.app.view.dictionary.DictionaryItemsView;

/**
 * Function: Reason: Date: 2017年8月16日 下午3:36:07
 * 
 * @author wucq
 * @version
 */
@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {
	private Log logger = LogFactory.getLog(CustomerServiceImpl.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private FieldGroupService fieldGroupService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private DeclarationService declarationService;
	@Autowired
	private TagService tagService;

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("scope", CustomerListColumnV2Enum.ALL.getScope());
		methodParams.put("name", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);

		// 获取对客户资料的读取权限
		Integer permission = permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);
		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new SearchView());
		}
		Result<PaginationSupport<CustomerV2PageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<CustomerV2PageResDto>>>() {
				});
		

		if (result == null || result.getResult() == null) {
			return Result.newSuccessResult(new SearchView());
		}
		List<String> names = new ArrayList<>();
		PaginationSupport<CustomerV2PageResDto> paSupport = result.getResult();
		if (paSupport != null) {
			List<CustomerV2PageResDto> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (CustomerV2PageResDto dto : dtos) {
					names.add(dto.getName());
				}
			}
		}
		return Result.newSuccessResult(new SearchView(names));

	}

	@Override
	public Result<CustomerImportBookView> addFromBook(String customerList) throws Exception {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("customerList", customerList);
		Result<PermissionResultDto> result = HttpUtils.post(TenantUrl.CUSTOMER_ADD_FROM_BOOK, methodParams,
				new BaseTypeReference<Result<PermissionResultDto>>() {
				});
		CustomerImportBookView view = new CustomerImportBookView();
		if (result.getResult() == null) {
			logger.info("批量导入返回结果：" + JsonUtils.objectToJson(result));
			return Result.newFailureResult();
		}
		PermissionResultDto resultDto = result.getResult();
		if (resultDto.isSuccess()) {
			view.setResultType(1);
			view.setMessage("成功导入" + resultDto.getPassList().size() + "个客户");
		} else {
			List<PassListDto> passListDtos = resultDto.getPassList();
			List<FailListDto> failListDtos = resultDto.getFailList();
			if (passListDtos == null || passListDtos.isEmpty()) {
				if (failListDtos.size() == 1) {
					FailListDto failListDto = failListDtos.get(0);
					view.setResultType(3);
					view.setMessage(failListDto.getMessage());
					List<NameFailReasonView> customerFailList = new ArrayList<>();
					customerFailList.add(new NameFailReasonView(failListDto.getName(), failListDto.getMessage()));
					view.setCustomerFailList(customerFailList);
					return Result.newSuccessResult(view);
				}
			}

			List<NameFailReasonView> customerFailList = new ArrayList<>();
			view.setResultType(2);
			view.setMessage("成功导入" + passListDtos.size() + "个客户，失败" + failListDtos.size() + "个");
			for (FailListDto failListDto : failListDtos) {
				customerFailList.add(new NameFailReasonView(failListDto.getName(), failListDto.getMessage()));
			}
			view.setCustomerFailList(customerFailList);
		}

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<Map<String, Object>> add(CustomerAddReqDto reqDto) throws BaseException {

		CustomerAddDto dto = new CustomerAddDto();
		dto.setName(reqDto.getCustomerName());
		dto.setMobile(reqDto.getCustomerMobile());
		if (StringUtils.isNotBlank(reqDto.getCustomerGender())) {
			dto.setSex(NumberUtils.toInt(reqDto.getCustomerGender()));
		}
		if (StringUtils.isNotBlank(reqDto.getCustomerLevel())) {
			dto.setLevel(NumberUtils.toInt(reqDto.getCustomerLevel()));
		}
		if (StringUtils.isNotBlank(reqDto.getCompany())) {
			dto.setOrganization(reqDto.getCompany());
		}
		if (StringUtils.isNotBlank(reqDto.getJob())) {
			dto.setPosition(reqDto.getJob());
		}
		if (StringUtils.isNotBlank(reqDto.getCustomertags())) {
			List<NameIdView> tagList = JsonUtils.jsonToObject(reqDto.getCustomertags(),
					new BaseTypeReference<List<NameIdView>>() {
					});
			if (tagList != null && !tagList.isEmpty()) {
				StringBuilder tags = new StringBuilder();
				for (NameIdView nameIdView : tagList) {
					if (tags.length() > 0) {
						tags.append(",");
					}
					tags.append(nameIdView.getName());
				}
				dto.setTags(tags.toString());
			}
		}
		dto.setAddCustomerType(CustomerAddWayEnum.MYSELF.getType());// 默认自建客户
		dto.setRemark(reqDto.getRemark());

		Result<Long> result = HttpUtils.post(TenantUrl.CUSTOMER_ADD, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Long>>() {
				});

		Map<String, Object> map = new HashMap<>();
		if (result != null && result.getResult() != null) {
			map.put("customerId", String.valueOf(result.getResult()));
			return Result.newSuccessResult(map);
		}

		return Result.newFailureResult(result.getCode(), result.getMessage(), null);

	}

	@Override
	public Result<CustomerQueryConditionView> getQueryConditionV2() throws BaseException {

		CustomerQueryConditionView view = new CustomerQueryConditionView();
		List<NameIdView> typeView = new ArrayList<>();
		typeView.add(new NameIdView("全部", String.valueOf(1)));
		typeView.add(new NameIdView("我负责的", String.valueOf(2)));
		typeView.add(new NameIdView("未跟进的", String.valueOf(3)));
		typeView.add(new NameIdView("我关注的", String.valueOf(4)));
		typeView.add(new NameIdView("我部门的", String.valueOf(5)));
		view.setCustomerTypeList(typeView);
		// 是否成交集合
		List<NameIdView> bargainView = new ArrayList<>();
		bargainView.add(new NameIdView("全部", ""));
		bargainView.add(new NameIdView("是", String.valueOf(1)));
		bargainView.add(new NameIdView("否", String.valueOf(0)));
		view.setBargainList(bargainView);
		// 客户级别集合
		List<NameIdView> levelView = new ArrayList<>();
		levelView.add(new NameIdView("全部", ""));
		Result<DictionaryItemsView> levelResult = dictionaryService.getCustomerLevel();
		if (levelResult.getResult() != null) {
			DictionaryItemsView itemsView = levelResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				levelView.addAll(itemsView.getItems());
			}
		}
		view.setLevelList(levelView);
		// 投资偏好集合
		List<NameIdView> investView = new ArrayList<>();
		Result<DictionaryItemsView> investResult = dictionaryService.getCustomerInvest();
		if (investResult.getResult() != null) {
			DictionaryItemsView itemsView = investResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				investView.addAll(itemsView.getItems());
			}
		}
		view.setInvestLikeList(investView);
		// 风险特征集合
		List<NameIdView> riskView = new ArrayList<>();
		Result<DictionaryItemsView> riskResult = dictionaryService.getCustomerRiskRating();
		if (riskResult.getResult() != null) {
			DictionaryItemsView itemsView = riskResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				riskView.addAll(itemsView.getItems());
			}
		}

		view.setRiskList(riskView);

		String[] codes = new String[3];
		codes[0] = DictionaryCodeConstant.SEX;// 姓别
		codes[1] = DictionaryCodeConstant.CUSTOMER_TYPE;// 投资人类型
		codes[2] = DictionaryCodeConstant.CUSTOMER_SOURCE;// 客户来源
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
						} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_TYPE)) {// 投资人类型下拉选项
							view.setInvestorTypeList(idViews);
						} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_SOURCE)) {// 客户来源下拉选项
							view.setSourceList(idViews);
						}
					}
				}
			}
		}

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<CustomerQueryConditionView> getQueryCondition() throws BaseException {
		CustomerQueryConditionView view = new CustomerQueryConditionView();
		List<NameIdView> typeView = new ArrayList<>();
		typeView.add(new NameIdView("全部", String.valueOf(1)));
		typeView.add(new NameIdView("我关注的", String.valueOf(2)));
		typeView.add(new NameIdView("未跟进的", String.valueOf(3)));
		view.setCustomerTypeList(typeView);
		// 是否成交集合
		List<NameIdView> bargainView = new ArrayList<>();
		bargainView.add(new NameIdView("全部", ""));
		bargainView.add(new NameIdView("是", String.valueOf(1)));
		bargainView.add(new NameIdView("否", String.valueOf(0)));
		view.setBargainList(bargainView);
		// 客户级别集合
		List<NameIdView> levelView = new ArrayList<>();
		levelView.add(new NameIdView("全部", ""));
		Result<DictionaryItemsView> levelResult = dictionaryService.getCustomerLevel();
		if (levelResult.getResult() != null) {
			DictionaryItemsView itemsView = levelResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				levelView.addAll(itemsView.getItems());
			}
		}
		view.setLevelList(levelView);
		// 投资偏好集合
		List<NameIdView> investView = new ArrayList<>();
		Result<DictionaryItemsView> investResult = dictionaryService.getCustomerInvest();
		if (investResult.getResult() != null) {
			DictionaryItemsView itemsView = investResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				investView.addAll(itemsView.getItems());
			}
		}
		view.setInvestLikeList(investView);
		// 风险特征集合
		List<NameIdView> riskView = new ArrayList<>();
		Result<DictionaryItemsView> riskResult = dictionaryService.getCustomerRiskRating();
		if (riskResult.getResult() != null) {
			DictionaryItemsView itemsView = riskResult.getResult();
			if (itemsView.getItems() != null && !itemsView.getItems().isEmpty()) {
				riskView.addAll(itemsView.getItems());
			}
		}

		view.setRiskList(riskView);

		String[] codes = new String[3];
		codes[0] = DictionaryCodeConstant.SEX;// 姓别
		codes[1] = DictionaryCodeConstant.CUSTOMER_TYPE;// 投资人类型
		codes[2] = DictionaryCodeConstant.CUSTOMER_SOURCE;// 客户来源
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
						} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_TYPE)) {// 投资人类型下拉选项
							view.setInvestorTypeList(idViews);
						} else if (dictionaryDto.getValue().equals(DictionaryCodeConstant.CUSTOMER_SOURCE)) {// 客户来源下拉选项
							view.setSourceList(idViews);
						}
					}
				}
			}
		}

		return Result.newSuccessResult(view);

	}

	@Override
	public Result<CustomerListColumnView> getColumns() throws BaseException {
		List<NameIdView> nameIdViews = new ArrayList<>();
		nameIdViews.add(new NameIdView("全部", String.valueOf(1)));
		nameIdViews.add(new NameIdView("我关注的", String.valueOf(2)));
		nameIdViews.add(new NameIdView("未跟进的", String.valueOf(3)));
		return Result.newSuccessResult(new CustomerListColumnView(nameIdViews));
	}

	@Override
	public Result<CustomerListColumnView> getColumnsV2() throws BaseException {

		List<NameIdView> nameIdViews = new ArrayList<>();
		nameIdViews.add(new NameIdView("全部", String.valueOf(1)));
		nameIdViews.add(new NameIdView("我负责的", String.valueOf(2)));
		nameIdViews.add(new NameIdView("我未跟进的", String.valueOf(3)));
		nameIdViews.add(new NameIdView("我关注的", String.valueOf(4)));
		nameIdViews.add(new NameIdView("我部门的", String.valueOf(5)));
		return Result.newSuccessResult(new CustomerListColumnView(nameIdViews));

	}

	@Override
	public Result<Boolean> update(Map<String, String> params) throws BaseException {
		if (StringUtils.isBlank(params.get("customerId"))) {
			throw new BaseException(CodeConstant.CODE_1300016);
		}

		validateUpdatePermission(NumberUtils.toLong(params.get("customerId")));// 校验是否有修改数据权限

		Result<Boolean> result = HttpUtils.post(TenantUrl.CUSTOMER_UPDATE, MapUtils.cast(params),
				new BaseTypeReference<Result<Boolean>>() {
				});

		return result;
	}

	private void validateUpdatePermission(Long customerId) throws BaseException {
		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", customerId);
		Result<BaseCustomer> dtoResult = HttpUtils.get(TenantUrl.GET_CUSTOMER, methodMap,
				new BaseTypeReference<Result<BaseCustomer>>() {
				});
		if (dtoResult.getResult() == null) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}
		Result<Boolean> permissionResult = permissionService.checkUpdate(DataObjectEnum.DECLARATION.getCode(),
				dtoResult.getResult().getUserId());
		if (!permissionResult.getResult()) {
			throw new BaseException(CodeConstant.CODE_1220016);
		}
	}

	@Override
	public Result<Map<String, Object>> updateBank(CustomerBankReqDto reqDto) throws BaseException {
		if (reqDto.getCustomerId() == null) {
			throw new BaseException(CodeConstant.CODE_1300016);
		}

		validateUpdatePermission(reqDto.getCustomerId());// 校验是否有修改数据权限

		String url = "";
		if (reqDto.getBankId() == null) {
			url = TenantUrl.CUSTOMER_BANK_ADD;
		} else {
			url = TenantUrl.CUSTOMER_BANK_UPDATE;
		}

		CustomerBankDto bankDto = new CustomerBankDto();
		bankDto.setId(reqDto.getBankId());
		bankDto.setAccount(reqDto.getBankNumber());
		bankDto.setBack(reqDto.getBankBackCardUrl());
		bankDto.setBankName(reqDto.getBankName());
		bankDto.setCustomerId(reqDto.getCustomerId());
		bankDto.setFront(reqDto.getBankFontCardUrl());

		Result<String> result = HttpUtils.post(url, ArgsUtils.toMap(bankDto), new BaseTypeReference<Result<String>>() {
		});
		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		Map<String, Object> reMap = new HashMap<>();
		reMap.put("bankId", result.getResult());
		return Result.newSuccessResult(reMap);

	}

	@Override
	public Result<Boolean> validateMobile(String mobile, Long id) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", id);
		methodMap.put("mobile", mobile);
		Result<Boolean> dtoResult = HttpUtils.get(TenantUrl.CUSTOMER_VALIDATE_MOBILE, methodMap,
				new BaseTypeReference<Result<Boolean>>() {
				});

		return dtoResult;

	}

	@Override
	public Result<List<BaseCustomer>> getByMobileOrNumber(String mobileOrNumber) throws BaseException {
		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("mobileOrNumber", mobileOrNumber);
		Result<List<BaseCustomer>> dtoResult = HttpUtils.get(TenantUrl.CUSTOMER_GET_BY_MOBILE_OR_NUMBER, methodMap,
				new BaseTypeReference<Result<List<BaseCustomer>>>() {
				});
		return dtoResult;
	}

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

			Result<Boolean> read = permissionService.checkRead(DataObjectEnum.CUSTOMER.getCode(),
					dtoResult.getResult().getUserId());// 鉴权

			if (read == null || read.getResult() == null || !read.getSuccess() || !read.getResult()) {
				throw new BaseException(CodeConstant.CODE_1220015);
			}

			// 运行到这里，代表已经有客户读权限

			// 查询当前用户是否可读客户联系信息权限
			List<PermissionMultiCodeDto> reqDtos = new ArrayList<PermissionMultiCodeDto>(2);
			reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CONTACT.getCode(), dto.getUserId()));
			Result<List<DataPermissionDto>> peResult = permissionService.checkRead(reqDtos);
			boolean contactRead = false;// 客户联系信息权限
			if (peResult != null && peResult.getResult() != null) {
				List<DataPermissionDto> dataPermissionDtos = peResult.getResult();
				if (dataPermissionDtos != null && !dataPermissionDtos.isEmpty()) {
					DataPermissionDto permissionDto = dataPermissionDtos.get(0);
					contactRead = permissionDto.getReadPermission();
				}
			}

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
					// 客户证件
					CustomerCredentialsDto credentialsDto = infoDto.getCredential();
					if (credentialsDto != null) {
						view.setCertificateType(
								new NameIdView(credentialsDto.getTypeText(), String.valueOf(credentialsDto.getType())));
						view.setCertificateNumber(credentialsDto.getNumber());
						view.setIdentityFrontCardUrl(credentialsDto.getFront());
						view.setIdentityBackCardUrl(credentialsDto.getBack());

					}

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
					List<CustomerBankView> bankList = new ArrayList<>();

					List<CustomerBankModel> bankModels = infoDto.getBanks();
					if (bankModels != null && !bankModels.isEmpty()) {
						for (CustomerBankModel model : bankModels) {
							CustomerBankView bankView = new CustomerBankView();
							bankView.setBankId(String.valueOf(model.getId()));
							bankView.setBankBackCardUrl(model.getBack());
							bankView.setBankFontCardUrl(model.getFront());
							bankView.setBankName(model.getBankName());
							bankView.setBankNumber(model.getAccount());

							bankList.add(bankView);
						}
						view.setBankList(bankList);
					}

				}
			}

			// 客户联系信息
			view.setCustomerName(dto.getName());
			view.setMobile(FormatContactUtils.formatMobile(true, contactRead, dto.getMobile()));
			DataAndPermissionDto<CustomerContactDto> contactDto = dto.getContact();
			if (contactDto != null) {
				CustomerContactDto contact = contactDto.getData();
				if (contact != null) {
					if (contactRead) {// 有查询客户联系信息权限
						view.setWeChat(contact.getWechat());
						view.setQq(contact.getQq());
						view.setMail(contact.getEmail());
					} else {
						// 没有权限加密
						view.setWeChat("***");
						view.setQq("***");
						view.setMail("***");
					}
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
	public Result<CustomerDetailHeaderView> getDetailHeaderV2(Long customerId) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", customerId);
		Result<CustomerDetailResDto> dtoResult = HttpUtils.get(TenantUrl.GET_CUSTOMER_DETAIL, methodMap,
				new BaseTypeReference<Result<CustomerDetailResDto>>() {
				});

		CustomerDetailHeaderView view = new CustomerDetailHeaderView();

		if (dtoResult != null && dtoResult.getResult() != null) {
			CustomerDetailResDto dto = dtoResult.getResult();

			Result<Boolean> read = permissionService.checkRead(DataObjectEnum.CUSTOMER.getCode(),
					dtoResult.getResult().getUserId());// 鉴权

			if (read == null || read.getResult() == null || !read.getSuccess() || !read.getResult()) {
				throw new BaseException(CodeConstant.CODE_1220015);
			}
			// 运行到这里，代表已经有客户读权限

			// 查询当前用户是否可读客户联系信息权限
			boolean contactRead = false;
			List<PermissionMultiCodeDto> reqDtos = new ArrayList<PermissionMultiCodeDto>(2);
			reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CUSTOMER.getCode(), dto.getUserId()));
			reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CONTACT.getCode(), dto.getUserId()));
			Result<List<DataPermissionDto>> peResult = permissionService.checkRead(reqDtos);
			if (peResult != null && peResult.getResult() != null) {
				List<DataPermissionDto> dataPermissionDtos = peResult.getResult();
				if (dataPermissionDtos != null && !dataPermissionDtos.isEmpty()) {
					for (DataPermissionDto permissionDto : dataPermissionDtos) {
						if (DataObjectEnum.CONTACT.getCode().equals(permissionDto.getDataObjectCode())) {
							contactRead = permissionDto.getReadPermission();// 获取读权限

							view.setReadCustomerContactInfoPermission(contactRead);
							view.setEditCustomerContactInfoPermission(permissionDto.getEditPermission());

						} else if (DataObjectEnum.CUSTOMER.getCode().equals(permissionDto.getDataObjectCode())) {
							view.setReadCustomerPermission(permissionDto.getReadPermission());
							view.setEditCustomerPermission(permissionDto.getEditPermission());

						}

					}

				}
			}

			// 名称与手机号码加密
			view.setName(dto.getName());
			if (contactRead) {
				view.setMobile(dto.getMobile());
				view.setMobileStr(MobileUtils.format(dto.getMobile()));
			} else {
				view.setMobile("");
				view.setMobileStr(FormatContactUtils.formatMobile(true, contactRead, dto.getMobile()));
			}
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
	public Result<CustomerDetailHeaderView> getDetailHeader(Long customerId, Integer from) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("id", customerId);
		Result<CustomerDetailResDto> dtoResult = HttpUtils.get(TenantUrl.GET_CUSTOMER_DETAIL, methodMap,
				new BaseTypeReference<Result<CustomerDetailResDto>>() {
				});

		CustomerDetailHeaderView view = new CustomerDetailHeaderView();

		if (dtoResult != null && dtoResult.getResult() != null) {
			CustomerDetailResDto dto = dtoResult.getResult();
			boolean contactRead = false;

			if (from != null && from == 1) {// from==1代表由客户公海进入的客户详情
				// 客户编辑，联系信息相关权限设置为false
				view.setReadCustomerPermission(true);
				view.setEditCustomerPermission(false);
				view.setReadCustomerContactInfoPermission(false);
				view.setEditCustomerContactInfoPermission(false);
			} else {
				Result<Boolean> read = permissionService.checkRead(DataObjectEnum.CUSTOMER.getCode(),
						dtoResult.getResult().getUserId());// 鉴权

				if (read == null || read.getResult() == null || !read.getSuccess() || !read.getResult()) {
					throw new BaseException(CodeConstant.CODE_1220015);
				}

				// 运行到这里，代表已经有客户读权限

				// 查询当前用户是否可读客户联系信息权限
				List<PermissionMultiCodeDto> reqDtos = new ArrayList<PermissionMultiCodeDto>(2);
				reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CUSTOMER.getCode(), dto.getUserId()));
				reqDtos.add(new PermissionMultiCodeDto(DataObjectEnum.CONTACT.getCode(), dto.getUserId()));
				Result<List<DataPermissionDto>> peResult = permissionService.checkRead(reqDtos);
				if (peResult != null && peResult.getResult() != null) {
					List<DataPermissionDto> dataPermissionDtos = peResult.getResult();
					if (dataPermissionDtos != null && !dataPermissionDtos.isEmpty()) {
						for (DataPermissionDto permissionDto : dataPermissionDtos) {
							if (DataObjectEnum.CONTACT.getCode().equals(permissionDto.getDataObjectCode())) {
								contactRead = permissionDto.getReadPermission();// 获取读权限

								view.setReadCustomerContactInfoPermission(contactRead);
								view.setEditCustomerContactInfoPermission(permissionDto.getEditPermission());

							} else if (DataObjectEnum.CUSTOMER.getCode().equals(permissionDto.getDataObjectCode())) {
								view.setReadCustomerPermission(permissionDto.getReadPermission());
								view.setEditCustomerPermission(permissionDto.getEditPermission());

							}

						}

					}
				}
			}

			// 名称与手机号码加密
			view.setName(dto.getName());
			if (contactRead) {
				view.setMobile(dto.getMobile());
				view.setMobileStr(MobileUtils.format(dto.getMobile()));
			} else {
				view.setMobile("");
				view.setMobileStr(FormatContactUtils.formatMobile(true, contactRead, dto.getMobile()));
			}
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
	public Result<ItemListDTO> getSearchPage(BasePageDto dto, String keyword) throws BaseException {

		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		methodParams.put("scope", CustomerListColumnV2Enum.MY.getScope());
		if (StringUtils.isNotBlank(keyword)) {
			methodParams.put("name", keyword);
		}

		// 获取对客户资料的读取权限
		Integer permission = permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);
		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new ItemListDTO());
		}
		Result<PaginationSupport<CustomerV2PageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<CustomerV2PageResDto>>>() {
				});

		// 读取客户联系方式权限
		Integer contactPermission = permissionService.getRead(DataObjectEnum.CONTACT.getCode()).getResult();
		Boolean isCustomerPermission = true; // 执行到这一步，代码已经拥有客户读取权限
		// 只要权限不是0（NONE），就是可以查看
		Boolean isContactPermission = !DataObjectPermissionEnum.NONE.getValue().equals(contactPermission);

		result.getResult().getDatas().forEach(m -> {
			m.setMobile(FormatContactUtils.formatMobile(isCustomerPermission, isContactPermission, m.getMobile()));
			// m.setName(FormatContactUtils.formatName(isCustomerPermission,
			// isContactPermission, m.getName()));
		});
		PaginationSupport<CustomerV2PageResDto> pager = result.getResult();

		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<CustomerV2PageResDto> myCustomerPageResDtos = pager.getDatas();
		if (myCustomerPageResDtos != null && !myCustomerPageResDtos.isEmpty()) {
			if (StringUtils.isBlank(keyword) && dto.getPage() == 1) {
				viewItems.add(new ViewItem(3002, new TextView("我的客户")));
			}
			for (CustomerV2PageResDto resDto : myCustomerPageResDtos) {
				String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, resDto.getId());
				Structure3001View view3001 = new Structure3001View();
				view3001.setCustomerId(resDto.getId() + "");
				view3001.setMobile(resDto.getMobile());
				view3001.setName(resDto.getName());
				view3001.setMobileText(MobileUtils.format(resDto.getMobile()));
				viewItems.add(new ViewItem(3001, jumpUrl, view3001));
			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getMyPageSearch(BasePageDto reqDto, String keyword) throws BaseException {

		Map<String, Object> methodParams = ArgsUtils.toMap(reqDto);
		methodParams.put("scope", CustomerListColumnV2Enum.ALL.getScope());
		methodParams.put("name", keyword);

		// 获取对客户资料的读取权限
		Integer permission = permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);
		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new ItemListDTO());
		}

		Result<PaginationSupport<CustomerV2PageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<CustomerV2PageResDto>>>() {
				});

		// 读取客户联系方式权限
		Integer contactPermission = permissionService.getRead(DataObjectEnum.CONTACT.getCode()).getResult();
		Boolean isCustomerPermission = true; // 执行到这一步，代码已经拥有客户读取权限
		// 只要权限不是0（NONE），就是可以查看
		Boolean isContactPermission = !DataObjectPermissionEnum.NONE.getValue().equals(contactPermission);

		result.getResult().getDatas().forEach(m -> {
			m.setMobile(FormatContactUtils.formatMobile(isCustomerPermission, isContactPermission, m.getMobile()));
			m.setName(FormatContactUtils.formatName(isCustomerPermission, isContactPermission, m.getName()));
		});
		PaginationSupport<CustomerV2PageResDto> pager = result.getResult();

		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<CustomerV2PageResDto> myCustomerPageResDtos = pager.getDatas();
		if (myCustomerPageResDtos != null && !myCustomerPageResDtos.isEmpty()) {
			for (CustomerV2PageResDto resDto : myCustomerPageResDtos) {
				String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, resDto.getId());
				Structure3008View view3008 = new Structure3008View();
				view3008.setCustomerId(resDto.getId() + "");
				view3008.setCustomerMobile(resDto.getMobile());
				view3008.setCustomerName(resDto.getName());
				view3008.setBottomText(MobileUtils.format(resDto.getMobile()));
				view3008.setIsConcern(resDto.getFocus());
				viewItems.add(new ViewItem(3008, jumpUrl, view3008));
			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getSalePageForOpenSea(CustomerSaleQueryReqDto dto) throws BaseException {

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		if (StringUtils.isNotBlank(dto.getFilter()) && "bill".equals(dto.getFilter())) {
			DeclarationSelectListQueryDto queryDto = new DeclarationSelectListQueryDto();
			BeanUtils.copyProperties(dto, queryDto);
			Result<PaginationSupport<DeclarationDto>> result = declarationService.getsByCustomerId(queryDto);
			if (result != null && result.getResult() != null) {
				PaginationSupport<DeclarationDto> pager = result.getResult();
				itemListDTO.setPaginaton(pager);
				List<DeclarationDto> declarationDtos = pager.getDatas();
				if (declarationDtos != null && !declarationDtos.isEmpty()) {
					for (DeclarationDto declarationDto : declarationDtos) {
						Structure3005View view3005 = new Structure3005View();
						view3005.setFrom("[报单]");
						view3005.setProductName(declarationDto.getProductName());
						view3005.setDate(declarationDto.getPayDate());
						view3005.setLeftName("预约金额");
						view3005.setLeftValue(String.valueOf(declarationDto.getDealAmount().intValue()) + "万");
						view3005.setMiddleName("创建人");
						view3005.setMiddleValue(declarationDto.getCreateUserName());
						view3005.setRightName("状态");
						view3005.setRightValue(declarationDto.getStatusName());
						String jumpUrl = Schema.DECLARATION_DETAIL
								+ String.format(Schema.DECLARATION_DETAIL_PARAMS, declarationDto.getId());
						viewItems.add(new ViewItem(3005, jumpUrl, view3005));
					}
				}
			}
		} else {
			ReservationSelectListQueryDto queryDto = new ReservationSelectListQueryDto();
			BeanUtils.copyProperties(dto, queryDto);
			Result<PaginationSupport<MyReservationListResDto>> result = reservationService.getsByCustomerId(queryDto);
			if (result != null && result.getResult() != null) {
				PaginationSupport<MyReservationListResDto> pager = result.getResult();
				itemListDTO.setPaginaton(pager);

				List<MyReservationListResDto> reservationDtos = pager.getDatas();
				if (reservationDtos != null && !reservationDtos.isEmpty()) {
					for (MyReservationListResDto reservationDto : reservationDtos) {
						Structure3005View view3005 = new Structure3005View();
						view3005.setFrom("[预约]");
						view3005.setProductName(reservationDto.getProductName());
						view3005.setDate(reservationDto.getEstimatePayDate());
						view3005.setLeftName("预约金额");
						view3005.setLeftValue(String.valueOf(reservationDto.getReservationAmount().intValue()) + "万");
						view3005.setMiddleName("创建人");
						view3005.setMiddleValue(reservationDto.getCreateRealName());
						view3005.setRightName("状态");
						view3005.setRightValue(reservationDto.getStatusText());
						String jumpUrl = Schema.RESERVATION_DETAIL
								+ String.format(Schema.RESERVATION_DETAIL_PARAMS, reservationDto.getId());
						viewItems.add(new ViewItem(3005, jumpUrl, view3005));
					}
				}

			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getSalePage(CustomerSaleQueryReqDto dto) throws BaseException {

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		if (StringUtils.isNotBlank(dto.getFilter()) && "bill".equals(dto.getFilter())) {
			DeclarationSelectListQueryDto queryDto = new DeclarationSelectListQueryDto();
			BeanUtils.copyProperties(dto, queryDto);
			Result<PaginationSupport<DeclarationDto>> result = declarationService.getsByCustomerId(queryDto);
			if (result != null && result.getResult() != null) {
				PaginationSupport<DeclarationDto> pager = result.getResult();
				itemListDTO.setPaginaton(pager);
				List<DeclarationDto> declarationDtos = pager.getDatas();
				if (declarationDtos != null && !declarationDtos.isEmpty()) {
					for (DeclarationDto declarationDto : declarationDtos) {
						Structure3005View view3005 = new Structure3005View();
						view3005.setFrom("[报单]");
						view3005.setProductName(declarationDto.getProductName());
						view3005.setDate(declarationDto.getPayDate());
						view3005.setLeftName("预约金额");
						view3005.setLeftValue(String.valueOf(declarationDto.getDealAmount().intValue()) + "万");
						view3005.setMiddleName("创建人");
						view3005.setMiddleValue(declarationDto.getCreateUserName());
						view3005.setRightName("状态");
						view3005.setRightValue(declarationDto.getStatusName());
						String jumpUrl = Schema.DECLARATION_DETAIL
								+ String.format(Schema.DECLARATION_DETAIL_PARAMS, declarationDto.getId());
						viewItems.add(new ViewItem(3005, jumpUrl, view3005));
					}
				}
			}
		} else {
			ReservationSelectListQueryDto queryDto = new ReservationSelectListQueryDto();
			BeanUtils.copyProperties(dto, queryDto);
			Result<PaginationSupport<MyReservationListResDto>> result = reservationService.getsByCustomerId(queryDto);
			if (result != null && result.getResult() != null) {
				PaginationSupport<MyReservationListResDto> pager = result.getResult();
				itemListDTO.setPaginaton(pager);

				List<MyReservationListResDto> reservationDtos = pager.getDatas();
				if (reservationDtos != null && !reservationDtos.isEmpty()) {
					for (MyReservationListResDto reservationDto : reservationDtos) {
						Structure3005View view3005 = new Structure3005View();
						view3005.setFrom("[预约]");
						view3005.setProductName(reservationDto.getProductName());
						view3005.setDate(reservationDto.getEstimatePayDate());
						view3005.setLeftName("预约金额");
						view3005.setLeftValue(String.valueOf(reservationDto.getReservationAmount().intValue()) + "万");
						view3005.setMiddleName("创建人");
						view3005.setMiddleValue(reservationDto.getCreateRealName());
						view3005.setRightName("状态");
						view3005.setRightValue(reservationDto.getStatusText());
						String jumpUrl = Schema.RESERVATION_DETAIL
								+ String.format(Schema.RESERVATION_DETAIL_PARAMS, reservationDto.getId());
						viewItems.add(new ViewItem(3005, jumpUrl, view3005));
					}
				}

			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getMyPageV3(CustomerListQueryReqDto dto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		if (StringUtils.isBlank(dto.getCustomerType())) {
			methodParams.put("scope", CustomerListColumnV2Enum.ALL.getScope());
		} else {
			CustomerListColumnV2Enum columnEnum = CustomerListColumnV2Enum.getEnum(dto.getCustomerType());
			if (columnEnum == null) {
				throw new BaseException(CodeConstant.CODE_1300019);
			}
			methodParams.put("scope", columnEnum.getScope());
		}
		if (StringUtils.isNotBlank(dto.getLevel())) {
			methodParams.put("levels", dto.getLevel());
		}
		if (StringUtils.isNotBlank(dto.getTagNames())) {
			methodParams.put("tags", dto.getTagNames());
		}
		if (StringUtils.isNotBlank(dto.getInvests())) {
			methodParams.put("investTypes", dto.getInvests());
		}
		if (StringUtils.isNotBlank(dto.getInvestorTypes())) {
			methodParams.put("typies", dto.getInvestorTypes());
		}
		if (StringUtils.isNotBlank(dto.getGender())) {
			methodParams.put("sexs", dto.getGender());
		}
		if (dto.getDistributionDateBegin() != null) {
			methodParams.put("distributionDateBegin",
					DateFormatUtils.format(new Date(dto.getDistributionDateBegin()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getDistributionDateEnd() != null) {
			methodParams.put("distributionDateEnd",
					DateFormatUtils.format(new Date(dto.getDistributionDateEnd()), "yyyy-MM-dd HH:mm:ss:SSS"));
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
		Integer permission = permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_CUSTOMER_V2_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);
		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new ItemListDTO());
		}
		Result<PaginationSupport<CustomerV2PageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<CustomerV2PageResDto>>>() {
				});

		// 读取客户联系方式权限
		Integer contactPermission = permissionService.getRead(DataObjectEnum.CONTACT.getCode()).getResult();
		Boolean isCustomerPermission = true; // 执行到这一步，代码已经拥有客户读取权限
		// 只要权限不是0（NONE），就是可以查看
		Boolean isContactPermission = !DataObjectPermissionEnum.NONE.getValue().equals(contactPermission);

		result.getResult().getDatas().forEach(m -> {
			m.setMobile(FormatContactUtils.formatMobile(isCustomerPermission, isContactPermission, m.getMobile()));
		});
		PaginationSupport<CustomerV2PageResDto> pager = result.getResult();

		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<CustomerV2PageResDto> myCustomerPageResDtos = pager.getDatas();
		if (myCustomerPageResDtos != null && !myCustomerPageResDtos.isEmpty()) {
			for (CustomerV2PageResDto resDto : myCustomerPageResDtos) {
				String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, resDto.getId());
				Structure3008View view3008 = new Structure3008View();
				view3008.setCustomerId(resDto.getId() + "");
				view3008.setCustomerMobile(resDto.getMobile());
				view3008.setCustomerName(resDto.getName());
				view3008.setBottomText("客户编号：" + resDto.getNumber());
				view3008.setIsConcern(resDto.getFocus());
				view3008.setIsBelongTo(UserUtils.getUserId() == resDto.getFpId());
				if (resDto.getTags() != null && !resDto.getTags().isEmpty()) {
					List<TagResDto> tagResDtos = resDto.getTags();
					List<String> tagNames = new ArrayList<>();
					for (TagResDto tag : tagResDtos) {
						tagNames.add(tag.getName());
					}
					view3008.setTags(tagNames);
				}
				viewItems.add(new ViewItem(3008, jumpUrl, view3008));
			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getMyPage(CustomerListQueryReqDto dto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		if (StringUtils.isBlank(dto.getCustomerType())) {
			methodParams.put("scope", CustomerListColumnEnum.ALL.getScope());
		} else {
			CustomerListColumnEnum columnEnum = CustomerListColumnEnum.getEnum(dto.getCustomerType());
			if (columnEnum == null) {
				throw new BaseException(CodeConstant.CODE_1300019);
			}
			methodParams.put("scope", columnEnum.getScope());
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
		if (dto.getDistributionDateBegin() != null) {
			methodParams.put("distributionDateBegin",
					DateFormatUtils.format(new Date(dto.getDistributionDateBegin()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getDistributionDateEnd() != null) {
			methodParams.put("distributionDateEnd",
					DateFormatUtils.format(new Date(dto.getDistributionDateEnd()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getBirthDayBegin() != null) {
			methodParams.put("birthDayBegin",
					DateFormatUtils.format(new Date(dto.getBirthDayBegin()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}
		if (dto.getBirthDayEnd() != null) {
			methodParams.put("birthDayEnd",
					DateFormatUtils.format(new Date(dto.getBirthDayEnd()), "yyyy-MM-dd HH:mm:ss:SSS"));
		}

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.CUSTOMER.getCode()).getResult();
		if (DataObjectPermissionEnum.NONE.getValue().equals(permission)) {
			// 表示没有任何权限,直接返回
			return Result.newSuccessResult(new ItemListDTO());
		}
		Result<PaginationSupport<MyCustomerPageResDto>> result = HttpUtils.get(TenantUrl.GET_CUSTOMER_MY_PAGE,
				methodParams, new BaseTypeReference<Result<PaginationSupport<MyCustomerPageResDto>>>() {
				});
		// 读取客户联系方式权限
		Integer contactPermission = permissionService.getRead(DataObjectEnum.CONTACT.getCode()).getResult();
		Boolean isCustomerPermission = true; // 执行到这一步，代码已经拥有客户读取权限
		// 只要权限不是0（NONE），就是可以查看
		Boolean isContactPermission = !DataObjectPermissionEnum.NONE.getValue().equals(contactPermission);

		result.getResult().getDatas().forEach(m -> {
			m.setMobile(FormatContactUtils.formatMobile(isCustomerPermission, isContactPermission, m.getMobile()));
			// m.setName(FormatContactUtils.formatName(isCustomerPermission,
			// isContactPermission, m.getName()));
		});
		PaginationSupport<MyCustomerPageResDto> pager = result.getResult();

		ItemListDTO itemListDTO = new ItemListDTO(pager);
		List<ViewItem> viewItems = new ArrayList<>();
		List<MyCustomerPageResDto> myCustomerPageResDtos = pager.getDatas();
		if (myCustomerPageResDtos != null && !myCustomerPageResDtos.isEmpty()) {
			for (MyCustomerPageResDto resDto : myCustomerPageResDtos) {
				String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, resDto.getId());
				Structure3008View view3008 = new Structure3008View();
				view3008.setCustomerId(resDto.getId() + "");
				view3008.setCustomerMobile(resDto.getMobile());
				view3008.setCustomerName(resDto.getName());
				view3008.setBottomText("客户编号：" + resDto.getNumber());
				view3008.setIsConcern(resDto.getFocus());
				viewItems.add(new ViewItem(3008, jumpUrl, view3008));
			}
		}
		itemListDTO.setViewItems(viewItems);

		return Result.newSuccessResult(itemListDTO);

	}
}
