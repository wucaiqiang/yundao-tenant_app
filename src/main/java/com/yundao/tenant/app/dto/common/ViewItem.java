package com.yundao.tenant.app.dto.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yundao.tenant.app.dto.DataDTO;

/**
 * 提供给客户端的首页每栏数据结构
 * 
 * @author xubingsong
 *
 */
public class ViewItem implements Serializable {

	private static final long serialVersionUID = -6899735374100845595L;

	protected ActionDTO action;
	protected Integer type;
	protected DataDTO viewData;
	
	public ViewItem() {
	}
	
	public ViewItem(Integer type) {
		super();
		this.type = type;
	}
	
	public ViewItem(Integer type, DataDTO viewData) {
		this(type);
		this.viewData = viewData;
	}
	
	public ViewItem(Integer type, String jumpUrl, DataDTO viewData) {
		this(type, viewData);
		this.action = new ActionDTO(jumpUrl);
	}

	public ViewItem(Integer type, boolean checkLogin, String jumpUrl, DataDTO viewData) {
		this(type, viewData);
		this.action = new ActionDTO(checkLogin, jumpUrl);
	}
	
	public ViewItem(ActionDTO action, Integer type, DataDTO viewData) {
		this(type, viewData);
		this.action = action;
	}

	public ActionDTO getAction() {
		return action;
	}

	public void setAction(ActionDTO action) {
		this.action = action;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public DataDTO getViewData() {
		return viewData;
	}

	public void setViewData(DataDTO viewData) {
		this.viewData = viewData;
	}

	
}
