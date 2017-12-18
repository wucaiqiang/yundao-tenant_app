

package com.yundao.tenant.app.dto.sale.declaration;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月6日 下午4:18:41 
 * @author   wucq
 * @version   
 */
public class DeclarationAddAndResubmitReqDto implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预约Id
	 */
	private Long appointId;
	/**
	 * 报单Id
	 */
	private Long orderId;
	/**
	 * 认购金额
	 */
	private String renGouAmount;
	/**
	 * 打款时间 时间戳
	 */
	private Long giveAmountDate;
	/**
	 * 备注
	 */
	private String remark;
	public Long getAppointId() {
	
		return appointId;
	}
	public void setAppointId(Long appointId) {
	
		this.appointId = appointId;
	}
	public String getRenGouAmount() {
	
		return renGouAmount;
	}
	public void setRenGouAmount(String renGouAmount) {
	
		this.renGouAmount = renGouAmount;
	}
	public Long getGiveAmountDate() {
	
		return giveAmountDate;
	}
	public void setGiveAmountDate(Long giveAmountDate) {
	
		this.giveAmountDate = giveAmountDate;
	}
	public String getRemark() {
	
		return remark;
	}
	public void setRemark(String remark) {
	
		this.remark = remark;
	}
	public Long getOrderId() {
	
		return orderId;
	}
	public void setOrderId(Long orderId) {
	
		this.orderId = orderId;
	}

}

