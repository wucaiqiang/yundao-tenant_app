
package com.yundao.tenant.app.view.index;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.msg.MsgMessageView;

/**
 * Function: Reason: Date: 2017年9月20日 上午11:41:55
 * 
 * @author wucq
 * @version
 */
public class TaskMessageView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Integer pendingCount;
	private List<MsgMessageView> pendingList;
	public Integer getPendingCount() {
	
		return pendingCount;
	}
	public void setPendingCount(Integer pendingCount) {
	
		this.pendingCount = pendingCount;
	}
	public List<MsgMessageView> getPendingList() {
	
		return pendingList;
	}
	public void setPendingList(List<MsgMessageView> pendingList) {
	
		this.pendingList = pendingList;
	}

}
