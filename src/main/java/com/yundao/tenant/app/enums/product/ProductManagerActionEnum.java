package com.yundao.tenant.app.enums.product;

public enum ProductManagerActionEnum {
	/**
	 * 申请上线
	 */
	APPLY_ON_LINE(1, "申请上线"),

	/**
	 * 取消申请上线
	 */
	CANCEL_APPLY_ON_LINE(2, "取消申请"),

	/**
	 * 终止发行
	 */
	STOP_ISSUED(3, "终止发行"),

	/**
	 * 重新申请上线
	 */
	AGAIN_APPLY_ON_LINE(4, "重新申请"),

	/**
	 * 启动上线发行：预售
	 */
	START_UP_PRE_SAISE(5, "启动预售"),

	/**
	 * 启动募集
	 */
	START_RAISE(6, "启动募集"),

	/**
	 * 启动上线发行：募集中
	 */
	START_ON_LINE_RAISE_ING(7, "启动募集"),

	/**
	 * 返回上线准备
	 */
	RETURN_ON_LINE_READY(8, "暂停预售"),

	/**
	 * 设为募集结束
	 */
	SET_RAISE_END(9, "结束募集"),

	/**
	 * 开放申购
	 */
	OPEN_PURCHASE(10, "开放申购"),

	/**
	 * 设为存续/封闭
	 */
	SET_SURVIVING(11, "设为封闭中"),

	/**
	 * 设为清算退出
	 */
	SET_CLEAR_OUT(12, "清算退出"),
	/**
	 * 返回上线准备(募集中到上线准备中)
	 */
	RETURN_ON_LINE_READY_FROM_RAISE(13, "暂停募集");

	private Integer code;

	private String name;

	ProductManagerActionEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
