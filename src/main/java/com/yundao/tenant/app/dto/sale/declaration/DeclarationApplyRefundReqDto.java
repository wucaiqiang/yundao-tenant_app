package com.yundao.tenant.app.dto.sale.declaration;

import io.swagger.annotations.ApiModelProperty;

/**
 * 申请退款 date: 2017年10月16日 下午1:45:22
 * 
 * @author:欧阳利
 * @description:
 */
public class DeclarationApplyRefundReqDto {

	@ApiModelProperty(value = "报单id")
	private Long id;

	@ApiModelProperty(value = "原因")
	private String reason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
