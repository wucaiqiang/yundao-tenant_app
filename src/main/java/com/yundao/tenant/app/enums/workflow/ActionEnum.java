package com.yundao.tenant.app.enums.workflow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjl on 2017/9/24.
 */
public enum ActionEnum {

    /**
     * PASS:通过
     */
    PASS(1, "PASS","通过"),
    /**
     * 驳回
     */
    REJECT(2, "REJECT","驳回"),
    /**
     * 取消
     */
    CANCEL(3, "CANCEL","取消"),
    
    /**
     * 已否决
     */
    NO_PASS(5, "VETO","已否决"),
    
    
	RESUBMIT(4, "CANCEL","重新提交");

    private Integer value;
    private String name;
    private String desc;


    private static Map<String, ActionEnum> enumMap = new HashMap<>();
    private static Map<Integer, ActionEnum> valueEnumMap = new HashMap<>();

    static {
        EnumSet<ActionEnum> set = EnumSet.allOf(ActionEnum.class);
        for (ActionEnum each : set) {
            enumMap.put(each.getName(), each);
            valueEnumMap.put(each.getValue(), each);
        }
    }
    public static ActionEnum getEnum(String name) {
        if (name == null)
            return null;
        return enumMap.get(name);
    }
    public static ActionEnum getEnumByValue(Integer value) {
        if (value == null)
            return null;
        return valueEnumMap.get(value);
    }
    ActionEnum(Integer value, String name,String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
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

	public String getDesc() {
	
		return desc;
	}

	public void setDesc(String desc) {
	
		this.desc = desc;
	}
    
}
