
package com.yundao.tenant.app.dto.customer.bank;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CustomerBankReqDto implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "新增不需要传")
    private Long bankId;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "银行卡账号")
    private String bankNumber;

    @ApiModelProperty(value = "开户行")
    private String bankName;

    @ApiModelProperty(value = "银行卡正面")
    private String bankFontCardUrl;

    @ApiModelProperty(value = "银行卡反面")
    private String bankBackCardUrl;

	public Long getBankId() {
	
		return bankId;
	}

	public void setBankId(Long bankId) {
	
		this.bankId = bankId;
	}

	public Long getCustomerId() {
	
		return customerId;
	}

	public void setCustomerId(Long customerId) {
	
		this.customerId = customerId;
	}

	public String getBankNumber() {
	
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
	
		this.bankNumber = bankNumber;
	}

	public String getBankName() {
	
		return bankName;
	}

	public void setBankName(String bankName) {
	
		this.bankName = bankName;
	}

	public String getBankFontCardUrl() {
	
		return bankFontCardUrl;
	}

	public void setBankFontCardUrl(String bankFontCardUrl) {
	
		this.bankFontCardUrl = bankFontCardUrl;
	}

	public String getBankBackCardUrl() {
	
		return bankBackCardUrl;
	}

	public void setBankBackCardUrl(String bankBackCardUrl) {
	
		this.bankBackCardUrl = bankBackCardUrl;
	}


}
