package com.yundao.tenant.app.enums.product;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjl on 2017/7/18.
 */
public enum NoticeStatusEnum {
	/**
	 * 未提交
	 */
    STATUS_1(1,"未提交"),
    /**
	 * 待审批
	 */
    STATUS_2(2,"待审批"),
    /**
	 * 已通过
	 */
    STATUS_3(3,"已通过"),
    /**
	 * 已驳回
	 */
    STATUS_4(4,"已驳回"),
    /**
	 * 已取消
	 */
    STATUS_5(5,"已取消");
    NoticeStatusEnum(Integer status, String name){
        this.status = status;
        this.name = name;
    }

    Integer status;
    String name;

    private static Map<Integer, NoticeStatusEnum> enumMap = new HashMap<Integer, NoticeStatusEnum>();

    static {
        EnumSet<NoticeStatusEnum> set = EnumSet.allOf(NoticeStatusEnum.class);
        for (NoticeStatusEnum each : set) {
            enumMap.put(each.getStatus(), each);
        }
    }

    public static NoticeStatusEnum getNoticeStatusEnum(Integer value) {
        return enumMap.get(value);
    }
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}





	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
