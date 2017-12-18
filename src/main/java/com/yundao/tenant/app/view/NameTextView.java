

package com.yundao.tenant.app.view;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月19日 下午8:13:20 
 * @author   wucq
 * @version   
 */
public class NameTextView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String text;
	public NameTextView(){}
	public NameTextView(String name,String text){
		this.name=name;
		this.text=text;
	}
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	

}

