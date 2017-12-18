package com.yundao.tenant.app.dto.customer.opensea;



import com.yundao.tenant.app.annotation.Owner;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户池分页数据
 *
 * @author jan
 * @create 2017-08-14 AM8:56
 **/
public class CustomerOpenSeaPageResDto {

    @ApiModelProperty(value = "客户id")
    private Long id;

    //@FieldCode(Customer.NAME)
    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "客户编号")
    private String number;

    @Owner
    @ApiModelProperty(value = "理财师id")
    private Long fpId;

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

	public String getNumber() {
	
		return number;
	}

	public void setNumber(String number) {
	
		this.number = number;
	}

	public Long getFpId() {
	
		return fpId;
	}

	public void setFpId(Long fpId) {
	
		this.fpId = fpId;
	}
    



}
