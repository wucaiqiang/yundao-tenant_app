

package com.yundao.tenant.app.dto.customer;

import com.yundao.tenant.app.model.customer.investtype.CustomerInvestTypeModel;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月10日 下午4:03:27 
 * @author   wucq
 * @version   
 */
public class CustomerInvestTypeDto extends CustomerInvestTypeModel {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 投资偏好中文名
	 */
	private String productTypeIdText;
	public String getProductTypeIdText() {
	
		return productTypeIdText;
	}
	public void setProductTypeIdText(String productTypeIdText) {
	
		this.productTypeIdText = productTypeIdText;
	}
	

}

