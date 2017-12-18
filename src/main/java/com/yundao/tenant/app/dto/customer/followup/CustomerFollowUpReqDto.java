
package com.yundao.tenant.app.dto.customer.followup;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年8月24日 下午2:06:53
 * 
 * @author wucq
 * @version
 */
public class CustomerFollowUpReqDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private Long customerId;
	private String followType;
	private String followStatus;
	private String content;
	private Long nextFollowDate;
	private String pictureList;
	@ApiModelProperty(value = "地图类型")
	private String mapType;
	@ApiModelProperty(value = "经度")
	private Double longitude;
	@ApiModelProperty(value = "纬度")
	private Double latitude;
	@ApiModelProperty(value = "地址")
	private String address;

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public String getFollowType() {

		return followType;
	}

	public void setFollowType(String followType) {

		this.followType = followType;
	}

	public String getFollowStatus() {

		return followStatus;
	}

	public void setFollowStatus(String followStatus) {

		this.followStatus = followStatus;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public Long getNextFollowDate() {

		return nextFollowDate;
	}

	public void setNextFollowDate(Long nextFollowDate) {

		this.nextFollowDate = nextFollowDate;
	}


	public String getPictureList() {
	
		return pictureList;
	}

	public void setPictureList(String pictureList) {
	
		this.pictureList = pictureList;
	}

	public String getMapType() {
	
		return mapType;
	}

	public void setMapType(String mapType) {
	
		this.mapType = mapType;
	}

	public Double getLongitude() {

		return longitude;
	}

	public void setLongitude(Double longitude) {

		this.longitude = longitude;
	}

	public Double getLatitude() {

		return latitude;
	}

	public void setLatitude(Double latitude) {

		this.latitude = latitude;
	}

	public String getAddress() {
	
		return address;
	}

	public void setAddress(String address) {
	
		this.address = address;
	}

}
