
package com.yundao.tenant.app.service.msg.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.msg.MsgMessagePageResDto;
import com.yundao.tenant.app.dto.msg.MsgMessageQueryReqDto;
import com.yundao.tenant.app.enums.msg.message.MsgMessageFirstTypeEnum;
import com.yundao.tenant.app.service.msg.MsgMessageService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.CalendarUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.msg.MsgUnReadCountView;
import com.yundao.tenant.app.view.msg.Structure5001View;
import com.yundao.tenant.app.view.msg.Structure5002View;

/**
 * Function: Reason: Date: 2017年9月7日 下午5:37:36
 * 
 * @author wucq
 * @version
 */
@Service
public class MsgMessageServiceImpl extends AbstractService implements MsgMessageService {

	@Override
	public Result<Integer> processReadAll(String type) throws BaseException {
		Map<String, Object> methodParams=new HashMap<>();
		if(StringUtils.isNotBlank(type) && "NOTICE".equals(type)){
			methodParams.put("type", MsgMessageFirstTypeEnum.MESSAGE_TYPE_2.getType());
		}
 		Result<Integer> result = HttpUtils.get(TenantUrl.MESSAGE_READ_ALL, methodParams,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;

	}

	@Override
	public Result<Integer> processRead(String ids) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("ids", ids);
		Result<Integer> result = HttpUtils.get(TenantUrl.MESSAGE_READ, methodParams,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;
	}

	@Override
	public Result<List<MsgMessagePageResDto>> getListByLimit(MsgMessageQueryReqDto dto) throws BaseException {
		Map<String, Object> params = ArgsUtils.toMap(dto);
		if (StringUtils.isNotBlank(dto.getType()) && "notice".equals(dto.getType())) {
			params.put("firstType", MsgMessageFirstTypeEnum.MESSAGE_TYPE_2.getType());
		} else {
			params.put("firstType", MsgMessageFirstTypeEnum.MESSAGE_TYPE_1.getType());
		}
		Result<PaginationSupport<MsgMessagePageResDto>> result = HttpUtils.get(TenantUrl.MESSAGE_GET_MY, params,
				new BaseTypeReference<Result<PaginationSupport<MsgMessagePageResDto>>>() {
				});
		if(result !=null && result.getResult() !=null){
			PaginationSupport<MsgMessagePageResDto> pager=result.getResult();
			if(pager !=null){
				return Result.newSuccessResult(pager.getDatas());
			}
		}
		return Result.newSuccessResult(null);
		
	}

	@Override
	public Result<ItemListDTO> getPage(MsgMessageQueryReqDto dto) throws BaseException {
		Map<String, Object> params = ArgsUtils.toMap(dto);
		if (StringUtils.isNotBlank(dto.getType()) && "notice".equals(dto.getType())) {
			params.put("firstType", MsgMessageFirstTypeEnum.MESSAGE_TYPE_2.getType());
		} else {
			params.put("firstType", MsgMessageFirstTypeEnum.MESSAGE_TYPE_1.getType());
		}

		ItemListDTO itemListDTO = new ItemListDTO();

		Result<PaginationSupport<MsgMessagePageResDto>> pagerResult = HttpUtils.get(TenantUrl.MESSAGE_GET_MY, params,
				new BaseTypeReference<Result<PaginationSupport<MsgMessagePageResDto>>>() {
				});
		if (pagerResult != null && pagerResult.getResult() != null) {
			PaginationSupport<MsgMessagePageResDto> pager = pagerResult.getResult();
			itemListDTO.setPaginaton(pager);
			List<ViewItem> viewItems = new ArrayList<>();
			
			List<MsgMessagePageResDto> messagePageResDtos = pager.getDatas();
			if (messagePageResDtos != null && !messagePageResDtos.isEmpty()) {
				for (MsgMessagePageResDto message : messagePageResDtos) {
					Structure5002View view5002 = new Structure5002View();
					if (message.getCreateDate() != null) {
						view5002.setText(CalendarUtils.changeDateToText(message.getCreateDate()));
					}
					viewItems.add(new ViewItem(5002, view5002));
					
					Structure5001View view5001 = new Structure5001View();
					view5001.setMessageId(String.valueOf(message.getId()));
					view5001.setTitle("[" + message.getTwoTypeText() + "]" + message.getTitle());
					view5001.setContent(message.getAppContent());
					view5001.setReaded(message.getReadStatus() == 2 ? true : false);
					String jumpUrl="";
					if(StringUtils.isNotBlank(message.getAppAction())){
						jumpUrl=message.getAppAction();
					}
					viewItems.add(new ViewItem(5001, jumpUrl, view5001));
				}
			}
			itemListDTO.setViewItems(viewItems);
		}
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<Integer> getTodoCount() throws BaseException {
		Date today=new Date();
		Map<String, Object> params = new HashMap<>();
		params.put("beginDate", DateFormatUtils.format(CalendarUtils.getDayBegin(today), "yyyy-MM-dd HH:mm:ss"));
		params.put("endDate", DateFormatUtils.format(CalendarUtils.getDayEnd(today), "yyyy-MM-dd HH:mm:ss"));
		Result<Integer> result = HttpUtils.get(TenantUrl.MSG_MESSAGE_GET_TODO_COUNT, params,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;
		
	}

	@Override
	public Result<MsgUnReadCountView> getUnreadCount(Date lastDate) throws BaseException {
		MsgUnReadCountView view = new MsgUnReadCountView();

		// 待办消息数量
		Map<String, Object> params = new HashMap<>();
		params.put("type", MsgMessageFirstTypeEnum.MESSAGE_TYPE_1.getType());
		Result<Integer> taskCountResult = HttpUtils.get(TenantUrl.MSG_MESSAGE_GET_UNREAD_COUNT, params,
				new BaseTypeReference<Result<Integer>>() {
				});
		if (taskCountResult != null && taskCountResult.getResult() != null) {
			view.setPendingCount(taskCountResult.getResult());
		}

		// 通知消息数量
		Map<String, Object> noticeParams = new HashMap<>();
		noticeParams.put("type", MsgMessageFirstTypeEnum.MESSAGE_TYPE_2.getType());
		noticeParams.put("fromDate", DateFormatUtils.format(lastDate, "yyyy-MM-dd HH:mm:ss"));
		Result<Integer> noticeCountResult = HttpUtils.get(TenantUrl.MSG_MESSAGE_GET_UNREAD_COUNT, noticeParams,
				new BaseTypeReference<Result<Integer>>() {
				});
		if (noticeCountResult != null && noticeCountResult.getResult() != null) {
			view.setNoticeCount(noticeCountResult.getResult());
		}

		return Result.newSuccessResult(view);

	}

}
