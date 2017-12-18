package com.yundao.tenant.app.enums.customer;


/**
 * 客户附件类型
 * @author gjl
 * @create 2017-07-06 PM3:55
 **/
public enum CustomerAddWayEnum {
    /**
     * 我自己创建
     */
    MYSELF(1, "我自己创建"),
    /**
     * "客户池创建
     */
	PUBLIC(2, "客户池创建");
    private Integer type;
    private String name;
    CustomerAddWayEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
