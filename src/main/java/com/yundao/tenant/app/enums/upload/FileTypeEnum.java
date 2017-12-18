package com.yundao.tenant.app.enums.upload;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件所支持图片枚举
 * date: 2017年8月23日 下午4:41:55
 * @author:wucq
 * @description:
 */
public enum FileTypeEnum {
    /**
     * 图片
     */
    IMAGE(1, "jpg,png,jpeg,gif,JPG,PNG,JPEG"),
    /**
     * 文件
     */
	FILE(2, "pdf,PDF");
    private Integer type;
    private String format;
    private static Map<Integer, FileTypeEnum> enumMap = new HashMap<>();

    static {
        EnumSet<FileTypeEnum> set = EnumSet.allOf(FileTypeEnum.class);
        for (FileTypeEnum each : set) {
            enumMap.put(each.getType(), each);
        }
    }
    FileTypeEnum(Integer type, String format) {
        this.type = type;
        this.format = format;
    }

    public String getFormat() {
	
		return format;
	}


	public void setFormat(String format) {
	
		this.format = format;
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
    public static FileTypeEnum getEnum(Integer value) {
        if (value == null)
            return null;
        return enumMap.get(value);
    }

    /**
     * 根据value 获取format
     */
    public static Set<String> getEnumFormat(Integer value) {
    	FileTypeEnum enumItem = getEnum(value);
        if (enumItem == null)
            return null;
        String format=enumItem.getFormat();
        if(StringUtils.isBlank(format)){
        	return null;
        }
        String fms[]=format.split(",");
        Set<String> set = new HashSet<String>(Arrays.asList(fms));
        return set;
    }
}
