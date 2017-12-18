package com.yundao.tenant.app.view.product;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.annotation.FieldCode;
import com.yundao.tenant.app.annotation.Owner;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.common.ViewItem;

/**
 * 
 * date: 2017年8月1日 下午4:09:03
 * 
 * @author:wucq
 * @description:
 */
public class ProductDetailView implements Serializable {

	private static final long serialVersionUID = -1606991347539089314L;
	protected ShareInfo shareInfo;
	private String amountUnit;
	private String minAppointAmount;
	private String increaseAppointAmount;
	private String startAmountAppointText;
	/**
	 * 0：不能报单，1:直接报单，2:先预约再报单
	 */
	private Integer addOrderMode;
	/**
	 * 0:未上线，1：上线准备中，2:预售中，3:募集中，4：募集结束，5：存续封闭中，6：清算退出
	 */
	private Integer productStatus;
	/**
	 * 改产品的预约数（已确认的）
	 */
	private Integer productAppointNum;
	/**
	 * 产品状态文本
	 */
	private String productStatusText;
	/**
	 * 产品对接人 姓名
	 */
	private String accessMan;
	/**
	 * 产品对接人手机号
	 */
	private String accessManMobile;
	private String wxInfo;
	protected List<ViewItem> viewItems;

	public ProductDetailView() {
	}

	public ShareInfo getShareInfo() {

		return shareInfo;
	}

	public void setShareInfo(ShareInfo shareInfo) {

		this.shareInfo = shareInfo;
	}

	public String getAmountUnit() {

		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {

		this.amountUnit = amountUnit;
	}

	public String getStartAmountAppointText() {

		return startAmountAppointText;
	}

	public void setStartAmountAppointText(String startAmountAppointText) {

		this.startAmountAppointText = startAmountAppointText;
	}

	public String getWxInfo() {

		return wxInfo;
	}

	public void setWxInfo(String wxInfo) {

		this.wxInfo = wxInfo;
	}

	public List<ViewItem> getViewItems() {

		return viewItems;
	}

	public void setViewItems(List<ViewItem> viewItems) {

		this.viewItems = viewItems;
	}

	public String getMinAppointAmount() {

		return minAppointAmount;
	}

	public void setMinAppointAmount(String minAppointAmount) {

		this.minAppointAmount = minAppointAmount;
	}

	public String getIncreaseAppointAmount() {

		return increaseAppointAmount;
	}

	public void setIncreaseAppointAmount(String increaseAppointAmount) {

		this.increaseAppointAmount = increaseAppointAmount;
	}

	public Integer getAddOrderMode() {

		return addOrderMode;
	}

	public void setAddOrderMode(Integer addOrderMode) {

		this.addOrderMode = addOrderMode;
	}

	public Integer getProductStatus() {

		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {

		this.productStatus = productStatus;
	}

	public Integer getProductAppointNum() {

		return productAppointNum;
	}

	public void setProductAppointNum(Integer productAppointNum) {

		this.productAppointNum = productAppointNum;
	}

	public String getProductStatusText() {

		return productStatusText;
	}

	public void setProductStatusText(String productStatusText) {

		this.productStatusText = productStatusText;
	}

	public String getAccessMan() {

		return accessMan;
	}

	public void setAccessMan(String accessMan) {

		this.accessMan = accessMan;
	}

	public String getAccessManMobile() {

		return accessManMobile;
	}

	public void setAccessManMobile(String accessManMobile) {

		this.accessManMobile = accessManMobile;
	}

}
