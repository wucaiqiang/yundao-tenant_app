

package com.yundao.tenant.app.dto.customer;

import java.io.Serializable;

/**
 * Function: 客户基本信息
 * Reason:	  
 * Date:     2017年8月21日 下午4:21:00 
 * @author   wucq
 * @version   
 */
public class CustomerInfoReqDto implements Serializable {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 客户级别
	 */
	private String customerLevel;
	/**
	 * 生日
	 */
	private Long birthday;
	/**
	 * 来源
	 */
	private String from;
	/**
	 * 证件类型
	 */
	private String certificateType;
	/**
	 * 证件号码
	 */
	private String certificateNumber;
	/**
	 * 证件正面照
	 */
	private String identityFrontCardUrl;
	/**
	 * 证件反面照
	 */
	private String identityBackCardUrl;
	/**
	 * 资产证明照片
	 */
	private String assetsCertificateUrlList;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 投资偏好
	 */
	private String investLike;
	/**
	 * 投资人类型
	 */
	private String investManType;
	/**
	 * 风险特征
	 */
	private String riskType;
	public String getTags() {
	
		return tags;
	}
	public void setTags(String tags) {
	
		this.tags = tags;
	}
	public String getGender() {
	
		return gender;
	}
	public void setGender(String gender) {
	
		this.gender = gender;
	}
	public String getCustomerLevel() {
	
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
	
		this.customerLevel = customerLevel;
	}
	public Long getBirthday() {
	
		return birthday;
	}
	public void setBirthday(Long birthday) {
	
		this.birthday = birthday;
	}
	public String getFrom() {
	
		return from;
	}
	public void setFrom(String from) {
	
		this.from = from;
	}
	public String getCertificateType() {
	
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
	
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
	
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
	
		this.certificateNumber = certificateNumber;
	}
	public String getIdentityFrontCardUrl() {
	
		return identityFrontCardUrl;
	}
	public void setIdentityFrontCardUrl(String identityFrontCardUrl) {
	
		this.identityFrontCardUrl = identityFrontCardUrl;
	}
	public String getIdentityBackCardUrl() {
	
		return identityBackCardUrl;
	}
	public void setIdentityBackCardUrl(String identityBackCardUrl) {
	
		this.identityBackCardUrl = identityBackCardUrl;
	}
	public String getAssetsCertificateUrlList() {
	
		return assetsCertificateUrlList;
	}
	public void setAssetsCertificateUrlList(String assetsCertificateUrlList) {
	
		this.assetsCertificateUrlList = assetsCertificateUrlList;
	}
	public String getRemark() {
	
		return remark;
	}
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	public String getInvestLike() {
	
		return investLike;
	}
	public void setInvestLike(String investLike) {
	
		this.investLike = investLike;
	}
	public String getInvestManType() {
	
		return investManType;
	}
	public void setInvestManType(String investManType) {
	
		this.investManType = investManType;
	}
	public String getRiskType() {
	
		return riskType;
	}
	public void setRiskType(String riskType) {
	
		this.riskType = riskType;
	}

}

