package com.yundao.tenant.app.dto.customer.distribution;

import com.yundao.tenant.app.dto.customer.CustomerMultiIdReqDto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerAllotToCsReqDto extends CustomerMultiIdReqDto{
    @ApiModelProperty(value = "客服id")
    private long csId;
    
    @ApiModelProperty(value = "回访事由")
    private String matter;

	public long getCsId() {
		return csId;
	}

	public void setCsId(long csId) {
		this.csId = csId;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}


}
