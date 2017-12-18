

package com.yundao.tenant.app.dto.report.declaration.customer;
/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 下午3:06:14 
 * @author   欧阳利
 * @version   
 */
public class CustomerType {
    private Integer id;
    
    private String name;

	public CustomerType(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
	
		return id;
	}

	public void setId(Integer id) {
	
		this.id = id;
	}

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}
}

