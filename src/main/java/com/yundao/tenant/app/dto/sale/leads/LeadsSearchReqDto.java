package com.yundao.tenant.app.dto.sale.leads;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 销售线索分页数据请求dto
 *
 * @author jan
 * @create 2017-09-06 PM8:14
 **/
public class LeadsSearchReqDto extends AbstractBasePageDto {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "关键字")
	private String keyword;

	public String getKeyword() {

		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

}
