package com.yundao.tenant.app.dto.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 数据权限dto
 *
 * @author 欧阳利
 *         2017年8月9日
 */
public class DataPermissionDto {

    @ApiModelProperty("数据对象code")
    private String dataObjectCode;

    @ApiModelProperty("是否有查询权限(1:有; 0:没有)")
    private Boolean isReadPermission;

    @ApiModelProperty("是否有删除权限(1:有; 0:没有)")
    private Boolean isDeletePermission;

    @ApiModelProperty("是否有修改权限(1:有; 0:没有)")
    private Boolean isEditPermission;

    public String getDataObjectCode() {
        return dataObjectCode;
    }

    public void setDataObjectCode(String dataObjectCode) {
        this.dataObjectCode = dataObjectCode;
    }

    public Boolean getReadPermission() {
        return isReadPermission;
    }

    public void setReadPermission(Boolean readPermission) {
        isReadPermission = readPermission;
    }

    public Boolean getDeletePermission() {
        return isDeletePermission;
    }

    public void setDeletePermission(Boolean deletePermission) {
        isDeletePermission = deletePermission;
    }

    public Boolean getEditPermission() {
        return isEditPermission;
    }

    public void setEditPermission(Boolean editPermission) {
        isEditPermission = editPermission;
    }
}
