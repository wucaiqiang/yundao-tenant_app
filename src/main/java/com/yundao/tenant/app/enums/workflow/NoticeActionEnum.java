package com.yundao.tenant.app.enums.workflow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjl on 2017/9/24.
 */
public enum NoticeActionEnum {

    /**
     * PASS:通过
     */
    PASS(3, "PASS"),
    /**
     * 驳回
     */
    REJECT(4, "REJECT"),
    /**
     * 取消
     */
    CANCEL(5, "CANCEL");

    private Integer value;
    private String name;


    private static Map<String, NoticeActionEnum> enumMap = new HashMap<>();

    static {
        EnumSet<NoticeActionEnum> set = EnumSet.allOf(NoticeActionEnum.class);
        for (NoticeActionEnum each : set) {
            enumMap.put(each.getName(), each);
        }
    }
    public static NoticeActionEnum getEnum(String name) {
        if (name == null)
            return null;
        return enumMap.get(name);
    }

    NoticeActionEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
