package com.yundao.tenant.app.enums.workflow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 报单状态
 *
 * @author gjl
 * @create 2017-07-22 PM3:36
 **/
public enum DeclarationStatusEnum {

	/**
	 * 待审核
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
	CANCEL(4, "已取消", false, false, false, true),
	/**
	 * 已作废
	 */
	DISCARD(5, "已作废", false, false, false, true),

	/**
	 * 已退款
	 */
	REFUND(6, "已退款", false, false, false, true);

	private Integer value;
	private String name;

	private Boolean hasPassOperate;
	private Boolean hasRejectOperate;
	private Boolean hasCancelOperate;
	private Boolean showText;

	DeclarationStatusEnum(Integer value, String name, Boolean hasPassOperate, Boolean hasRejectOperate,
			Boolean hasCancelOperate, Boolean showText) {
		this.value = value;
		this.name = name;
		this.hasPassOperate = hasPassOperate;
		this.hasRejectOperate = hasRejectOperate;
		this.hasCancelOperate = hasCancelOperate;
		this.showText = showText;
	}

	private static Map<Integer, DeclarationStatusEnum> enumMap = new HashMap<>();

	static {
		EnumSet<DeclarationStatusEnum> set = EnumSet.allOf(DeclarationStatusEnum.class);
		for (DeclarationStatusEnum each : set) {
			enumMap.put(each.getValue(), each);
		}
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * 获取枚举
	 */
	public static DeclarationStatusEnum getEnum(Integer value) {
		if (value == null)
			return null;
		return enumMap.get(value);
	}

	/**
	 * 获取在审核者页面显示的文本
	 */
	public static String getName(Integer value) {
		DeclarationStatusEnum enumItem = getEnum(value);
		if (enumItem == null)
			return null;
		return enumItem.getName();
	}

	/**
	 * 是否能进行审核
	 *
	 * @param value
	 * @return
	 */
	public static Boolean canAudit(Integer value) {
		return DeclarationStatusEnum.APPROVALING.getValue().equals(value);
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

	public static Map<Integer, DeclarationStatusEnum> getEnumMap() {
		return enumMap;
	}

	public static void setEnumMap(Map<Integer, DeclarationStatusEnum> enumMap) {
		DeclarationStatusEnum.enumMap = enumMap;
	}

	public Boolean getShowText() {
		return showText;
	}

	public void setShowText(Boolean showText) {
		showText = showText;
	}
}
