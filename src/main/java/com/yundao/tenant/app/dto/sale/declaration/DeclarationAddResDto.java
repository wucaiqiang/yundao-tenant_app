
package com.yundao.tenant.app.dto.sale.declaration;

import java.io.Serializable;

import com.yundao.tenant.app.dto.common.ActionDTO;

/**
 * Function: Reason: Date: 2017年9月6日 下午4:18:41
 * 
 * @author wucq
 * @version
 */
public class DeclarationAddResDto implements Serializable {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private ActionDTO action;
	public String getText() {
	
		return text;
	}
	public void setText(String text) {
	
		this.text = text;
	}
	public ActionDTO getAction() {
	
		return action;
	}
	public void setAction(ActionDTO action) {
	
		this.action = action;
	}
	
}
