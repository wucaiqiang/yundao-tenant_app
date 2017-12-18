
package com.yundao.tenant.app.view.index;

import java.io.Serializable;

import com.yundao.tenant.app.dto.common.ActionDTO;

/**
 * Function: Reason: Date: 2017年9月8日 下午4:57:40
 * 
 * @author wucq
 * @version
 */
public class FunctionModuleView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String iconUrl;
	private String title;
	private String des;
	private ActionDTO action;
	public FunctionModuleView(){}
	public FunctionModuleView(String iconUrl,String title,String des, ActionDTO action){
		this.iconUrl=iconUrl;
		this.title=title;
		this.des=des;
		this.action=action;
	}
	public String getIconUrl() {
	
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
	
		this.iconUrl = iconUrl;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getDes() {
	
		return des;
	}
	public void setDes(String des) {
	
		this.des = des;
	}
	public ActionDTO getAction() {
	
		return action;
	}
	public void setAction(ActionDTO action) {
	
		this.action = action;
	}
	
}
