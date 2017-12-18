package com.yundao.tenant.app.enums.customer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * @author:wucq
 * @description:
 */
public enum CustomerListColumnEnum {
	
	/**
	 *全部
	 */
	ALL("1","all"), 
	/**
	 * 我关注的
	 */
	FOCUS("2","focus"), 
	/**
	 * 未跟进
	 */
	UNFOLLOW("3","unfollow");
	
	public static Map<String, CustomerListColumnEnum> enumMap = new HashMap<String, CustomerListColumnEnum>();
	static {
		EnumSet<CustomerListColumnEnum> set = EnumSet.allOf(CustomerListColumnEnum.class);
		for (CustomerListColumnEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}
	private CustomerListColumnEnum(String value,String scope) {
		this.value = value;
		this.scope=scope;
	}
	private String value;
	private String scope;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getScope() {
	
		return scope;
	}
	public void setScope(String scope) {
	
		this.scope = scope;
	}
	/**
	 * 获取客户栏目枚举
	 * 
	 * @param value
	 * @return
	 */
	public static CustomerListColumnEnum getEnum(String value) {
		CustomerListColumnEnum sexEnum = enumMap.get(value);
		return sexEnum;
	}
}
