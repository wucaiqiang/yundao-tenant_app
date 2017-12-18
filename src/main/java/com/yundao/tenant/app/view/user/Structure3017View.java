

package com.yundao.tenant.app.view.user;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:36:08 
 * @author   wucq
 * @version   
 */
public class Structure3017View implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String employeeId;
	private String mobile;
	public Structure3017View(){}

	public Structure3017View(String name,String employeeId) {
		this.name=name;
		this.employeeId=employeeId;
	}

	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}

	public String getEmployeeId() {
	
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
	
		this.employeeId = employeeId;
	}

	public String getMobile() {
	
		return mobile;
	}

	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}


}

