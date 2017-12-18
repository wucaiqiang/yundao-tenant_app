
package com.yundao.tenant.app.dto.sale.declaration;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DeclarationBankReqDto implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "新增不需要传")
    private Long bankId;

    @ApiModelProperty(value = "报单id")
    private Long orderId;

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


	public Long getOrderId() {
	
		return orderId;
	}

	public void setOrderId(Long orderId) {
	
		this.orderId = orderId;
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
