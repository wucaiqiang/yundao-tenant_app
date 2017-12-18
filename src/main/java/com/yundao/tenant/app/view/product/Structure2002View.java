
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure2002View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String spaceColor="FFFFFF";
	private int  spaceHeight=114;
	private String text;
	private String textColor="999999";
	public Structure2002View(){}
	public Structure2002View(String text){
		this.text=text;
	}
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
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	public String getTextColor() {
	
		return textColor;
	}
	public void setTextColor(String textColor) {
	
		this.textColor = textColor;
	}
	

}
