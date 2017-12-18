
package com.yundao.tenant.app.dto.customer.followup;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class CustomerFollowUpDto {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "客户id")
	private Long customerId;

	@ApiModelProperty(value = "跟进方式")
	private Integer type;

	@ApiModelProperty(value = "跟进状态")
	private Integer status;

	@ApiModelProperty(value = "跟进内容")
	private String content;

	@ApiModelProperty(value = "下次跟进时间")
	private Date nextFollowDate;
	@ApiModelProperty(value = "地图类型")
	private Integer mapType;
	@ApiModelProperty(value = "经度")
	private Double itudeX;
	@ApiModelProperty(value = "纬度")
	private Double itudeY;
	@ApiModelProperty(value = "地址")
	private String address;
	@ApiModelProperty(value = "附件列表")
	private String attachs;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNextFollowDate() {
		return nextFollowDate;
	}

	public void setNextFollowDate(Date nextFollowDate) {
		this.nextFollowDate = nextFollowDate;
	}

	public Integer getMapType() {

		return mapType;
	}

	public void setMapType(Integer mapType) {

		this.mapType = mapType;
	}

	public Double getItudeX() {

		return itudeX;
	}

	public void setItudeX(Double itudeX) {

		this.itudeX = itudeX;
	}

	public Double getItudeY() {

		return itudeY;
	}

	public void setItudeY(Double itudeY) {

		this.itudeY = itudeY;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getAttachs() {
	
		return attachs;
	}

	public void setAttachs(String attachs) {
	
		this.attachs = attachs;
	}


}
