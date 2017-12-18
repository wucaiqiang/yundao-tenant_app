
package com.yundao.tenant.app.view.customer;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3014View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户名
	 */
	private String customerId;
	/**
	 * 客户名
	 */
	private String customerName;
	/**
	 * 客户编号
	 */
	private String customerNo;
	/**
	 * 是否能领取
	 */
	private Boolean canGet = true;
	
	public String getCustomerName() {
	
		return customerName;
	}
	public void setCustomerName(String customerName) {
	
		this.customerName = customerName;
	}
	public String getCustomerNo() {
	
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
	
		this.customerNo = customerNo;
	}
	public Boolean getCanGet() {
	
		return canGet;
	}
	public void setCanGet(Boolean canGet) {
	
		this.canGet = canGet;
	}
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
	

}
