
package com.yundao.tenant.app.view.customer;

import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月16日 下午4:25:42
 * 
 * @author wucq
 * @version
 */
public class CustomerListColumnView {
	private List<NameIdView> customerType;

	public CustomerListColumnView() {
	}

	public CustomerListColumnView(List<NameIdView> customerType) {
		this.customerType = customerType;
	}

	public List<NameIdView> getCustomerType() {

		return customerType;
	}

	public void setCustomerType(List<NameIdView> customerType) {

		this.customerType = customerType;
	}

}
