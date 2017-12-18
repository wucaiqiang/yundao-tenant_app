package com.yundao.tenant.app.dto.tag;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class TagReqDto extends AbstractBasePageDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("模糊关键字关键字")
	private String keyword;

	public String getKeyword() {

		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

}
