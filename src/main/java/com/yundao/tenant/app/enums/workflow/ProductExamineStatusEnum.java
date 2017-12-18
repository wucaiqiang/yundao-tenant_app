package com.yundao.tenant.app.enums.workflow;

import java.util.*;

/**
 * 产品审核状态
 *
 * @author jan
 * @create 2017-07-22 PM3:36
 **/
public enum ProductExamineStatusEnum {

	/**
	 * 未提交
	 */
	INITIAL(0, "未提交", false, false, false, false),

	/**
	 * 待审批
	 */
	APPROVALING(1, "待审批", true, true, true, false),

	/**
	 * 已通过
	 */
	PASS(2, "已通过", false, false, false, true),

	/**
	 * 已驳回
	 */
	REJECT(3, "已驳回", false, false, false, true),

	/**
	 * 已取消
	 */
	CANCEL(4, "已取消", false, false, false, true);

	private Integer value;
	private String name;

	private Boolean hasPassOperate;
	private Boolean hasRejectOperate;
	private Boolean hasCancelOperate;
	private Boolean showText;

	private static Map<Integer, ProductExamineStatusEnum> enumMap = new HashMap<>();

	static {
		EnumSet<ProductExamineStatusEnum> set = EnumSet.allOf(ProductExamineStatusEnum.class);
		for (ProductExamineStatusEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}

	ProductExamineStatusEnum(Integer value, String name, Boolean hasPassOperate, Boolean hasRejectOperate,
			Boolean hasCancelOperate, Boolean showText) {
		this.value = value;
		this.name = name;
		this.hasPassOperate = hasPassOperate;
		this.hasRejectOperate = hasRejectOperate;
		this.hasCancelOperate = hasCancelOperate;
		this.showText = showText;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 获取枚举
	 */
	public static ProductExamineStatusEnum getEnum(Integer value) {
		if (value == null)
			return null;
		return enumMap.get(value);
	}

	public static Boolean canAudit(Integer value) {
		return ProductExamineStatusEnum.APPROVALING.getValue().equals(value);
	}

	/**
	 * 根据value 获取name
	 */
	public static String getEnumName(Integer value) {
		ProductExamineStatusEnum enumItem = getEnum(value);
		if (enumItem == null)
			return null;
		return enumItem.getName();
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getHasPassOperate() {
		return hasPassOperate;
	}

	public void setHasPassOperate(Boolean hasPassOperate) {
		this.hasPassOperate = hasPassOperate;
	}

	public Boolean getHasRejectOperate() {
		return hasRejectOperate;
	}

	public void setHasRejectOperate(Boolean hasRejectOperate) {
		this.hasRejectOperate = hasRejectOperate;
	}

	public Boolean getHasCancelOperate() {
		return hasCancelOperate;
	}

	public void setHasCancelOperate(Boolean hasCancelOperate) {
		this.hasCancelOperate = hasCancelOperate;
	}

	public static Map<Integer, ProductExamineStatusEnum> getEnumMap() {
		return enumMap;
	}

	public static void setEnumMap(Map<Integer, ProductExamineStatusEnum> enumMap) {
		ProductExamineStatusEnum.enumMap = enumMap;
	}

	public Boolean getShowText() {
		return showText;
	}

	public void setShowText(Boolean showText) {
		showText = showText;
	}
}
