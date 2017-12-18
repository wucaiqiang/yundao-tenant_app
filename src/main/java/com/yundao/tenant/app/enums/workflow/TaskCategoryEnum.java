package com.yundao.tenant.app.enums.workflow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gjl on 2017/9/24.
 */
public enum TaskCategoryEnum {

    /**
     * 审批
     */
    AUDIT("audit", "审批"),
    /**
     * 重新提交
     */
    REJECT_EDIT("reject_edit","重新提交");

    private String value;
    private String name;


    private static Map<String, TaskCategoryEnum> enumMap = new HashMap<>();

    static {
        EnumSet<TaskCategoryEnum> set = EnumSet.allOf(TaskCategoryEnum.class);
        for (TaskCategoryEnum each : set) {
            enumMap.put(each.getName(), each);
        }
    }
    public static TaskCategoryEnum getEnum(String name) {
        if (name == null)
            return null;
        return enumMap.get(name);
    }

    TaskCategoryEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }


    public String getValue() {
	
		return value;
	}

	public void setValue(String value) {
	
		this.value = value;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
