
package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年9月2日 下午4:55:21
 * 
 * @author wucq
 * @version
 */
public class DeclarationCustomerView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 成单理财师
	 */
	private String doneOrderManagerName;
	/**
	 * 证件类型
	 */
	private NameIdView certificateType;
	/**
	 * 证件类型集合
	 */
	private List<NameIdView> certificateTypeList;
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
	 * 资产证明图片数组
	 */
	private List<String> assetsCertificateUrlList;
	public String getCustomerName() {
	
		return customerName;
	}
	public void setCustomerName(String customerName) {
	
		this.customerName = customerName;
	}
	public String getDoneOrderManagerName() {
	
		return doneOrderManagerName;
	}
	public void setDoneOrderManagerName(String doneOrderManagerName) {
	
		this.doneOrderManagerName = doneOrderManagerName;
	}
	public NameIdView getCertificateType() {
	
		return certificateType;
	}
	public void setCertificateType(NameIdView certificateType) {
	
		this.certificateType = certificateType;
	}
	public List<NameIdView> getCertificateTypeList() {
	
		return certificateTypeList;
	}
	public void setCertificateTypeList(List<NameIdView> certificateTypeList) {
	
		this.certificateTypeList = certificateTypeList;
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
	public List<String> getAssetsCertificateUrlList() {
	
		return assetsCertificateUrlList;
	}
	public void setAssetsCertificateUrlList(List<String> assetsCertificateUrlList) {
	
		this.assetsCertificateUrlList = assetsCertificateUrlList;
	}
	
	
}
