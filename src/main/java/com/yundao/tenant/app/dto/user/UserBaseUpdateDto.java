package com.yundao.tenant.app.dto.user;

import java.io.Serializable;
import java.util.List;

import com.yundao.core.base.model.BaseModel;
import com.yundao.tenant.app.model.tenant.TenantModel;

public class UserBaseUpdateDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 名片简介
	 */
	private String cardIntroduce;
	/**
	 * 名片头衔
	 */
	private String cardHeaderBit;

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getAvatar() {

		return avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public String getCardIntroduce() {

		return cardIntroduce;
	}

	public void setCardIntroduce(String cardIntroduce) {

		this.cardIntroduce = cardIntroduce;
	}

	public String getCardHeaderBit() {

		return cardHeaderBit;
	}

	public void setCardHeaderBit(String cardHeaderBit) {

		this.cardHeaderBit = cardHeaderBit;
	}

}