

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:36:08 
 * @author   wucq
 * @version   
 */
public class TextView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	public TextView(){}
	public TextView(String text){
		this.text=text;
	}
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	
}

