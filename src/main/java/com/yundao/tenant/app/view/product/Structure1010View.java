
package com.yundao.tenant.app.view.product;


import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure1010View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String updateTime;
	private String type;
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getUpdateTime() {
	
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
	
		this.updateTime = updateTime;
	}
	public String getType() {
	
		return type;
	}
	public void setType(String type) {
	
		this.type = type;
	}
	
	
}
