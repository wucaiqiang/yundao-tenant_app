package com.yundao.tenant.app.dto.customer;


import io.swagger.annotations.ApiModelProperty;

/**
 * 多客户id请求dto
 *
 * @author jan
 * @create 2017-08-10 PM3:41
 **/
public class CustomerMultiIdReqDto {

    @ApiModelProperty(value = "客户id，多个逗号分隔")
    private String customerIds;

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }
}