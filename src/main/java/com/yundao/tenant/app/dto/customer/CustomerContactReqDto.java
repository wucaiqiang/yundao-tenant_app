

package com.yundao.tenant.app.dto.customer;

import java.io.Serializable;

/**
 * Function: 客户联系信息
 * Reason:	  
 * Date:     2017年8月21日 下午4:30:21 
 * @author   wucq
 * @version   
 */
public class CustomerContactReqDto implements Serializable {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String customerName;
	private String mobile;
	private String weChat;
	private String qq;
	private String mail;
	private String province;
	private String city;
	private String address;
	private String industry;
	private String organization;
	private String job;
	public String getCustomerName() {
	
		return customerName;
	}
	public void setCustomerName(String customerName) {
	
		this.customerName = customerName;
	}
	public String getMobile() {
	
		return mobile;
	}
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	public String getWeChat() {
	
		return weChat;
	}
	public void setWeChat(String weChat) {
	
		this.weChat = weChat;
	}
	public String getQq() {
	
		return qq;
	}
	public void setQq(String qq) {
	
		this.qq = qq;
	}
	public String getMail() {
	
		return mail;
	}
	public void setMail(String mail) {
	
		this.mail = mail;
	}
	public String getProvince() {
	
		return province;
	}
	public void setProvince(String province) {
	
		this.province = province;
	}
	public String getCity() {
	
		return city;
	}
	public void setCity(String city) {
	
		this.city = city;
	}
	public String getAddress() {
	
		return address;
	}
	public void setAddress(String address) {
	
		this.address = address;
	}
	public String getIndustry() {
	
		return industry;
	}
	public void setIndustry(String industry) {
	
		this.industry = industry;
	}
	public String getOrganization() {
	
		return organization;
	}
	public void setOrganization(String organization) {
	
		this.organization = organization;
	}
	public String getJob() {
	
		return job;
	}
	public void setJob(String job) {
	
		this.job = job;
	}

}

