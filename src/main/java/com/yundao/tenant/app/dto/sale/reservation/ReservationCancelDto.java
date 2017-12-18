package com.yundao.tenant.app.dto.sale.reservation;

import java.io.Serializable;

/**
 * Created by lan on 2017/8/31.
 */
public class ReservationCancelDto implements Serializable {

    private Long id;
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
