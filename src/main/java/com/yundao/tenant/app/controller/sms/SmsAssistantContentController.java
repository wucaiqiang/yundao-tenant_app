package com.yundao.tenant.app.controller.sms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.validator.spring.BindingResultHandler;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentAddDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentExeTimeUpdateDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentReqDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentResDto;
import com.yundao.tenant.app.service.sms.SmsAssistantContentService;
import com.yundao.tenant.app.view.sms.SmsAssistantContentDetailView;
import com.yundao.tenant.app.view.sms.SmsAssistantContentView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sms/assistant/content/")
@ResponseBody
@Api("短信助手发送的短信内容")
public class SmsAssistantContentController {

	@Autowired
	private SmsAssistantContentService smsAssistantContentService;

	@RequestMapping(value = "get_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询短信助手发送的短信内容")
	public Result<ItemListDTO> getPage(@ModelAttribute SmsAssistantContentReqDto dto) throws Exception {
		return smsAssistantContentService.getPage(dto);
	}

	@RequestMapping(value = "add_or_update", method = RequestMethod.POST)
	@ApiOperation(value = "新增短信助手发送的短信内容", notes = "根据SmsAssistantContent对象创建短信助手发送的短信内容")
	public Result<Map<String, Object>> addOrUpdate(@Validated @ModelAttribute SmsAssistantContentAddDto reqDto,
			BindingResult bindingResult) throws Exception {
		BindingResultHandler.handleByException(bindingResult);
		return smsAssistantContentService.addOrUpdate(reqDto);
	}

	@RequestMapping(value = "update_exe_time", method = RequestMethod.POST)
	@ApiOperation(value = "设置短信发送提醒时间", notes = "设置短信发送提醒时间")
	public Result<Integer> updateExeTime(@Validated @ModelAttribute SmsAssistantContentExeTimeUpdateDto reqDto,
			BindingResult bindingResult) throws Exception {
		BindingResultHandler.handleByException(bindingResult);
		return smsAssistantContentService.updateExeTime(reqDto);
	}

	@RequestMapping(value = "get", method = RequestMethod.POST)
	@ApiOperation(value = "获取短信助手发送的短信内容详细信息")
	public Result<SmsAssistantContentView> get(@RequestParam Long smsId) throws Exception {
		return smsAssistantContentService.get(smsId);
	}
	@RequestMapping(value = "get_deatail", method = RequestMethod.POST)
	@ApiOperation(value = "获取短信助手发送的短信内容详细信息")
	public Result<SmsAssistantContentDetailView> getDetail(@RequestParam Long smsId) throws Exception {
		return smsAssistantContentService.getDetail(smsId);
	}
	@RequestMapping(value = "get_stay_alert", method = RequestMethod.POST)
	@ApiOperation(value = "获取待提醒短信")
	public Result<Map<String,Object>> getStayAlert() throws Exception {
		return smsAssistantContentService.getStayAlert();
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除短信助手发送的短信内容信息")
	public Result<Integer> delete(@RequestParam String smsIds) throws Exception {
		return smsAssistantContentService.delete(smsIds);
	}

}
