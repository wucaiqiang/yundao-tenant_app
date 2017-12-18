package com.yundao.tenant.app.dto.customer.distribution;


import com.yundao.tenant.app.dto.customer.CustomerMultiIdReqDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户分配请求
 *
 * @author jan
 * @create 2017-08-14 PM4:54
 **/
public class CustomerAllotReqDto extends CustomerMultiIdReqDto {

    @ApiModelProperty(value = "理财师id")
    private long fpId;

    public long getFpId() {
        return fpId;
    }

    public void setFpId(long fpId) {
        this.fpId = fpId;
    }
}
