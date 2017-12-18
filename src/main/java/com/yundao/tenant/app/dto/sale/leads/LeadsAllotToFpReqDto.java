package com.yundao.tenant.app.dto.sale.leads;


import io.swagger.annotations.ApiModelProperty;

/**
 * 分配给理财师数据请求dto
 *
 * @author jan
 * @create 2017-09-09 PM8:16
 **/
public class LeadsAllotToFpReqDto {

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "理财师id")
    private Long fpId;

    public Long getFpId() {
        return fpId;
    }

    public void setFpId(Long fpId) {
        this.fpId = fpId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
