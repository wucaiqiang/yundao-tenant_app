

package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 下午2:37:20 
 * @author   wucq
 * @version   
 */
public class Structure1008View implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<CommissionRuleView> classList;
	public Structure1008View(){}

	public Structure1008View(List<CommissionRuleView> classList) {
		this.classList=classList;
	}

	public List<CommissionRuleView> getClassList() {
	
		return classList;
	}
	public void setClassList(List<CommissionRuleView> classList) {
	
		this.classList = classList;
	}

	
	

}

