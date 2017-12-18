package com.yundao.tenant.app.dto.permission;

import java.io.Serializable;

import com.yundao.tenant.app.annotation.IdField;

/**
 * 删除操作获取数据传输对象
 *
 * @author jan
 * @create 2017-07-31 PM9:02
 **/
public class PermissionDeleteDto implements Serializable {

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
     * 创建人id
     */
    private Long createUserId;

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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}
