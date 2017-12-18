
package com.yundao.tenant.app.view.customer;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3001View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户名
	 */
	private String name;
	/**
	 * 客户手机号码
	 */
	private String mobile;
	/**
	 * 客户手机号 进行3-4-4分开
	 */
	private String mobileText;
	/**
	 * 客户ID
	 */
	private String customerId;
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
	public String getMobileText() {
	
		return mobileText;
	}
	public void setMobileText(String mobileText) {
	
		this.mobileText = mobileText;
	}
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}


}
