package com.yundao.tenant.app.dto.common;

import java.io.Serializable;


/**
 * 
 * date: 2017年8月1日 下午4:12:16
 * @author:wucq
 * @description:
 */
public class ActionDTO implements Serializable {

	private static final long serialVersionUID = 7998180700442690539L;
	private boolean checkLogin;
	private String jumpUrl;
	
	public ActionDTO() {
	}
	
	public ActionDTO(String jumpUrl) {
		super();
		this.jumpUrl = jumpUrl;
	}

	public ActionDTO(boolean checkLogin, String jumpUrl) {
		super();
		this.checkLogin = checkLogin;
		this.jumpUrl = jumpUrl;
	}

	public boolean isCheckLogin() {
		return checkLogin;
	}

	public void setCheckLogin(boolean checkLogin) {
		this.checkLogin = checkLogin;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

}
