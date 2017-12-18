package com.yundao.tenant.app.dto.sale.reservation;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 添加预约请求dto
 *
 * @author jan
 * @create 2017-08-23 PM6:25
 **/
public class ReservationUpdateReqDto {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "预计打款日期")
    private Date estimatePayDate;

    @ApiModelProperty(value = "预约金额")
    private Double reservationAmount;

    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
