package com.yundao.tenant.app.dto.sale.reservation;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by lan on 2017/8/30.
 */
public class ReservationAddDto {


    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "预计打款日期")
    private Date estimatePayDate;

    @ApiModelProperty(value = "预约金额")
    private Double reservationAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    public Date getEstimatePayDate() {
        return estimatePayDate;
    }

    public void setEstimatePayDate(Date estimatePayDate) {
        this.estimatePayDate = estimatePayDate;
    }

    public Double getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(Double reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
