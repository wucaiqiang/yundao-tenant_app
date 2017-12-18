package com.yundao.tenant.app.enums.customer;

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
public enum CustomerListColumnV2Enum {
	/**
	 * 全部客户
	 */
	ALL("1", "all"),
	/**
	 * 我负责的
	 */
	MY("2", "my"),
	/**
	 * 我未跟进的
	 */
	NEVERFOLLOW("3", "neverfollow"),
	/**
	 * 我关注的
	 */
	FOCUS("4", "focus"),
	/**
	 * 我部门的
	 */
	UNFOLLOW("5", "department");
	public static Map<String, CustomerListColumnV2Enum> enumMap = new HashMap<String, CustomerListColumnV2Enum>();
	static {
		EnumSet<CustomerListColumnV2Enum> set = EnumSet.allOf(CustomerListColumnV2Enum.class);
		for (CustomerListColumnV2Enum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}

	private CustomerListColumnV2Enum(String value, String scope) {
		this.value = value;
		this.scope = scope;
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
	public static CustomerListColumnV2Enum getEnum(String value) {
		CustomerListColumnV2Enum sexEnum = enumMap.get(value);
		return sexEnum;
	}
}
