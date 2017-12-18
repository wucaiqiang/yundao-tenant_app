package com.yundao.tenant.app.view.sale.reservation;

import com.yundao.tenant.app.view.NameIdView;

import java.util.List;

/**
 * Created by lan on 2017/8/31.
 */
public class ReservationListColumnsView {

    private List<NameIdView> appointType;

    public ReservationListColumnsView() {
    }

    public ReservationListColumnsView(List<NameIdView> appointType) {
        this.appointType = appointType;
    }

    public List<NameIdView> getAppointType() {
        return appointType;
    }

    public void setAppointType(List<NameIdView> appointType) {
        this.appointType = appointType;
    }
}
