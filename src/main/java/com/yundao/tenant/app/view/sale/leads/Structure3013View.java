
package com.yundao.tenant.app.view.sale.leads;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3013View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 线索Id
	 */
	private String lineId;
	/**
	 * 服务号
	 */
	private String channel;
	/**
	 * 线索时间
	 */
	private String lineTime;
	/**
	 * 类型+内容
	 */
	private String content;
	/**
	 * 处理状态
	 */
	private String handle;
	/**
	 * 是否能处理（要判断权限）
	 */
	private boolean canHandle;
	public String getLineId() {
	
		return lineId;
	}
	public void setLineId(String lineId) {
	
		this.lineId = lineId;
	}
	public String getChannel() {
	
		return channel;
	}
	public void setChannel(String channel) {
	
		this.channel = channel;
	}
	public String getLineTime() {
	
		return lineTime;
	}
	public void setLineTime(String lineTime) {
	
		this.lineTime = lineTime;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public String getHandle() {
	
		return handle;
	}
	public void setHandle(String handle) {
	
		this.handle = handle;
	}
	public boolean isCanHandle() {
	
		return canHandle;
	}
	public void setCanHandle(boolean canHandle) {
	
		this.canHandle = canHandle;
	}
	
	
}
