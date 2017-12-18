
package com.yundao.tenant.app.view.customer;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3008View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户名
	 */
	private String customerName;
	/**
	 * 客户手机号码
	 */
	private String customerMobile;
	/**
	 * 分配时间
	 */
	private String bottomText;
	/**
	 * 是否关注
	 */
	private Boolean isConcern;
	/**
	 * 客户ID
	 */
	private String customerId;
	/**
	 * 是否是自己的客户
	 */
	private Boolean isBelongTo;
	/**
	 * 客户标签
	 */
	private List<String> tags;

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getCustomerMobile() {

		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {

		this.customerMobile = customerMobile;
	}

	public String getBottomText() {

		return bottomText;
	}

	public void setBottomText(String bottomText) {

		this.bottomText = bottomText;
	}

	public Boolean getIsConcern() {

		return isConcern;
	}

	public void setIsConcern(Boolean isConcern) {

		this.isConcern = isConcern;
	}

	public String getCustomerId() {

		return customerId;
	}

	public void setCustomerId(String customerId) {

		this.customerId = customerId;
	}

	public Boolean getIsBelongTo() {

		return isBelongTo;
	}

	public void setIsBelongTo(Boolean isBelongTo) {

		this.isBelongTo = isBelongTo;
	}

	public List<String> getTags() {
	
		return tags;
	}

	public void setTags(List<String> tags) {
	
		this.tags = tags;
	}

}
