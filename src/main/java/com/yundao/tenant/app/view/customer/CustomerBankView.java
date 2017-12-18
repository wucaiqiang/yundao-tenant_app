

package com.yundao.tenant.app.view.customer;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月18日 下午5:21:50 
 * @author   wucq
 * @version   
 */
public class CustomerBankView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 银行卡ID
	 */
	private String bankId;
	/**
	 * 开户行名
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankNumber;
	/**
	 * 银行卡正面照
	 */
	private String bankFontCardUrl;
	/**
	 * 银行卡反面照
	 */
	private String bankBackCardUrl;
	public String getBankId() {
	
		return bankId;
	}
	public void setBankId(String bankId) {
	
		this.bankId = bankId;
	}
	public String getBankName() {
	
		return bankName;
	}
	public void setBankName(String bankName) {
	
		this.bankName = bankName;
	}
	public String getBankNumber() {
	
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
	
		this.bankNumber = bankNumber;
	}
	public String getBankFontCardUrl() {
	
		return bankFontCardUrl;
	}
	public void setBankFontCardUrl(String bankFontCardUrl) {
	
		this.bankFontCardUrl = bankFontCardUrl;
	}
	public String getBankBackCardUrl() {
	
		return bankBackCardUrl;
	}
	public void setBankBackCardUrl(String bankBackCardUrl) {
	
		this.bankBackCardUrl = bankBackCardUrl;
	}
	

}

