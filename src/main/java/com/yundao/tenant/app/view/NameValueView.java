

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:36:08 
 * @author   wucq
 * @version   
 */
public class NameValueView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Object value;
	public NameValueView(){}

	public NameValueView(String name,Object value) {
		this.name=name;
		this.value=value;
	}

	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}

	public Object getValue() {
	
		return value;
	}

	public void setValue(Object value) {
	
		this.value = value;
	}
	

}

