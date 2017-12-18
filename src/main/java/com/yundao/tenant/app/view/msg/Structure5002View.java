
package com.yundao.tenant.app.view.msg;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure5002View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 时间e.g. 03-20 12：21 ，昨天 12:21 ，12：21（今天的消息不展示日期，昨天的消息日期展示为昨天，其他则为，日期+时间格式）
	 */
	private String text;
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	
}
