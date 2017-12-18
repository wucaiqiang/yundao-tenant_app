
package com.yundao.tenant.app.view.index;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.msg.MsgMessageView;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:23:47
 * 
 * @author wucq
 * @version
 */
public class IndexWorkbenchView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 今日代办数
	 */
	private Integer pendingCount;
	/**
	 * 今日代办集合，最多显示3条
	 */
	private List<MsgMessageView> pendingList;
	/**
	 * 功能列表
	 */
	private List<FunctionModuleView> functionList;
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
	public List<FunctionModuleView> getFunctionList() {
	
		return functionList;
	}
	public void setFunctionList(List<FunctionModuleView> functionList) {
	
		this.functionList = functionList;
	}
	
	
}
