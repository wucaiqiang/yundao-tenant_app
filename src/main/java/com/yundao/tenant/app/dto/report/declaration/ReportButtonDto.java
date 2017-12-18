

package com.yundao.tenant.app.dto.report.declaration;

import com.yundao.tenant.app.dto.common.ActionDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 下午5:08:53 
 * @author   欧阳利
 * @version   
 */
public class ReportButtonDto {
	@ApiModelProperty("类目标题，eg.业绩报表")
    private String itemTitle;
	@ApiModelProperty("类目Icon")
	private String itemIcon;
	@ApiModelProperty("类目跳转动作")
	private ActionDTO itemAction;
	
	
	public ReportButtonDto(String itemTitle, String itemIcon, ActionDTO itemAction) {
		super();
		this.itemTitle = itemTitle;
		this.itemIcon = itemIcon;
		this.itemAction = itemAction;
	}
	public String getItemTitle() {
	
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
	
		this.itemTitle = itemTitle;
	}
	public String getItemIcon() {
	
		return itemIcon;
	}
	public void setItemIcon(String itemIcon) {
	
		this.itemIcon = itemIcon;
	}
	public ActionDTO getItemAction() {
	
		return itemAction;
	}
	public void setItemAction(ActionDTO itemAction) {
	
		this.itemAction = itemAction;
	}
}

