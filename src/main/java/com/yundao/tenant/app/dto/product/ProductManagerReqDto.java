package com.yundao.tenant.app.dto.product;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class ProductManagerReqDto extends AbstractBasePageDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("搜索关键字")
	private String keyword;

	public String getKeyword() {
	
		return keyword;
	}

	public void setKeyword(String keyword) {
	
		this.keyword = keyword;
	}

    
}
