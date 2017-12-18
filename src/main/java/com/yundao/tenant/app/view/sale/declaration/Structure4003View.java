
package com.yundao.tenant.app.view.sale.declaration;

import java.util.Date;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.util.DateFormatUtils;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure4003View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 报单Id
	 */
	private String orderId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 当前预约状态
	 */
	private String stateText;
	/**
	 * 左边值
	 */
	private String leftValue;
	/**
	 * 左边名
	 */
	private String leftName;
	/**
	 * 右边值
	 */
	private Date rightValue;
	/**
	 * 右边名
	 */
	private String rightName;
	/**
	 * 报单日期
	 */
	private Date orderDateText;
	/**
	 * 底部右下值
	 */
	private String orderCertificateText;
	/**
	 * 打款凭证是否已上传
	 */
	private Boolean orderCertificateState;

	public String getOrderId() {

		return orderId;
	}

	public void setOrderId(String orderId) {

		this.orderId = orderId;
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

	public String getStateText() {

		return stateText;
	}

	public void setStateText(String stateText) {

		this.stateText = stateText;
	}

	public String getLeftValue() {

		return leftValue;
	}

	public void setLeftValue(String leftValue) {

		this.leftValue = leftValue;
	}

	public String getLeftName() {

		return leftName;
	}

	public void setLeftName(String leftName) {

		this.leftName = leftName;
	}

	public String getRightValue() {
		if (rightValue != null) {
			return DateFormatUtils.format(rightValue, "yyyy-MM-dd");
		}
		return "";
	}

	public void setRightValue(Date rightValue) {

		this.rightValue = rightValue;
	}

	public String getRightName() {

		return rightName;
	}

	public void setRightName(String rightName) {

		this.rightName = rightName;
	}


	public String getOrderDateText() {
	
		if (orderDateText != null) {
			return "报单时间 "+DateFormatUtils.format(orderDateText, "yyyy-MM-dd HH:mm");
		}
		
		return "";
	}

	public void setOrderDateText(Date orderDateText) {
	
		this.orderDateText = orderDateText;
	}

	public String getOrderCertificateText() {

		return orderCertificateText;
	}

	public void setOrderCertificateText(String orderCertificateText) {

		this.orderCertificateText = orderCertificateText;
	}

	public Boolean getOrderCertificateState() {

		return orderCertificateState;
	}

	public void setOrderCertificateState(Boolean orderCertificateState) {

		this.orderCertificateState = orderCertificateState;
	}
}
