package com.yundao.tenant.app.dto.permission;

import java.util.List;

import com.yundao.tenant.app.annotation.IdField;
import com.yundao.tenant.app.annotation.Owner;

public class PermissionObjectDto {

    /**
     * id
     */
	@IdField
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 拥有者id
     */
    @Owner
    private List<Long> ownerIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getOwnerIds() {
		return ownerIds;
	}

	public void setOwnerIds(List<Long> ownerIds) {
		this.ownerIds = ownerIds;
	}
}
