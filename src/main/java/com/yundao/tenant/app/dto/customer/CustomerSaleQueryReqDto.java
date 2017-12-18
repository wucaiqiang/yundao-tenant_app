package com.yundao.tenant.app.dto.customer;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerSaleQueryReqDto extends AbstractBasePageDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户ID")
    private String customerId;

	@ApiModelProperty("预约：appointment,报单： bill")
    private String filter;

	public String getCustomerId() {
	
		return customerId;
	}

	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}

	public String getFilter() {
	
		return filter;
	}

	public void setFilter(String filter) {
	
		this.filter = filter;
	}
	

    
}
