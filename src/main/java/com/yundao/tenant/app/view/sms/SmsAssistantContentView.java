
package com.yundao.tenant.app.view.sms;

import java.io.Serializable;
import java.util.List;

/**
 * Function: Reason: Date: 2017年11月30日 下午5:50:55
 * 
 * @author wucq
 * @version
 */
public class SmsAssistantContentView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private List<SmsAssistantCustomerView> customerList;
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public List<SmsAssistantCustomerView> getCustomerList() {
	
		return customerList;
	}
	public void setCustomerList(List<SmsAssistantCustomerView> customerList) {
	
		this.customerList = customerList;
	}
	
}
