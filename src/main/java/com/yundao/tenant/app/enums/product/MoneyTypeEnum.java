package com.yundao.tenant.app.enums.product;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * @author:wucq
 * @description:
 */
public enum MoneyTypeEnum {
	/**
	 * 固定收益
	 */
	CNY("0","万","人民币"), 
	/**
	 * 浮动收益
	 */
	USD("1","万美元","美元");
	public static Map<String, MoneyTypeEnum> enumMap = new HashMap<String, MoneyTypeEnum>();
	static {
		EnumSet<MoneyTypeEnum> set = EnumSet.allOf(MoneyTypeEnum.class);
		for (MoneyTypeEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}
	private MoneyTypeEnum(String value,String unit,String desc) {
		this.value = value;
		this.unit=unit;
		this.desc=desc;
	}
	private String value;
	private String unit;
	private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
	
		return desc;
	}
	public void setDesc(String desc) {
	
		this.desc = desc;
	}
	
	public String getUnit() {
	
		return unit;
	}
	public void setUnit(String unit) {
	
		this.unit = unit;
	}
	/**
	 * 获取客户性别枚举
	 * 
	 * @param value
	 * @return
	 */
	public static MoneyTypeEnum getMoneyTypeEnum(String value) {
		MoneyTypeEnum sexEnum = enumMap.get(value);
		return sexEnum;
	}
}
