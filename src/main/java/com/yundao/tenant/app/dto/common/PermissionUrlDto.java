package com.yundao.tenant.app.dto.common;

import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;

/**
 * 数据权限 url
 *
 * @author jan
 * @create 2017-08-01 PM2:19
 **/
public class PermissionUrlDto {

    /**
     * 获取我的数据 url
     */
    private String personalUrl;

    /**
     * 获取我部门的数据 url
     */
    private String departmentUrl;

    /**
     * 获取所有的数据 url
     */
    private String publicUrl;

    public String getPermissionUrl(Integer permission) {
        if (DataObjectPermissionEnum.PERSONAL.getValue().equals(permission))
            return this.personalUrl;
        if (DataObjectPermissionEnum.DEPARTMENT.getValue().equals(permission))
            return this.departmentUrl;
        if (DataObjectPermissionEnum.PUBLIC.getValue().equals(permission))
            return this.publicUrl;
        return "";
    }

    public void setPersonalUrl(String personalUrl) {
        this.personalUrl = personalUrl;
    }

    public void setDepartmentUrl(String departmentUrl) {
        this.departmentUrl = departmentUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }
}
