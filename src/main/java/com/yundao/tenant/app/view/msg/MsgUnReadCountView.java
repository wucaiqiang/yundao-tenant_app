

package com.yundao.tenant.app.view.msg;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月7日 下午5:35:21 
 * @author   wucq
 * @version   
 */
public class MsgUnReadCountView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 代办消息数
	 */
	private int pendingCount;
	/**
	 * 通知消息数
	 */
	private int noticeCount;
	public int getPendingCount() {
	
		return pendingCount;
	}
	public void setPendingCount(int pendingCount) {
	
		this.pendingCount = pendingCount;
	}
	public int getNoticeCount() {
	
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
	
		this.noticeCount = noticeCount;
	}
	

}

