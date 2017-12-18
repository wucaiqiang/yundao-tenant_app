
package com.yundao.tenant.app.view.roadshow;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.view.NameValueView;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure6004View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	public Structure6004View(){}
	public Structure6004View(String productName){
		this.productName=productName;
	}
	public String getProductName() {
	
		return productName;
	}
	public void setProductName(String productName) {
	
		this.productName = productName;
	}
	
}
