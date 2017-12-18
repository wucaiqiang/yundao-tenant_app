
package com.yundao.tenant.app.service.sms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentAddDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentExeTimeUpdateDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentReqDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentResDto;
import com.yundao.tenant.app.enums.sms.SmsAssistantContentStatusEnum;
import com.yundao.tenant.app.model.customer.BaseCustomer;
import com.yundao.tenant.app.service.sms.SmsAssistantContentService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.DateUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.MobileUtils;
import com.yundao.tenant.app.view.sms.SmsAssistantContentDetailView;
import com.yundao.tenant.app.view.sms.SmsAssistantContentView;
import com.yundao.tenant.app.view.sms.SmsAssistantCustomerView;
import com.yundao.tenant.app.view.sms.SmsAssistantStayAlertView;
import com.yundao.tenant.app.view.sms.Structure3019View;

/**
 * Function: Reason: Date: 2017年11月30日 下午4:42:47
 * 
 * @author wucq
 * @version
 */
@Service
public class SmsAssistantContentServiceImpl implements SmsAssistantContentService {

	@Override
	public Result<Integer> updateExeTime(SmsAssistantContentExeTimeUpdateDto reqDto) throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("id", reqDto.getSmsId());
		params.put("executeTime", DateFormatUtils.format(reqDto.getDate() != null ? new Date(reqDto.getDate()) : null,
				"yyyy-MM-dd HH:mm:ss:SSS"));
		Result<Integer> result = HttpUtils.post(TenantUrl.SMSASSISTANTCONTENT_UPDATE_EXE_TIME, params,
				new BaseTypeReference<Result<Integer>>() {
				});

		return result;

	}

	@Override
	public Result<Map<String, Object>> addOrUpdate(SmsAssistantContentAddDto reqDto) throws Exception {
		String url = TenantUrl.SMSASSISTANTCONTENT_ADD;
		Map<String, Object> params = new HashMap<>();
		if (reqDto.getSmsId() != null) {
			params.put("id", reqDto.getSmsId());
			url = TenantUrl.SMSASSISTANTCONTENT_UPDATE;
		}
		params.put("callTitle", reqDto.getHonour());
		params.put("sendTitle", reqDto.getSign());
		params.put("content", reqDto.getContent());
		params.put("sendStatus", reqDto.getState());
		params.put("customerIds", reqDto.getCustomerIds());
		Result<Long> result = HttpUtils.post(url, params, new BaseTypeReference<Result<Long>>() {
		});
		if (!result.getSuccess() || result.getResult() == null) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("smsId", result.getResult());
		return Result.newSuccessResult(retMap);

	}

	@Override
	public Result<Integer> delete(String smsIds) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("ids", smsIds);
		Result<Integer> result = HttpUtils.post(TenantUrl.SMSASSISTANTCONTENT_DELETE, params,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;

	}

	@Override
	public Result<SmsAssistantContentView> get(Long smsId) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("id", smsId);
		Result<SmsAssistantContentResDto> result = HttpUtils.get(TenantUrl.SMSASSISTANTCONTENT_GET, params,
				new BaseTypeReference<Result<SmsAssistantContentResDto>>() {
				});
		if (!result.getSuccess() || result.getResult() == null) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		SmsAssistantContentResDto dto = result.getResult();

		SmsAssistantContentView view = new SmsAssistantContentView();
		view.setContent(dto.getCallTitle() + dto.getContent() + dto.getSendTitle());
		List<BaseCustomer> customers = dto.getCustomers();
		if (customers != null && !customers.isEmpty()) {
			List<SmsAssistantCustomerView> customerViews = new ArrayList<>();
			customers.forEach((v) -> {
				SmsAssistantCustomerView customerView = new SmsAssistantCustomerView();
				customerView.setName(v.getName());
				customerView.setMobile(v.getMobile());
				customerViews.add(customerView);
			});
			view.setCustomerList(customerViews);
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<SmsAssistantContentDetailView> getDetail(Long smsId) throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("id", smsId);
		Result<SmsAssistantContentResDto> result = HttpUtils.get(TenantUrl.SMSASSISTANTCONTENT_GET, params,
				new BaseTypeReference<Result<SmsAssistantContentResDto>>() {
				});
		if (!result.getSuccess() || result.getResult() == null) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		SmsAssistantContentResDto dto = result.getResult();

		SmsAssistantContentDetailView view = new SmsAssistantContentDetailView();
		view.setContent(dto.getContent());
		view.setSign(dto.getSendTitle());
		view.setHonour(dto.getCallTitle());
		view.setSmsId(String.valueOf(dto.getId()));
		List<BaseCustomer> customers = dto.getCustomers();
		if (customers != null && !customers.isEmpty()) {
			List<SmsAssistantCustomerView> customerViews = new ArrayList<>();
			customers.forEach((v) -> {
				SmsAssistantCustomerView customerView = new SmsAssistantCustomerView();
				customerView.setCustomerId(String.valueOf(v.getId()));
				customerView.setName(v.getName());
				customerView.setMobile(v.getMobile());
				customerView.setMobileText(MobileUtils.format(v.getMobile()));
				customerViews.add(customerView);
			});
			view.setCustomerList(customerViews);
		}
		return Result.newSuccessResult(view);

	}

	@Override
	public Result<Map<String, Object>> getStayAlert() throws Exception {
		Result<List<SmsAssistantContentResDto>> result = HttpUtils.get(TenantUrl.SMSASSISTANTCONTENT_GET_STAY_ALERT,
				null, new BaseTypeReference<Result<List<SmsAssistantContentResDto>>>() {
				});

		if (result.getResult() == null || !result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		Map<String, Object> retMap = new HashMap<>();

		List<SmsAssistantStayAlertView> views = new ArrayList<>();
		List<SmsAssistantContentResDto> resDtos = result.getResult();

		if (resDtos != null && !resDtos.isEmpty()) {
			for (SmsAssistantContentResDto dto : resDtos) {
				SmsAssistantStayAlertView view = new SmsAssistantStayAlertView();
				view.setSmsId(String.valueOf(dto.getId()));
				view.setContent(dto.getCallTitle() + dto.getContent() + dto.getSendTitle());
				view.setTime(dto.getExecuteTime() != null ? dto.getExecuteTime().getTime() : null);
				List<BaseCustomer> customers = dto.getCustomers();
				if (customers != null && !customers.isEmpty()) {
					List<String> customerNameList = new ArrayList<>();
					for (BaseCustomer customer : customers) {
						customerNameList.add(customer.getName());
					}
					view.setCustomerNameList(customerNameList);
				}
				views.add(view);
			}
		}
		retMap.put("smsList", views);
		return Result.newSuccessResult(retMap);

	}

	@Override
	public Result<ItemListDTO> getPage(SmsAssistantContentReqDto smsAssistantContentReqDto) throws Exception {
		ItemListDTO itemListDTO = new ItemListDTO();
		Result<PaginationSupport<SmsAssistantContentResDto>> result = HttpUtils.get(
				TenantUrl.SMSASSISTANTCONTENT_GET_PAGE, ArgsUtils.toMap(smsAssistantContentReqDto),
				new BaseTypeReference<Result<PaginationSupport<SmsAssistantContentResDto>>>() {
				});

		if (result.getResult() != null) {
			PaginationSupport<SmsAssistantContentResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);
			if (pager.getDatas() != null && !pager.getDatas().isEmpty()) {
				List<ViewItem> viewItems = new ArrayList<>();
				List<SmsAssistantContentResDto> resDtos = pager.getDatas();
				for (SmsAssistantContentResDto dto : resDtos) {
					List<BaseCustomer> customers = dto.getCustomers();
					Structure3019View view3019 = new Structure3019View();
					view3019.setSmsId(String.valueOf(dto.getId()));
					view3019.setContent(dto.getCallTitle() + dto.getContent() + dto.getSendTitle());
					view3019.setState(dto.getSendStatus());

					if (customers != null && !customers.isEmpty()) {
						List<String> customerNameList = new ArrayList<>();
						customers.forEach((v) -> {
							customerNameList.add(v.getName());
						});
						view3019.setCustomerNameList(customerNameList);
					}
					String jumpUrl = "";
					if (dto.getSendStatus() != null
							&& (dto.getSendStatus() == SmsAssistantContentStatusEnum.SEND_SUCCESS.getValue()
									|| dto.getSendStatus() == SmsAssistantContentStatusEnum.SEND_FAIL.getValue())) {
						view3019.setDate("发送时间：" + (dto.getSendTime() == null ? "无"
								: DateFormatUtils.format(dto.getSendTime(), "yyyy-MM-dd HH:mm")));
						jumpUrl = Schema.SMS_ASSISTANT_CONTENT_DETAIL
								+ String.format(Schema.SMS_ASSISTANT_CONTENT_DETAIL_PARAMS, dto.getId());
					} else {
						jumpUrl = Schema.SMS_ASSISTANT_CONTENT_EDIT
								+ String.format(Schema.SMS_ASSISTANT_CONTENT_EDIT_PARAMS, dto.getId());
						view3019.setDate("提醒时间：" + (dto.getExecuteTime() == null ? "无"
								: DateFormatUtils.format(dto.getExecuteTime(), "yyyy-MM-dd HH:mm")));
					}
					viewItems.add(new ViewItem(3019, jumpUrl, view3019));
				}
				itemListDTO.setViewItems(viewItems);
			}
		}
		return Result.newSuccessResult(itemListDTO);

	}

}
