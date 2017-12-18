
package com.yundao.tenant.app.view.customer;

import java.util.Date;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.util.DateFormatUtils;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3005View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预约或报单
	 */
	private String from;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 如果是预约的则显示预约日期，如果是报单的则显示报单日期
	 */
	private Date date;
	/**
	 * 左边标题
	 */
	private String leftName;
	/**
	 * 左边值
	 */
	private String leftValue;
	/**
	 * 中间标题
	 */
	private String middleName;
	/**
	 * 中间值
	 */
	private String middleValue;
	/**
	 * 右边标题
	 */
	private String rightName;
	/**
	 * 右边值
	 */
	private String rightValue;

	public String getFrom() {

		return from;
	}

	public void setFrom(String from) {

		this.from = from;
	}

	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getDate() {
		if (date != null) {
           return DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return "";
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public String getLeftName() {

		return leftName;
	}

	public void setLeftName(String leftName) {

		this.leftName = leftName;
	}

	public String getLeftValue() {

		return leftValue;
	}

	public void setLeftValue(String leftValue) {

		this.leftValue = leftValue;
	}

	public String getMiddleName() {

		return middleName;
	}

	public void setMiddleName(String middleName) {

		this.middleName = middleName;
	}

	public String getMiddleValue() {

		return middleValue;
	}

	public void setMiddleValue(String middleValue) {

		this.middleValue = middleValue;
	}

	public String getRightName() {

		return rightName;
	}

	public void setRightName(String rightName) {

		this.rightName = rightName;
	}

	public String getRightValue() {

		return rightValue;
	}

	public void setRightValue(String rightValue) {

		this.rightValue = rightValue;
	}

}
