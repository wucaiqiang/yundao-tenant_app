package com.yundao.tenant.app.dto.fieldgroup;

import com.yundao.core.base.model.BaseModel;

import java.io.Serializable;

public class FieldGroupDto extends BaseModel implements Serializable {
    /**
	 * 业务对象id
	 */
    private Long businessObjectId;

    /**
	 * 父id
	 */
    private Long parentId;

    /**
	 * 名字
	 */
    private String name;

    /**
	 * 描述
	 */
    private String description;

    /**
	 * 是否共用，0：否，1：是
	 */
    private Integer isShare;

    private static final long serialVersionUID = 1L;

    public Long getBusinessObjectId() {
        return businessObjectId;
    }

    public void setBusinessObjectId(Long businessObjectId) {
        this.businessObjectId = businessObjectId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }
}