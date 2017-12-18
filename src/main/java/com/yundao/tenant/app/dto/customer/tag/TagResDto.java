

package com.yundao.tenant.app.dto.customer.tag;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年12月4日 下午3:25:03 
 * @author   wucq
 * @version   
 */
public class TagResDto extends BaseTag {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户ID
	 */
	private Long customerId;
	public Long getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(Long customerId) {
	
		this.customerId = customerId;
	}
	

}

