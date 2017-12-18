package com.yundao.tenant.app.dto.user;

import com.yundao.tenant.app.dto.DataDTO;

public class UserBaseDTO implements DataDTO {

	private static final long serialVersionUID = 417148366478942559L;

	private String id;
	private String ticket;
	private String userId;
	private String username;
	private String realName;
	private String email;
	private String mechanismName;
	private Integer mechanismId;

	public UserBaseDTO() {
	}

	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}

	public String getTicket() {
	
		return ticket;
	}

	public void setTicket(String ticket) {
	
		this.ticket = ticket;
	}

	public String getUserId() {
	
		return userId;
	}

	public void setUserId(String userId) {
	
		this.userId = userId;
	}

	public String getUsername() {
	
		return username;
	}

	public void setUsername(String username) {
	
		this.username = username;
	}

	public String getRealName() {
	
		return realName;
	}

	public void setRealName(String realName) {
	
		this.realName = realName;
	}

	public String getEmail() {
	
		return email;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}

	public String getMechanismName() {
	
		return mechanismName;
	}

	public void setMechanismName(String mechanismName) {
	
		this.mechanismName = mechanismName;
	}

	public Integer getMechanismId() {
	
		return mechanismId;
	}

	public void setMechanismId(Integer mechanismId) {
	
		this.mechanismId = mechanismId;
	}

}
