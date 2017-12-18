

package com.yundao.tenant.app.view.sms;

import java.io.Serializable;
import java.util.List;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年12月9日 下午2:15:10 
 * @author   wucq
 * @version   
 */
public class SmsAssistantStayAlertView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String smsId;
	private long time;
	private List<String> customerNameList;
	private String content;
	public String getSmsId() {
	
		return smsId;
	}
	public void setSmsId(String smsId) {
	
		this.smsId = smsId;
	}
	public long getTime() {
	
		return time;
	}
	public void setTime(long time) {
	
		this.time = time;
	}
	public List<String> getCustomerNameList() {
	
		return customerNameList;
	}
	public void setCustomerNameList(List<String> customerNameList) {
	
		this.customerNameList = customerNameList;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	

}

