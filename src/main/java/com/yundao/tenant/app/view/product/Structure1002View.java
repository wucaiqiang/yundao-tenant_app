
package com.yundao.tenant.app.view.product;


import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure1002View implements DataDTO{
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String leftValue;
	private String leftName;
	private String rightValue;
	private String rightName;
	private List<String> tags;
	/**
	 * 产品名称
	 */
	private String topValue;
	private String bottomValue;

	public String getLeftValue() {

		return leftValue;
	}

	public void setLeftValue(String leftValue) {

		this.leftValue = leftValue;
	}

	public String getLeftName() {

		return leftName;
	}

	public void setLeftName(String leftName) {

		this.leftName = leftName;
	}

	public String getRightValue() {

		return rightValue;
	}

	public void setRightValue(String rightValue) {

		this.rightValue = rightValue;
	}

	public String getRightName() {

		return rightName;
	}

	public void setRightName(String rightName) {

		this.rightName = rightName;
	}


	public List<String> getTags() {
	
		return tags;
	}

	public void setTags(List<String> tags) {
	
		this.tags = tags;
	}

	public String getBottomValue() {

		return bottomValue;
	}

	public void setBottomValue(String bottomValue) {

		this.bottomValue = bottomValue;
	}

	public String getTopValue() {
	
		return topValue;
	}

	public void setTopValue(String topValue) {
	
		this.topValue = topValue;
	}

}
