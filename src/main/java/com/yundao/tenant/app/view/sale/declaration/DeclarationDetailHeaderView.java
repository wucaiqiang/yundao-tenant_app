

package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;

import com.yundao.tenant.app.dto.common.ActionDTO;


/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月2日 下午4:55:21 
 * @author   wucq
 * @version   
 */
public class DeclarationDetailHeaderView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 报单编号
	 */
	private String orderNOText;
	/**
	 * 认购金额
	 */
	private String renGouAmountText;
	/**
	 * 当前报单的状态.
	 */
	private String stateText;
	/**
	 * 当前报单的状态.
	 */
	private Integer state;
	/**
	 * 0:未提交申请退款，1：申请退款中 2：申请退款被驳回，这个要state==2，才有效
	 */
	private int applyRefundStatus;
	/**
	 * 申请退款被驳回原因
	 */
	private String applyRefundRejectReason;
	/**
	 * 申请退款原因
	 */
	private String applyRefundReason;
	/**
	 * 当前用户是否能取消申请退款
	 */
	private boolean canCancelRefund;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 预约额度
	 */
	private String appointAmountText;
	/**
	 * 产品详情的动作
	 */
	private ActionDTO productAction;
	/**
	 * 报单 已驳回,已取消，已作废的原因
	 */
	private String cancelReson;
	/**
	 * 是否有权限-客户-编辑 权限
	 */
	private Boolean customerEditPermission;
	/**
	 * 是否有权限-客户-编辑 权限
	 */
	private Boolean customerReadPermission;
	/**
	 * 是否有权限-客户-编辑 权限
	 */
	private Boolean orderEditPermission;
	public String getOrderNOText() {
	
		return orderNOText;
	}
	public void setOrderNOText(String orderNOText) {
	
		this.orderNOText = orderNOText;
	}
	public String getRenGouAmountText() {
	
		return renGouAmountText;
	}
	public void setRenGouAmountText(String renGouAmountText) {
	
		this.renGouAmountText = renGouAmountText;
	}
	public String getStateText() {
	
		return stateText;
	}
	public void setStateText(String stateText) {
	
		this.stateText = stateText;
	}
	
	public Integer getState() {
	
		return state;
	}
	public void setState(Integer state) {
	
		this.state = state;
	}
	public String getCustomerName() {
	
		return customerName;
	}
	public void setCustomerName(String customerName) {
	
		this.customerName = customerName;
	}
	public String getProductName() {
	
		return productName;
	}
	public void setProductName(String productName) {
	
		this.productName = productName;
	}
	public String getAppointAmountText() {
	
		return appointAmountText;
	}
	public void setAppointAmountText(String appointAmountText) {
	
		this.appointAmountText = appointAmountText;
	}
	public String getCancelReson() {
	
		return cancelReson;
	}
	public void setCancelReson(String cancelReson) {
	
		this.cancelReson = cancelReson;
	}
	public Boolean getCustomerEditPermission() {
	
		return customerEditPermission;
	}
	public void setCustomerEditPermission(Boolean customerEditPermission) {
	
		this.customerEditPermission = customerEditPermission;
	}
	public Boolean getCustomerReadPermission() {
	
		return customerReadPermission;
	}
	public void setCustomerReadPermission(Boolean customerReadPermission) {
	
		this.customerReadPermission = customerReadPermission;
	}
	public Boolean getOrderEditPermission() {
	
		return orderEditPermission;
	}
	public void setOrderEditPermission(Boolean orderEditPermission) {
	
		this.orderEditPermission = orderEditPermission;
	}
	public ActionDTO getProductAction() {
	
		return productAction;
	}
	public void setProductAction(ActionDTO productAction) {
	
		this.productAction = productAction;
	}
	public int getApplyRefundStatus() {
	
		return applyRefundStatus;
	}
	public void setApplyRefundStatus(int applyRefundStatus) {
	
		this.applyRefundStatus = applyRefundStatus;
	}
	public String getApplyRefundRejectReason() {
	
		return applyRefundRejectReason;
	}
	public void setApplyRefundRejectReason(String applyRefundRejectReason) {
	
		this.applyRefundRejectReason = applyRefundRejectReason;
	}
	public boolean isCanCancelRefund() {
	
		return canCancelRefund;
	}
	public void setCanCancelRefund(boolean canCancelRefund) {
	
		this.canCancelRefund = canCancelRefund;
	}
	public String getApplyRefundReason() {
	
		return applyRefundReason;
	}
	public void setApplyRefundReason(String applyRefundReason) {
	
		this.applyRefundReason = applyRefundReason;
	}

}

