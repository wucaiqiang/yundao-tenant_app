package com.yundao.tenant.app.enums.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户附件类型
 * @author gjl
 * @create 2017-07-06 PM3:55
 **/
public enum FileFormatEnum {
    /**
     * 资产证明
     */
    IMAGE(1, "图片"),
	VIDEO(2, "视频"),;
    private Integer type;
    private String name;
    private static Map<Integer, FileFormatEnum> enumMap = new HashMap<>();

    static {
        EnumSet<FileFormatEnum> set = EnumSet.allOf(FileFormatEnum.class);
        for (FileFormatEnum each : set) {
            enumMap.put(each.getType(), each);
        }
    }
    FileFormatEnum(Integer type, String name) {
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
    /**
     * 获取枚举
     */
    public static FileFormatEnum getEnum(Integer value) {
        if (value == null)
            return null;
        return enumMap.get(value);
    }

    /**
     * 根据value 获取name
     */
    public static String getEnumName(Integer value) {
    	FileFormatEnum enumItem = getEnum(value);
        if (enumItem == null)
            return null;
        return enumItem.getName();
    }
}
