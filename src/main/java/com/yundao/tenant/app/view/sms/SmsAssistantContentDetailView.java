
package com.yundao.tenant.app.view.sms;

import java.io.Serializable;
import java.util.List;

/**
 * Function: Reason: Date: 2017年11月30日 下午5:50:55
 * 
 * @author wucq
 * @version
 */
public class SmsAssistantContentDetailView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String smsId;
	private String honour;
	private String sign;
	private String content;
	private List<SmsAssistantCustomerView> customerList;
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public String getSmsId() {
	
		return smsId;
	}
	public void setSmsId(String smsId) {
	
		this.smsId = smsId;
	}
	public String getHonour() {
	
		return honour;
	}
	public void setHonour(String honour) {
	
		this.honour = honour;
	}
	public String getSign() {
	
		return sign;
	}
	public void setSign(String sign) {
	
		this.sign = sign;
	}
	public List<SmsAssistantCustomerView> getCustomerList() {
	
		return customerList;
	}
	public void setCustomerList(List<SmsAssistantCustomerView> customerList) {
	
		this.customerList = customerList;
	}
	
}
