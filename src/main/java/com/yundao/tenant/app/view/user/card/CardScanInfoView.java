
package com.yundao.tenant.app.view.user.card;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:07:34
 * 
 * @author wucq
 * @version
 */
public class CardScanInfoView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 名片上的姓名
	 */
	private String name;
	/**
	 * 名片上的手机号
	 */
	private String mobile;
	/**
	 * 名片上的公司名
	 */
	private String company;
	/**
	 * 名片上的职位
	 */
	private String job;
	/**
	 * 名片上的手机号
	 */
	private String email;
	/**
	 * 地址
	 */
	private String address;

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

	public String getCompany() {

		return company;
	}

	public void setCompany(String company) {

		this.company = company;
	}

	public String getJob() {

		return job;
	}

	public void setJob(String job) {

		this.job = job;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getAddress() {
	
		return address;
	}

	public void setAddress(String address) {
	
		this.address = address;
	}
	

}
