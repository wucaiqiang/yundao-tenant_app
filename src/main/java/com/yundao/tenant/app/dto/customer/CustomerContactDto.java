package com.yundao.tenant.app.dto.customer;

import java.io.Serializable;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月9日 下午1:52:42 
 * @author   wucq
 * @version   
 */
public class CustomerContactDto implements DataDTO,Serializable{
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 邮箱
	 */
	private String email;
	public String getMobile() {
	
		return mobile;
	}
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	public String getWechat() {
	
		return wechat;
	}
	public void setWechat(String wechat) {
	
		this.wechat = wechat;
	}
	public String getQq() {
	
		return qq;
	}
	public void setQq(String qq) {
	
		this.qq = qq;
	}
	public String getEmail() {
	
		return email;
	}
	public void setEmail(String email) {
	
		this.email = email;
	}

}

