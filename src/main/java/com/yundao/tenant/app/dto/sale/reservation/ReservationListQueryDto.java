package com.yundao.tenant.app.dto.sale.reservation;

import com.yundao.tenant.app.dto.AbstractBasePageDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/9/4.
 */
public class ReservationListQueryDto extends AbstractBasePageDto {


    @ApiModelProperty(value = "预约状态")
    private String statuss;


    @ApiModelProperty(value = "产品或者客户名称")
    private String productOrCustomerName;


    @ApiModelProperty(value = "传参代表排除保单的预约")
    private Integer isRemoveDeclaration;

    public String getProductOrCustomerName() {
        return productOrCustomerName;
    }

    public void setProductOrCustomerName(String productOrCustomerName) {
        this.productOrCustomerName = productOrCustomerName;
    }

    public Integer getIsRemoveDeclaration() {
        return isRemoveDeclaration;
    }

    public void setIsRemoveDeclaration(Integer isRemoveDeclaration) {
        this.isRemoveDeclaration = isRemoveDeclaration;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }
}
