package com.yundao.tenant.app.dto.sale.leads;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户详情销售线索数据请求dto
 *
 * @author jan
 * @create 2017-09-07 PM6:05
 **/
public class LeadsQueryReqDto extends AbstractBasePageDto {
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "客户id")
    private Long customerId;
	@ApiModelProperty(value = "如果为0或者没有：我的客户进入 ， 1：从公海进入 ,则不能进行操作线索")
	private Integer from;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

	public Integer getFrom() {
	
		return from;
	}

	public void setFrom(Integer from) {
	
		this.from = from;
	}
    
}
