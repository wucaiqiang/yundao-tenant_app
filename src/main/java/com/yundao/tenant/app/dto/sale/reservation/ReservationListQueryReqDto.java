package com.yundao.tenant.app.dto.sale.reservation;

import com.yundao.tenant.app.dto.AbstractBasePageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/9/4.
 */
public class ReservationListQueryReqDto extends AbstractBasePageDto {

    @ApiModelProperty("预约列表筛选类别 预约列表type")
    private String filterType;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
