package com.yundao.tenant.app.dto.sale.reservation;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by lan on 2017/8/31.
 */
public class ReservationCancelReqDto implements Serializable {

    @ApiModelProperty(value = "预约Id")
    private String appointId;
    @ApiModelProperty(value = "取消预约原因")
    private String reason;

    public String getAppointId() {
        return appointId;
    }

    public void setAppointId(String appointId) {
        this.appointId = appointId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

