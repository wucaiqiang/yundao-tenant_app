package com.yundao.tenant.app.enums.report;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * date: 2017年7月15日 下午2:10:01
 * @author:wucq
 * @description:
 */
public enum ReportTimeTypeEnum {
	/**
	 * 本月
	 */
	CUR_MONTH("CUR_MONTH","本月"), 
	/**
	 * 上月
	 */
	LAST_MONTH("LAST_MONTH","上月"), 
	/**
	 * 本周
	 */
	CUR_WEEK("CUR_WEEK","本周"), 
	/**
	 * 上周
	 */
	LAST_WEEK("LAST_WEEK","上周");
	public static Map<String, ReportTimeTypeEnum> enumMap = new HashMap<String, ReportTimeTypeEnum>();
	static {
		EnumSet<ReportTimeTypeEnum> set = EnumSet.allOf(ReportTimeTypeEnum.class);
		for (ReportTimeTypeEnum each : set) {
			enumMap.put(each.getType(), each);
		}
	}
	private ReportTimeTypeEnum(String type,String desc) {
		this.type = type;
		this.desc=desc;
	}
	private String type;
	private String desc;
	
	public String getType() {
	
		return type;
	}

	public void setType(String type) {
	
		this.type = type;
	}

	public String getDesc() {
	
		return desc;
	}

	public void setDesc(String desc) {
	
		this.desc = desc;
	}

	/**
	 * 获取客户性别枚举
	 * 
	 * @param value
	 * @return
	 */
	public static ReportTimeTypeEnum getMoneyTypeEnum(String value) {
		ReportTimeTypeEnum sexEnum = enumMap.get(value);
		return sexEnum;
	}
}
