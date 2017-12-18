
package com.yundao.tenant.app.dto.sms;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SmsAssistantContentExeTimeUpdateDto implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "短信id")
	private String smsId;

	@ApiModelProperty(value = "提醒时间")
	private Long date;

	public String getSmsId() {
	
		return smsId;
	}

	public void setSmsId(String smsId) {
	
		this.smsId = smsId;
	}

	public Long getDate() {
	
		return date;
	}

	public void setDate(Long date) {
	
		this.date = date;
	}
	

}
