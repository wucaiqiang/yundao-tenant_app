
package com.yundao.tenant.app.service.sale.leads.impl;

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
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.LabelValueDto;
import com.yundao.tenant.app.dto.common.PermissionUrlDto;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.customer.MyCustomerPageResDto;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotToFpReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsPageReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsPageResDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsQueryReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsRemindResDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsRemindUnAllotResDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsResDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsSearchReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsUpdateStatusReqDto;
import com.yundao.tenant.app.dto.user.visit.UserVisitDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.customer.CustomerListColumnEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.enums.sale.leads.LeadsStatusEnum;
import com.yundao.tenant.app.service.dictionary.DictionaryService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.sale.leads.LeadsService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.NameValueView;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.sale.leads.LeadsQueryConditionView;
import com.yundao.tenant.app.view.sale.leads.Structure3013View;
import com.yundao.tenant.app.view.sale.leads.Structure3018View;

/**
 * Function: Reason: Date: 2017年9月11日 下午3:19:35
 * 
 * @author wucq
 * @version
 */
@Service
public class LeadsServiceImpl extends AbstractService implements LeadsService {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("customerName", keyword);
		methodParams.put("currentPage", 1);
		methodParams.put("pageSize", Integer.MAX_VALUE);

		// 获取读取的权限
		Integer permission = permissionService.getRead(DataObjectEnum.LEADS.getCode()).getResult();

		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_LEADS_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_LEADS_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_LEADS_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);

		// 没有对应的url，表示没有权限，直接返回
		if (BooleanUtils.isEmpty(url)) {
			return Result.newSuccessResult(new SearchView());
		}
		Result<PaginationSupport<LeadsPageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<LeadsPageResDto>>>() {
				});

		if (result == null || result.getResult() == null) {
			return Result.newSuccessResult(new SearchView());
		}

		List<String> names = new ArrayList<>();
		PaginationSupport<LeadsPageResDto> paSupport = result.getResult();
		if (paSupport != null) {
			List<LeadsPageResDto> dtos = paSupport.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (LeadsPageResDto dto : dtos) {
					names.add(dto.getCustomerName());
				}
			}
		}
		return Result.newSuccessResult(new SearchView(names));

	}

	@Override
	public Result<Boolean> doAllot(LeadsAllotReqDto dto) throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("customerId", dto.getCustomerId());
		Result<List<LeadsRemindUnAllotResDto>> unallotResult = HttpUtils.get(TenantUrl.GET_LEADS_UN_ALLOT, params,
				new BaseTypeReference<Result<List<LeadsRemindUnAllotResDto>>>() {
				});

		if (unallotResult.getResult() == null || unallotResult.getResult().isEmpty()) {
			throw new BaseException(CodeConstant.CODE_1300041);
		}

		List<LeadsRemindUnAllotResDto> unAllotResDtos = unallotResult.getResult();
		StringBuilder leadIds = new StringBuilder();
		for (LeadsRemindUnAllotResDto allotResDto : unAllotResDtos) {
			if (leadIds.length() > 0) {
				leadIds.append(",");
			}
			leadIds.append(allotResDto.getId());
		}
		dto.setLeadsIds(leadIds.toString());// 设置线索IDss

		Result<Boolean> result = HttpUtils.post(TenantUrl.ALLOT_LEADS, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Boolean>>() {
				});

		return result;

	}

	@Override
	public Result<Boolean> allotToFp(LeadsAllotToFpReqDto dto) throws BaseException {
		return HttpUtils.post(TenantUrl.ALLOT_LEADS_TO_FP, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Boolean>>() {
				});
	}

	@Override
	public Result<LeadsQueryConditionView> getQueryCondition() throws BaseException {

		LeadsQueryConditionView view = new LeadsQueryConditionView();
		// 分类名称
		List<NameIdView> operationList = new ArrayList<>();
		operationList.add(new NameIdView("全部线索", "all"));
		operationList.add(new NameIdView("未处理的线索", "unprocess"));
		view.setOperationList(operationList);

		String codes = "dic_leads_type,dic_leads_channel,dic_customer_status";

		Result<List<DictionaryDto>> dictionaryResult = dictionaryService.gets(codes);
		if (dictionaryResult.getResult() != null && !dictionaryResult.getResult().isEmpty()) {
			List<DictionaryDto> dictionaryDtos = dictionaryResult.getResult();
			for (DictionaryDto dictionaryDto : dictionaryDtos) {
				if (StringUtils.isBlank(dictionaryDto.getValue())) {
					continue;
				}
				List<LabelValueDto> labelValueDtos = dictionaryDto.getSelections();
				List<NameIdView> nameIdViews = new ArrayList<>();
				if (labelValueDtos != null && !labelValueDtos.isEmpty()) {
					for (LabelValueDto labelValueDto : labelValueDtos) {
						nameIdViews.add(new NameIdView(labelValueDto.getLabel(), labelValueDto.getValue()));
					}
				}
				if ("dic_leads_type".equals(dictionaryDto.getValue())) {
					// 类型集合
					view.setTypeList(nameIdViews);
				} else if ("dic_leads_channel".equals(dictionaryDto.getValue())) {
					// 渠道集合
					view.setChannelList(nameIdViews);
				} else if ("dic_customer_status".equals(dictionaryDto.getValue())) {
					// 客户有效性集合
					view.setCustomerValidList(nameIdViews);
				}
			}
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<Long> doProcess(LeadsUpdateStatusReqDto dto) throws BaseException {
		// 判断是否有编辑线索的权限
		/*
		 * UserCustomerDto userCustomer =
		 * customerService.getUserCustomer(dto.getId()); Boolean canUpdate =
		 * permissionService.checkUpdate(DataObjectEnum.LEADS.getCode(),
		 * userCustomer != null ? userCustomer.getUserId() : null).getResult();
		 * if (!canUpdate) throw new BaseException(CodeConstant.CODE_1220015);
		 */
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", dto.getLineId());
		methodParams.put("followRemark", dto.getReason());
		if (StringUtils.isBlank(dto.getHandleType())) {
			throw new BaseException(CodeConstant.CODE_1300025);
		}
		if ("contacted".equals(dto.getHandleType())) {
			methodParams.put("status", 1);
		} else if ("close".equals(dto.getHandleType())) {
			methodParams.put("status", 2);
		} else {
			throw new BaseException(CodeConstant.CODE_1300026);
		}

		return HttpUtils.post(TenantUrl.PROCESS_LEADS, methodParams, new BaseTypeReference<Result<Long>>() {
		});
	}

	@Override
	public Result<ItemListDTO> getPageForOpenSeaCustomer(LeadsQueryReqDto dto) throws BaseException {

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		Result<PaginationSupport<LeadsResDto>> result = HttpUtils.get(TenantUrl.GET_LEADS_FOR_CUSTOMER_DETAIL_PUBLIC,
				ArgsUtils.toMap(dto), new BaseTypeReference<Result<PaginationSupport<LeadsResDto>>>() {
				});
		if (result != null && result.getResult() != null) {
			PaginationSupport<LeadsResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);
			List<LeadsResDto> leadsResDtos = pager.getDatas();
			for (LeadsResDto leadsResDto : leadsResDtos) {
				Structure3013View view3013 = new Structure3013View();
				view3013.setLineId(String.valueOf(leadsResDto.getId()));
				view3013.setChannel(leadsResDto.getChannelText());
				if (StringUtils.isNotBlank(leadsResDto.getCreateDate())) {
					Date lineTime = DateFormatUtils.parse(leadsResDto.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
					view3013.setLineTime(DateFormatUtils.format(lineTime, "yyyy-MM-dd HH:mm"));
				}
				view3013.setContent("[" + leadsResDto.getTypeText() + "]" + leadsResDto.getContent());
				if (LeadsStatusEnum.UNPROCESS.getValue() == leadsResDto.getStatus()) {
					view3013.setHandle(LeadsStatusEnum.UNPROCESS.getName());
				} else {
					String processUserName = leadsResDto.getProcessUserName();
					if (StringUtils.isBlank(leadsResDto.getProcessUserName())) {
						processUserName = "--";
					}
					view3013.setHandle(processUserName + ":" + leadsResDto.getStatusText());
				}
				// 如果 是公海进来的线索列表，操作权限全部设置为false
				view3013.setCanHandle(false);

				viewItems.add(new ViewItem(3013, view3013));
			}
		}

		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> getPageForCustomer(LeadsQueryReqDto dto) throws BaseException {

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		Integer permission = permissionService.getRead(DataObjectEnum.LEADS.getCode()).getResult();

		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_LEADS_FOR_CUSTOMER_DETAIL_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_LEADS_FOR_CUSTOMER_DETAIL_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_LEADS_FOR_CUSTOMER_DETAIL_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);

		if (StringUtils.isNotBlank(url)) {
			Result<PaginationSupport<LeadsResDto>> result = HttpUtils.get(url, ArgsUtils.toMap(dto),
					new BaseTypeReference<Result<PaginationSupport<LeadsResDto>>>() {
					});
			if (result != null && result.getResult() != null) {
				PaginationSupport<LeadsResDto> pager = result.getResult();
				itemListDTO.setPaginaton(pager);
				List<LeadsResDto> leadsResDtos = pager.getDatas();
				for (LeadsResDto leadsResDto : leadsResDtos) {
					Structure3013View view3013 = new Structure3013View();
					view3013.setLineId(String.valueOf(leadsResDto.getId()));
					view3013.setChannel(leadsResDto.getChannelText());
					if (StringUtils.isNotBlank(leadsResDto.getCreateDate())) {
						Date lineTime = DateFormatUtils.parse(leadsResDto.getCreateDate(), "yyyy-MM-dd HH:mm:ss");
						view3013.setLineTime(DateFormatUtils.format(lineTime, "yyyy-MM-dd HH:mm"));
					}
					view3013.setContent("[" + leadsResDto.getTypeText() + "]" + leadsResDto.getContent());
					if (LeadsStatusEnum.UNPROCESS.getValue() == leadsResDto.getStatus()) {
						view3013.setHandle(LeadsStatusEnum.UNPROCESS.getName());
					} else {
						String processUserName = leadsResDto.getProcessUserName();
						if (StringUtils.isBlank(leadsResDto.getProcessUserName())) {
							processUserName = "--";
						}
						view3013.setHandle(processUserName + ":" + leadsResDto.getStatusText());
					}
					if (dto.getFrom() != null && dto.getFrom() == 1) {
						// 如果 是公海进来的线索列表，操作权限全部设置为false
						view3013.setCanHandle(false);
					} else {
						if (leadsResDto.getFpId() != UserUtils.getUserId()) {
							view3013.setCanHandle(false);
						} else {
							view3013.setCanHandle(leadsResDto.getCanProcess());
						}
					}

					viewItems.add(new ViewItem(3013, view3013));
				}
			}
		}

		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);

	}

	private Result<ItemListDTO> getPageData(Map<String, Object> methodParams) throws BaseException {
		// 获取对客户资料的读取权限
		Integer permission = permissionService.getRead(DataObjectEnum.LEADS.getCode()).getResult();
		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_LEADS_PAGE_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_LEADS_PAGE_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_LEADS_PAGE_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);

		// 没有对应的url，表示没有权限，直接返回
		ItemListDTO itemListDTO = new ItemListDTO();

		if (BooleanUtils.isEmpty(url)) {
			return Result.newSuccessResult(itemListDTO);
		}
		Result<PaginationSupport<LeadsPageResDto>> result = HttpUtils.get(url, methodParams,
				new BaseTypeReference<Result<PaginationSupport<LeadsPageResDto>>>() {
				});
		if (result.getResult() != null) {
			PaginationSupport<LeadsPageResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);

			List<LeadsPageResDto> resDtos = pager.getDatas();
			if (resDtos != null && !resDtos.isEmpty()) {
				List<ViewItem> viewItems = new ArrayList<>();

				for (LeadsPageResDto resDto : resDtos) {
					Structure3018View view3018 = new Structure3018View();
					view3018.setLineId(String.valueOf(resDto.getId()));
					view3018.setName(resDto.getCustomerName());
					view3018.setType(resDto.getTypeText());
					view3018.setCustomerNo(resDto.getNumber());
					view3018.setCustomerId(String.valueOf(resDto.getCustomerId()));
					view3018.setChannel(resDto.getChannelText());
					view3018.setAssetManagerName(resDto.getFpName());
					view3018.setContent(resDto.getContent());
					view3018.setDistributionCallbackOperate(resDto.getCanAllotToCs());
					view3018.setDistributionAssetManagerOperate(resDto.getCanAllotToFp());
					view3018.setViewCallbackOperate(resDto.getCanViewVisitDetail());
					if (resDto.getCanViewVisitDetail()) {
						Map<String, Object> visitIdMap = new HashMap<>();
						visitIdMap.put("id", resDto.getUserVisitId());
						Result<UserVisitDto> visitResult = HttpUtils.get(TenantUrl.GET_USER_VISIT_GET, visitIdMap,
								new BaseTypeReference<Result<UserVisitDto>>() {
								});
						if (visitResult.getResult() != null) {
							UserVisitDto userVisitDto = visitResult.getResult();
							List<NameValueView> contentList = new ArrayList<>();
							contentList.add(new NameValueView("回访时间",
									DateFormatUtils.format(userVisitDto.getVisitDate(), "yyyy-MM-dd HH:mm:ss")));
							contentList.add(new NameValueView("回访方式", userVisitDto.getTypeText()));
							contentList.add(new NameValueView("回访状态", userVisitDto.getStatusText()));
							contentList.add(new NameValueView("回访内容", userVisitDto.getContent()));
							contentList.add(new NameValueView("客户状态", userVisitDto.getCustomerStatusText()));
							contentList.add(new NameValueView("回访负责人", userVisitDto.getUserName()));
							view3018.setContentList(contentList);
						}
					}
					viewItems.add(new ViewItem(3018, view3018));

				}

				itemListDTO.setViewItems(viewItems);
			}
		}
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<ItemListDTO> search(LeadsSearchReqDto dto) throws BaseException {
		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		if (StringUtils.isNotBlank(dto.getKeyword())) {
			methodParams.put("customerName", dto.getKeyword());
		}
		return this.getPageData(methodParams);
	}

	@Override
	public Result<ItemListDTO> getPage(LeadsPageReqDto dto) throws BaseException {

		Map<String, Object> methodParams = ArgsUtils.toMap(dto);
		if (StringUtils.isNotBlank(dto.getOperation())) {
			methodParams.put("scope", dto.getOperation());
		}
		if (StringUtils.isNotBlank(dto.getType())) {
			methodParams.put("types", dto.getType());
		}
		if (StringUtils.isNotBlank(dto.getChannel())) {
			methodParams.put("channels", dto.getChannel());
		}
		if (StringUtils.isNotBlank(dto.getCustomerValid())) {
			methodParams.put("statuss", dto.getCustomerValid());
		}
		return this.getPageData(methodParams);
	}

}
