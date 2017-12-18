package com.yundao.tenant.app.model.tenant;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class TenantModel extends BaseModel implements Serializable {
    /**
	 * 名字
	 */
    private String name;

    /**
	 * 编码
	 */
    private String code;

    /**
	 * 类型
	 */
    private Integer type;

    /**
	 * 地址
	 */
    private String address;

    /**
	 * 排序，越小越靠前
	 */
    private Integer sequence;


    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    
}