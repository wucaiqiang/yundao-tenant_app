package com.yundao.tenant.app.dto.tag;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class TagDto extends BaseModel implements Serializable {
    /**
	 * 名称
	 */
    private String name;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}