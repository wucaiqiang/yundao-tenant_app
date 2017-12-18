
package com.yundao.tenant.app.dto.customer.followup;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerFollowUpPageDto extends AbstractBasePageDto {
    @ApiModelProperty(value = "客户id")
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
