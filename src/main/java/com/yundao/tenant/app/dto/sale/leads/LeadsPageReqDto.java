package com.yundao.tenant.app.dto.sale.leads;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 销售线索分页数据请求dto
 *
 * @author jan
 * @create 2017-09-06 PM8:14
 **/
public class LeadsPageReqDto extends AbstractBasePageDto {

    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "处理类型（单选）")
    private String operation;
    @ApiModelProperty(value = "类型（单选）")
    private String type;
    @ApiModelProperty(value = "渠道（单选））")
    private String channel;
    @ApiModelProperty(value = "客户有效性（单选）")
    private String customerValid;
	public String getOperation() {
	
		return operation;
	}
	public void setOperation(String operation) {
	
		this.operation = operation;
	}
	public String getType() {
	
		return type;
	}
	public void setType(String type) {
	
		this.type = type;
	}
	public String getChannel() {
	
		return channel;
	}
	public void setChannel(String channel) {
	
		this.channel = channel;
	}
	public String getCustomerValid() {
	
		return customerValid;
	}
	public void setCustomerValid(String customerValid) {
	
		this.customerValid = customerValid;
	}
    

}
