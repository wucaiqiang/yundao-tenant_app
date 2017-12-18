
package com.yundao.tenant.app.view.user.visit;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure3012View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String dateText;
	private boolean success;
	private String reason;
	private String content;
	private String way;
	private String name;
	public String getDateText() {
	
		return dateText;
	}
	public void setDateText(String dateText) {
	
		this.dateText = dateText;
	}
	public boolean isSuccess() {
	
		return success;
	}
	public void setSuccess(boolean success) {
	
		this.success = success;
	}
	public String getReason() {
	
		return reason;
	}
	public void setReason(String reason) {
	
		this.reason = reason;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public String getWay() {
	
		return way;
	}
	public void setWay(String way) {
	
		this.way = way;
	}
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	
}
