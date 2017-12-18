
package com.yundao.tenant.app.view.customer;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月27日 上午11:39:14
 * 
 * @author wucq
 * @version
 */

public class NameFailReasonView implements Serializable {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 失败原因 ： e.g. 手机号码已被已有客户使用
	 */
	private String failReason;

	public NameFailReasonView() {
	}

	public NameFailReasonView(String name, String failReason) {
		this.name = name;
		this.failReason = failReason;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getFailReason() {

		return failReason;
	}

	public void setFailReason(String failReason) {

		this.failReason = failReason;
	}

}
