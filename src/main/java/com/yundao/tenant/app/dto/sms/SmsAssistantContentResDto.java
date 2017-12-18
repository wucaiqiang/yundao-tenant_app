

package com.yundao.tenant.app.dto.sms;

import java.util.List;

import com.yundao.tenant.app.model.customer.BaseCustomer;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月29日 下午6:23:31 
 * @author   wucq
 * @version   
 */
public class SmsAssistantContentResDto extends BaseSmsAssistantContent {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BaseCustomer> customers;

	public List<BaseCustomer> getCustomers() {
	
		return customers;
	}

	public void setCustomers(List<BaseCustomer> customers) {
	
		this.customers = customers;
	}
	

}

