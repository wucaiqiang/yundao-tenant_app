package com.yundao.tenant.app.dto.product;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

public class ProductBaseQueryReqDto extends AbstractBasePageDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("产品名称")
	private String name;
	@ApiModelProperty("空：只显示预售和售中的  filter_0_1: 过滤掉“未上线”和“上线准备中”两种状态的产品，filter_none：查询全部,filter_order: 产品包括->1、募集中（可直接报单）的产品 2、募集中（先预约后报单）且该员工有审批通过的预约的产品 3、预售，且该员工有审批通过的预约的产品")
	private String filterType;
	@ApiModelProperty("搜索关键字")
	private String keyword;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getFilterType() {

		return filterType;
	}

	public void setFilterType(String filterType) {

		this.filterType = filterType;
	}

	public String getKeyword() {

		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

}
