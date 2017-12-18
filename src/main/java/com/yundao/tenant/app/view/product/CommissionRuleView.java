
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.LeftMiddleRightView;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class CommissionRuleView implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String className;
	private String leftTitle;
	private String middleTitle;
	private String rightTitle;
	private List<LeftMiddleRightView> values;
	private String bottomName;
	private String bottomValue;
	public String getLeftTitle() {
	
		return leftTitle;
	}
	public void setLeftTitle(String leftTitle) {
	
		this.leftTitle = leftTitle;
	}
	public String getMiddleTitle() {
	
		return middleTitle;
	}
	public void setMiddleTitle(String middleTitle) {
	
		this.middleTitle = middleTitle;
	}
	public String getRightTitle() {
	
		return rightTitle;
	}
	public void setRightTitle(String rightTitle) {
	
		this.rightTitle = rightTitle;
	}
	public List<LeftMiddleRightView> getValues() {
	
		return values;
	}
	public void setValues(List<LeftMiddleRightView> values) {
	
		this.values = values;
	}
	public String getClassName() {
	
		return className;
	}
	public void setClassName(String className) {
	
		this.className = className;
	}
	public String getBottomName() {
	
		return bottomName;
	}
	public void setBottomName(String bottomName) {
	
		this.bottomName = bottomName;
	}
	public String getBottomValue() {
	
		return bottomValue;
	}
	public void setBottomValue(String bottomValue) {
	
		this.bottomValue = bottomValue;
	}
	
	
}
