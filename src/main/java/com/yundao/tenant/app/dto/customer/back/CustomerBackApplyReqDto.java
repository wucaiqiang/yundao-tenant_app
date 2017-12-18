package com.yundao.tenant.app.dto.customer.back;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 申请请求回退dto
 *
 * @author jan
 * @create 2017-08-10 PM3:40
 **/
public class CustomerBackApplyReqDto implements Serializable {

    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "回退原因")
    private String reason;
    @ApiModelProperty(value = "客户id，多个逗号分隔")
    private String customerId;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

	public String getCustomerId() {
	
		return customerId;
	}

	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
    
}