
package com.yundao.tenant.app.view.customer;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月18日 上午10:38:43
 * 
 * @author wucq
 * @version
 */
public class CustomerDetailHeaderView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String mobile;
	private String mobileStr;
	private String customerLevel;
	private String riskAspect;
	private List<String> investLikeList;
	private Boolean hasFocus;
	private List<NameIdView> tagList;
	/**
	 * 客户查看权限
	 */
	private boolean readCustomerPermission;
	/**
	 * 客户编辑权限
	 */
	private boolean editCustomerPermission;
	/**
	 * 客户联系信息查看权限
	 */
	private boolean readCustomerContactInfoPermission;
	/**
	 * 客户联系信息编辑权限
	 */
	private boolean editCustomerContactInfoPermission;
	/**
	 * 该客户是否属于当前用户
	 */
	private Boolean isBelongTo;
	/**
	 * 理财师名称
	 */
	private String afpName;
	
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public String getMobile() {
	
		return mobile;
	}
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	public String getMobileStr() {
	
		return mobileStr;
	}
	public void setMobileStr(String mobileStr) {
	
		this.mobileStr = mobileStr;
	}
	public String getCustomerLevel() {
	
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
	
		this.customerLevel = customerLevel;
	}
	public String getRiskAspect() {
	
		return riskAspect;
	}
	public void setRiskAspect(String riskAspect) {
	
		this.riskAspect = riskAspect;
	}
	public List<String> getInvestLikeList() {
	
		return investLikeList;
	}
	public void setInvestLikeList(List<String> investLikeList) {
	
		this.investLikeList = investLikeList;
	}
	public Boolean getHasFocus() {
	
		return hasFocus;
	}
	public void setHasFocus(Boolean hasFocus) {
	
		this.hasFocus = hasFocus;
	}
	public List<NameIdView> getTagList() {
	
		return tagList;
	}
	public void setTagList(List<NameIdView> tagList) {
	
		this.tagList = tagList;
	}
	public boolean isReadCustomerPermission() {
	
		return readCustomerPermission;
	}
	public void setReadCustomerPermission(boolean readCustomerPermission) {
	
		this.readCustomerPermission = readCustomerPermission;
	}
	public boolean isEditCustomerPermission() {
	
		return editCustomerPermission;
	}
	public void setEditCustomerPermission(boolean editCustomerPermission) {
	
		this.editCustomerPermission = editCustomerPermission;
	}
	public boolean isReadCustomerContactInfoPermission() {
	
		return readCustomerContactInfoPermission;
	}
	public void setReadCustomerContactInfoPermission(boolean readCustomerContactInfoPermission) {
	
		this.readCustomerContactInfoPermission = readCustomerContactInfoPermission;
	}
	public boolean isEditCustomerContactInfoPermission() {
	
		return editCustomerContactInfoPermission;
	}
	public void setEditCustomerContactInfoPermission(boolean editCustomerContactInfoPermission) {
	
		this.editCustomerContactInfoPermission = editCustomerContactInfoPermission;
	}
	public Boolean getIsBelongTo() {
	
		return isBelongTo;
	}
	public void setIsBelongTo(Boolean isBelongTo) {
	
		this.isBelongTo = isBelongTo;
	}
	public String getAfpName() {
	
		return afpName;
	}
	public void setAfpName(String afpName) {
	
		this.afpName = afpName;
	}
	

}
