package com.yundao.tenant.app.dto.sale.reservation;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by lan on 2017/8/30.
 */
public class ReservationAddReqDto implements Serializable {

    @ApiModelProperty(value = "产品ID")
    private Long productId;
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    @ApiModelProperty(value = "预约金额")
    private Integer amount;
    @ApiModelProperty(value = "预约时间日期戳")
    private Long appointDate;
    @ApiModelProperty(value = "备注")
    private String remarks;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Long appointDate) {
        this.appointDate = appointDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
