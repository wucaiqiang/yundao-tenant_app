

package com.yundao.tenant.app.view.customer;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月29日 下午7:38:21 
 * @author   wucq
 * @version   
 */
public class UserCustomerFocusView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private Boolean concernStatus;
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
	public Boolean getConcernStatus() {
	
		return concernStatus;
	}
	public void setConcernStatus(Boolean concernStatus) {
	
		this.concernStatus = concernStatus;
	}
	

}

