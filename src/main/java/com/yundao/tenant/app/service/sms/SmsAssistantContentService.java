
package com.yundao.tenant.app.service.sms;

import java.util.List;
import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentAddDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentExeTimeUpdateDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentReqDto;
import com.yundao.tenant.app.dto.sms.SmsAssistantContentResDto;
import com.yundao.tenant.app.view.sms.SmsAssistantContentDetailView;
import com.yundao.tenant.app.view.sms.SmsAssistantContentView;

/**
 * Function: Reason: Date: 2017年11月30日 下午4:42:15
 * 
 * @author wucq
 * @version
 */
public interface SmsAssistantContentService {
	public Result<Map<String, Object>> addOrUpdate(SmsAssistantContentAddDto reqDto) throws Exception;
	public Result<Integer> updateExeTime(SmsAssistantContentExeTimeUpdateDto reqDto) throws Exception;

	public Result<Integer> delete(String ids) throws Exception;

	public Result<SmsAssistantContentView> get(Long smsId) throws Exception;
	public Result<SmsAssistantContentDetailView> getDetail(Long smsId) throws Exception;

	public Result<Map<String,Object>> getStayAlert() throws Exception;

	public Result<ItemListDTO> getPage(SmsAssistantContentReqDto smsAssistantContentReqDto) throws Exception;
}
