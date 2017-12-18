package com.yundao.tenant.app.enums.product;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * @author:wucq
 * @description:
 */
public enum ProductIncomeTypeEnum {
	/**
	 * 固定收益
	 */
	FIXED("dic_income_type_fix","固定收益"), 
	/**
	 * 浮动收益
	 */
	FLOAT("dic_income_type_float","浮动收益"), 
	/**
	 * 固定+浮动收益
	 */
	FIXED_FLOAT("dic_income_type_fix_float","固定+浮动收益");
	private ProductIncomeTypeEnum(String value,String desc) {
		this.value = value;
		this.desc=desc;
	}
	private String value;
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
	
}
