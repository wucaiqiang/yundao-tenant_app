
package com.yundao.tenant.app.dto.sms;

import io.swagger.annotations.ApiModelProperty;

public class SmsAssistantContentAddDto {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "短信id 如果有则是编辑，如果没有则是新增")
	private Long smsId;
	@ApiModelProperty(value = "称呼")
	private String honour;

	@ApiModelProperty(value = "签名")
	private String sign;

	@ApiModelProperty(value = "内容")
	private String content;

	@ApiModelProperty(value = "发送状态 ： 0:未发送 1：发送成功 2：发送失败")
	private Integer state;

	@ApiModelProperty(value = "客户IDs")
	private String customerIds;

	public String getHonour() {

		return honour;
	}

	public void setHonour(String honour) {

		this.honour = honour;
	}

	public String getSign() {

		return sign;
	}

	public void setSign(String sign) {

		this.sign = sign;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public Integer getState() {

		return state;
	}

	public void setState(Integer state) {

		this.state = state;
	}

	public String getCustomerIds() {

		return customerIds;
	}

	public void setCustomerIds(String customerIds) {

		this.customerIds = customerIds;
	}

	public Long getSmsId() {
	
		return smsId;
	}

	public void setSmsId(Long smsId) {
	
		this.smsId = smsId;
	}

}
