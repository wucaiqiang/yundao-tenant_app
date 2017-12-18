

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:36:08 
 * @author   wucq
 * @version   
 */
public class NameIdView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	public NameIdView(){}

	public NameIdView(String name,String id) {
		this.name=name;
		this.id=id;
	}

	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}

	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}


	

}

