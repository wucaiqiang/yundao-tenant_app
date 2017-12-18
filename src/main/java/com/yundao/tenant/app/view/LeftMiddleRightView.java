

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 下午2:02:10 
 * @author   wucq
 * @version   
 */
public class LeftMiddleRightView implements DataDTO{
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String leftValue;
	private String middleValue;
	private String rightValue;
	public LeftMiddleRightView(){}

	public LeftMiddleRightView(String leftValue,String middleValue,String rightValue) {
		this.leftValue=leftValue;
		this.middleValue=middleValue;
		this.rightValue=rightValue;
	}

	public String getLeftValue() {
	
		return leftValue;
	}
	public void setLeftValue(String leftValue) {
	
		this.leftValue = leftValue;
	}
	public String getMiddleValue() {
	
		return middleValue;
	}
	public void setMiddleValue(String middleValue) {
	
		this.middleValue = middleValue;
	}
	public String getRightValue() {
	
		return rightValue;
	}
	public void setRightValue(String rightValue) {
	
		this.rightValue = rightValue;
	}
}

