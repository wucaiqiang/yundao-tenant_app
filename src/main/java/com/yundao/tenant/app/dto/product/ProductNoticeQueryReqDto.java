package com.yundao.tenant.app.dto.product;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class ProductNoticeQueryReqDto extends AbstractBasePageDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("公告标题")
    private String title;

	public String getTitle() {
	
		return title;
	}

	public void setTitle(String title) {
	
		this.title = title;
	}

	

    
}
