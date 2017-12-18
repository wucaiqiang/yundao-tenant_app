package com.yundao.tenant.app.dto.department;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.dto.user.UserSimpleInfoDto;

import io.swagger.annotations.ApiModelProperty;

public class DepartmentUserListResDto implements Serializable {

	/**
	 * 部门id
	 */
	@ApiModelProperty(value = "部门id")
	private Long id;

	/**
	 * 子部门
	 */
	@ApiModelProperty(value = "子部门")
	private List<DepartmentUserListResDto> childs;

	/**
	 * 名字
	 */
	@ApiModelProperty(value = "名字")
	private String name;

	/**
	 * 编码（最小八位数，最大支持八层，从00000001开始）
	 */
	@ApiModelProperty(value = "编码（最小八位数，最大支持八层，从00000001开始）")
	private String code;

	/**
	 * 父id
	 */
	@ApiModelProperty(value = "父id")
	private Long parentId;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "部门下面的人")
	private List<UserSimpleInfoDto> users;
	@ApiModelProperty(value = "部门以其子部门下面的人数")
	private Integer userNum;

	private static final long serialVersionUID = 1000L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<DepartmentUserListResDto> getChilds() {
	
		return childs;
	}

	public void setChilds(List<DepartmentUserListResDto> childs) {
	
		this.childs = childs;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<UserSimpleInfoDto> getUsers() {
	
		return users;
	}

	public void setUsers(List<UserSimpleInfoDto> users) {
	
		this.users = users;
	}

	public Integer getUserNum() {
	
		return userNum;
	}

	public void setUserNum(Integer userNum) {
	
		this.userNum = userNum;
	}
	

}