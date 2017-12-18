

package com.yundao.tenant.app.dto.customer.attach;

import com.yundao.tenant.app.model.customer.attach.CustomerAttachModel;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月10日 下午2:53:41 
 * @author   wucq
 * @version   
 */
public class CustomerAttachDto extends CustomerAttachModel {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 附件类型中文名
	 */
	private String typeText;
	public String getTypeText() {
	
		return typeText;
	}
	public void setTypeText(String typeText) {
	
		this.typeText = typeText;
	}
	

}

