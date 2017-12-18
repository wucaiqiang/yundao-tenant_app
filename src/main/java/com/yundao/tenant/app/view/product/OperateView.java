
package com.yundao.tenant.app.view.product;

/**
 * Function: Reason: Date: 2017年10月19日 下午3:29:49
 * 
 * @author wucq
 * @version
 */
public class OperateView {
	/**
	 * 提示标题
	 */
	private String title;
	/**
	 * 提示内容
	 */
	private String content;
	/**
	 * 提示内容
	 */
	private boolean enable;
	/**
	 * 操作类型
	 */
	private int type;
	/**
	 * 按钮文本
	 */
	private String text;
	/**
	 * 在不可点击的时候的提示内容
	 */
	private String tip;
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public boolean isEnable() {
	
		return enable;
	}
	public void setEnable(boolean enable) {
	
		this.enable = enable;
	}
	public int getType() {
	
		return type;
	}
	public void setType(int type) {
	
		this.type = type;
	}
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	public String getTip() {
	
		return tip;
	}
	public void setTip(String tip) {
	
		this.tip = tip;
	}
	
	
}
