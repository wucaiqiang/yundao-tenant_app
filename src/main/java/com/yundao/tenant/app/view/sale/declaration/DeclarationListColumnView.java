
package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月16日 下午4:25:42
 * 
 * @author wucq
 * @version
 */
public class DeclarationListColumnView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<NameIdView> orderType;

	public DeclarationListColumnView() {
	}

	public DeclarationListColumnView(List<NameIdView> orderType) {
		this.orderType = orderType;
	}

	public List<NameIdView> getOrderType() {

		return orderType;
	}

	public void setOrderType(List<NameIdView> orderType) {

		this.orderType = orderType;
	}
}
