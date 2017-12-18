
package com.yundao.tenant.app.view.customer;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3020View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户名
	 */
	private Long customerId;
	/**
	 * 客户名
	 */
	private String customerName;
	/**
	 * 客户编号
	 */
	private String customerNo;

	/**
	 * 成交情况文本
	 */
	private String bargain;
	/**
	 * 级别文本
	 */
	private String level;
	/**
	 * 创建时间
	 */
	private String crateTime;
	
	/**
	 * 创建者
	 */
	private String creator;



	public Long getCustomerId() {
	
		return customerId;
	}

	public void setCustomerId(Long customerId) {
	
		this.customerId = customerId;
	}

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

	public String getBargain() {
	
		return bargain;
	}

	public void setBargain(String bargain) {
	
		this.bargain = bargain;
	}

	public String getLevel() {
	
		return level;
	}

	public void setLevel(String level) {
	
		this.level = level;
	}

	public String getCrateTime() {
	
		return crateTime;
	}

	public void setCrateTime(String crateTime) {
	
		this.crateTime = crateTime;
	}

	public String getCreator() {
	
		return creator;
	}

	public void setCreator(String creator) {
	
		this.creator = creator;
	}
    
}
