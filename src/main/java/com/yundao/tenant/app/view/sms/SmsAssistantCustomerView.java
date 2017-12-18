
package com.yundao.tenant.app.view.sms;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年12月1日 下午2:40:16
 * 
 * @author wucq
 * @version
 */
public class SmsAssistantCustomerView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String customerId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 客户用于显示的手机号 用-分割
	 */
	private String mobileText;
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
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
	

}
