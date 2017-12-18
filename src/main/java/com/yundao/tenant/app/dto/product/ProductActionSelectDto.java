package com.yundao.tenant.app.dto.product;

import io.swagger.annotations.ApiModelProperty;

public class ProductActionSelectDto {
	@ApiModelProperty("选项值")
    private Integer value;
	@ApiModelProperty("选项名称")
    private String lable;
	
	@ApiModelProperty("是否有权限操作")
    private Boolean canAction = true;
	
	@ApiModelProperty("无权限操作提示信息")
	private String title;

	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Boolean getCanAction() {
	
		return canAction;
	}
	public void setCanAction(Boolean canAction) {
	
		this.canAction = canAction;
	}
    
}
