package com.yundao.tenant.app.dto.common;

import java.io.Serializable;
import java.util.List;

/**
 * 不分页列表
 * date: 2017年11月1日 上午10:55:58
 * @author:欧阳利
 * @description:
 */
public class ItemListNoPageDTO implements Serializable {

	private static final long serialVersionUID = -1606991347539089314L;
	
	private String  permissionText;
	
	private String chatDes;
	
	protected List<ViewItem> viewItems;

	public String getChatDes() {
	
		return chatDes;
	}

	public void setChatDes(String chatDes) {
	
		this.chatDes = chatDes;
	}

	public String getPermissionText() {
	
		return permissionText;
	}

	public void setPermissionText(String permissionText) {
	
		this.permissionText = permissionText;
	}

	public ItemListNoPageDTO(List<ViewItem> viewItems) {
		this.viewItems = viewItems;
	}

	public List<ViewItem> getViewItems() {
	
		return viewItems;
	}

	public void setViewItems(List<ViewItem> viewItems) {
	
		this.viewItems = viewItems;
	}


}
