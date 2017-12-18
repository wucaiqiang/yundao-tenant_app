package com.yundao.tenant.app.dto.sale.reservation;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

/**
 * Created by lan on 2017/9/4.
 */
public class ReservationSearchListReqDto extends AbstractBasePageDto {

    private  String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
