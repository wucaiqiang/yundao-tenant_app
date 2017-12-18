
package com.yundao.tenant.app.dto.customer;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年8月24日 下午3:55:18
 * 
 * @author wucq
 * @version
 */
public class CustomerAddReqDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "姓名")
	private String customerName;
	@ApiModelProperty(value = "手机号码")
	private String customerMobile;
	@ApiModelProperty(value = "性别")
	private String customerGender;
	@ApiModelProperty(value = "客户级别")
	private String customerLevel;
	@ApiModelProperty(value = "客户标签")
	private String customertags;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "公司名")
	private String company;
	@ApiModelProperty(value = "职位")
	private String job;

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getCustomerMobile() {

		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {

		this.customerMobile = customerMobile;
	}

	public String getCustomerGender() {

		return customerGender;
	}

	public void setCustomerGender(String customerGender) {

		this.customerGender = customerGender;
	}

	public String getCustomertags() {

		return customertags;
	}

	public void setCustomertags(String customertags) {

		this.customertags = customertags;
	}

	public String getRemark() {

		return remark;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}

	public String getCustomerLevel() {

		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {

		this.customerLevel = customerLevel;
	}

	public String getEmail() {
	
		return email;
	}

	public void setEmail(String email) {
	
		this.email = email;
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
	

}
