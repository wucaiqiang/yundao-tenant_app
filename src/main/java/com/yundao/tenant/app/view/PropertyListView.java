

package com.yundao.tenant.app.view;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:35:18 
 * @author   wucq
 * @version   
 */
public class PropertyListView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<NameValueView> values;
	public PropertyListView(){}

	public PropertyListView(List<NameValueView> values ) {
		this.values=values;
	}

	public List<NameValueView> getValues() {
		return values;
	}
	public void setValues(List<NameValueView> values) {
	
		this.values = values;
	}

}

