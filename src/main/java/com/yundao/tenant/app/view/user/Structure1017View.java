
package com.yundao.tenant.app.view.user;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure1017View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 头衔
	 */
	private String cardHeaderBit;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 公司
	 */
	private String company;
	public String getAvatar() {
	
		return avatar;
	}
	public void setAvatar(String avatar) {
	
		this.avatar = avatar;
	}
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public String getCardHeaderBit() {
	
		return cardHeaderBit;
	}
	public void setCardHeaderBit(String cardHeaderBit) {
	
		this.cardHeaderBit = cardHeaderBit;
	}
	public String getMobile() {
	
		return mobile;
	}
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	public String getCompany() {
	
		return company;
	}
	public void setCompany(String company) {
	
		this.company = company;
	}
}
