package com.yundao.tenant.app.dto.user.visit;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户详情页回访分页数据
 *
 * @author jan
 * @create 2017-08-11 AM10:25
 **/
public class UserVisitPageReqDto extends AbstractBasePageDto {

    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "客户id")
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
