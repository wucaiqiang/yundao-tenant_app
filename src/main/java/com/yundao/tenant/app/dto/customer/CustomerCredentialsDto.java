

package com.yundao.tenant.app.dto.customer;

import com.yundao.tenant.app.model.customer.credential.CustomerCredentialsModel;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月10日 上午11:35:13 
 * @author   wucq
 * @version   
 */
public class CustomerCredentialsDto extends CustomerCredentialsModel {
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	 private String typeText;
	public String getTypeText() {
	
		return typeText;
	}
	public void setTypeText(String typeText) {
	
		this.typeText = typeText;
	}
	

}

