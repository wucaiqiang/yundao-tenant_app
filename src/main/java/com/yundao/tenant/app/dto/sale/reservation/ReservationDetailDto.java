package com.yundao.tenant.app.dto.sale.reservation;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/9/12.
 */
public class ReservationDetailDto extends BaseReservationDto {

    @ApiModelProperty(value = "是否已报单")
    private Boolean hasDeclaration;

    @ApiModelProperty(value = "预约是否作废")
    private Boolean discard;

    public Boolean getHasDeclaration() {

        return hasDeclaration;
    }

    public void setHasDeclaration(Boolean hasDeclaration) {

        this.hasDeclaration = hasDeclaration;
    }

    public Boolean getDiscard() {

        return discard;
    }

    public void setDiscard(Boolean discard) {

        this.discard = discard;
    }

}
