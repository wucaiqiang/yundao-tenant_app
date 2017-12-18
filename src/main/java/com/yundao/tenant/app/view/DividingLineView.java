
package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:23:47
 * 
 * @author wucq
 * @version
 */
public class DividingLineView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 间隔控制颜色
	 */
	private String spaceColor ="F6F6F6";
	/**
	 * 间隔控制高度
	 */
	private int spaceHeight=10;
	public String getSpaceColor() {
	
		return spaceColor;
	}
	public void setSpaceColor(String spaceColor) {
	
		this.spaceColor = spaceColor;
	}
	public int getSpaceHeight() {
	
		return spaceHeight;
	}
	public void setSpaceHeight(int spaceHeight) {
	
		this.spaceHeight = spaceHeight;
	}
	
}
