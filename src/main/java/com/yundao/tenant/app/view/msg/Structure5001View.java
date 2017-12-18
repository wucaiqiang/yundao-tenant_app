
package com.yundao.tenant.app.view.msg;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure5001View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 消息ID
	 */
	private String messageId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否已读
	 */
	private Boolean readed;
	public String getMessageId() {
	
		return messageId;
	}
	public void setMessageId(String messageId) {
	
		this.messageId = messageId;
	}
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
	public Boolean getReaded() {
	
		return readed;
	}
	public void setReaded(Boolean readed) {
	
		this.readed = readed;
	}
	
}
