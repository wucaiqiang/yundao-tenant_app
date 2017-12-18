

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
public class Structure1007View implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<IncomeRuleView> classList;
	public Structure1007View(){}

	public Structure1007View(List<IncomeRuleView> ruleViews) {
		this.classList=ruleViews;
	}

	public List<IncomeRuleView> getClassList() {
	
		return classList;
	}
	public void setClassList(List<IncomeRuleView> classList) {
	
		this.classList = classList;
	}
	

}

