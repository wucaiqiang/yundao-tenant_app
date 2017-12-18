
package com.yundao.tenant.app.view.sale.reservation;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年10月20日 下午5:49:07
 * 
 * @author wucq
 * @version
 */
public class DeclarationReservationSelectedView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Boolean hasNext;
	/**
	 * 预约标题
	 */
	private String appointTitle;
	private String appointId;
	private String customerName;
	private String appointAmount;

	public Boolean getHasNext() {

		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {

		this.hasNext = hasNext;
	}

	public String getAppointId() {

		return appointId;
	}

	public void setAppointId(String appointId) {

		this.appointId = appointId;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getAppointAmount() {

		return appointAmount;
	}

	public void setAppointAmount(String appointAmount) {

		this.appointAmount = appointAmount;
	}

	public String getAppointTitle() {
	
		return appointTitle;
	}

	public void setAppointTitle(String appointTitle) {
	
		this.appointTitle = appointTitle;
	}
	

}
