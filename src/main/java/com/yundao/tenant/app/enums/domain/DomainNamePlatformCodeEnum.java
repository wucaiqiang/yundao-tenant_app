package com.yundao.tenant.app.enums.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * 
 * @author:wucq
 * @description:
 */
public enum DomainNamePlatformCodeEnum {
	/**
	 * C端域名
	 */
	SERVICE_NUMBER("service_number", "服务号");
	public static Map<String, DomainNamePlatformCodeEnum> enumMap = new HashMap<String, DomainNamePlatformCodeEnum>();
	static {
		EnumSet<DomainNamePlatformCodeEnum> set = EnumSet.allOf(DomainNamePlatformCodeEnum.class);
		for (DomainNamePlatformCodeEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}

	private DomainNamePlatformCodeEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	private String value;
	private String desc;

	public String getDesc() {

		return desc;
	}

	public void setDesc(String desc) {

		this.desc = desc;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	/**
	 * 获取客户性别枚举
	 * 
	 * @param value
	 * @return
	 */
	public static DomainNamePlatformCodeEnum getDomainNameTypeEnum(String value) {
		DomainNamePlatformCodeEnum sexEnum = enumMap.get(value);
		return sexEnum;
	}
}
