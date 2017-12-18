
package com.yundao.tenant.app.view.msg;

import java.io.Serializable;

import com.yundao.tenant.app.dto.common.ActionDTO;

/**
 * Function: Reason: Date: 2017年9月8日 下午4:52:28
 * 
 * @author wucq
 * @version
 */
public class MsgMessageView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 消息id
	 */
	private String messageId;
	/**
	 * 是否已读
	 */
	private Boolean readed;
	/**
	 * 代办时间
	 */
	private String timeText;
	/**
	 * 操作 
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 动作
	 */
	private ActionDTO action;

	public String getMessageId() {

		return messageId;
	}

	public void setMessageId(String messageId) {

		this.messageId = messageId;
	}

	public Boolean getReaded() {

		return readed;
	}

	public void setReaded(Boolean readed) {

		this.readed = readed;
	}

	public String getTimeText() {

		return timeText;
	}

	public void setTimeText(String timeText) {

		this.timeText = timeText;
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

	public ActionDTO getAction() {

		return action;
	}

	public void setAction(ActionDTO action) {

		this.action = action;
	}

}
