
package com.yundao.tenant.app.dto.roadshow;

/**
 * Function: Reason: Date: 2017年11月9日 上午11:40:58
 * 
 * @author wucq
 * @version
 */
public class RoadshowResDto extends BaseRoadshow {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 平台名
	 */
	private String platformName;
	/**
	 * 平台code
	 */
	private String platformCode;
	/**
	 * 栏目名
	 */
	private String columnNames;
	/**
	 * 产品名
	 */
	private String productName;
	/**
	 * 产品微信简版内容
	 */
	private String wxContent;
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 产品发行状态
	 */
	private Integer issuedStatus;
	/**
	 * 发行状态中文
	 */
	private String issuedStatusText;
	/**
	 * 状态中文
	 */
	private String statusText;
	/**
	 * 操作人
	 */
	private String updateUserName;
	/**
	 * 视频信息
	 */
	private VideoDetailResDto video;

	public String getPlatformName() {

		return platformName;
	}

	public void setPlatformName(String platformName) {

		this.platformName = platformName;
	}

	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getStatusText() {

		return statusText;
	}

	public void setStatusText(String statusText) {

		this.statusText = statusText;
	}


	public String getUpdateUserName() {
	
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
	
		this.updateUserName = updateUserName;
	}

	public String getPlatformCode() {

		return platformCode;
	}

	public void setPlatformCode(String platformCode) {

		this.platformCode = platformCode;
	}

	public String getColumnNames() {

		return columnNames;
	}

	public void setColumnNames(String columnNames) {

		this.columnNames = columnNames;
	}

	public String getProductId() {

		return productId;
	}

	public void setProductId(String productId) {

		this.productId = productId;
	}

	public VideoDetailResDto getVideo() {

		return video;
	}

	public void setVideo(VideoDetailResDto video) {

		this.video = video;
	}

	public Integer getIssuedStatus() {
	
		return issuedStatus;
	}

	public void setIssuedStatus(Integer issuedStatus) {
	
		this.issuedStatus = issuedStatus;
	}

	public String getIssuedStatusText() {
	
		return issuedStatusText;
	}

	public void setIssuedStatusText(String issuedStatusText) {
	
		this.issuedStatusText = issuedStatusText;
	}

	public String getWxContent() {
	
		return wxContent;
	}

	public void setWxContent(String wxContent) {
	
		this.wxContent = wxContent;
	}
	

}
