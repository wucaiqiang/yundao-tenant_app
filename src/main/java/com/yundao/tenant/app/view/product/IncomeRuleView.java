
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class IncomeRuleView implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String className;
	private String amountTitle;
	private String timeLimitTitle;
	private String fixedRateTitle;
	private String floatRateTitle;
	private List<IncomeValueView> values;
	public String getClassName() {
	
		return className;
	}
	public void setClassName(String className) {
	
		this.className = className;
	}
	public String getAmountTitle() {
	
		return amountTitle;
	}
	public void setAmountTitle(String amountTitle) {
	
		this.amountTitle = amountTitle;
	}
	public String getTimeLimitTitle() {
	
		return timeLimitTitle;
	}
	public void setTimeLimitTitle(String timeLimitTitle) {
	
		this.timeLimitTitle = timeLimitTitle;
	}
	public String getFixedRateTitle() {
	
		return fixedRateTitle;
	}
	public void setFixedRateTitle(String fixedRateTitle) {
	
		this.fixedRateTitle = fixedRateTitle;
	}
	public String getFloatRateTitle() {
	
		return floatRateTitle;
	}
	public void setFloatRateTitle(String floatRateTitle) {
	
		this.floatRateTitle = floatRateTitle;
	}
	public List<IncomeValueView> getValues() {
	
		return values;
	}
	public void setValues(List<IncomeValueView> values) {
	
		this.values = values;
	}
	
	
}
