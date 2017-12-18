package com.yundao.tenant.app.enums.search;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * @author:wucq
 * @description:
 */
public enum SearchTypeEnum {
	/**
	 * 产品搜索
	 */
	PRODUCT("search_product","产品搜索"),
	CUSTOMER("search_customer","客户搜索"),
	CUSTOMER_OPENSEA("search_customer_open_sea","搜索客户公海"),
	RESERVATION("search_appoint","预约搜索"),
	DECLARATION("search_order","报单搜索"),
	PRODUCT_MANAGER("search_product_manager","产品管理搜索"),
	SALE_LEADS("search_sale_line","搜索销售线索"),
	PRODUCT_NOTICE_MANAGER("search_product_notice_manager","产品公告管理搜索");
	private SearchTypeEnum(String value,String name) {
		this.value = value;
		this.name=name;
	}
	private String value;
	private String name;
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
