
package com.yundao.tenant.app.view.product;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure1012View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String productId;
	/**
	 * 最低预约金额
	 */
	private String minAppointAmount;
	/**
	 * 预约递增金额
	 */
	private String increaseAppointAmount;
	/**
	 * 提示起始投资金额，XXX万起投，XX万递增
	 */
	private String startAmountAppointText;
	/**
	 * 新增报单模式：0：不能报单，1:直接报单，2:先预约再报单
	 */
	private Integer addOrderMode;
	/**
	 * 产品状态：0:未上线，1：上线准备中，2:预售中，3:募集中，4：募集结束，5：存续封闭中，6：清算退出
	 */
	private Integer productStatus;
	/**
	 * 产品状态文本
	 */
	private String productStatusText;
	/**
	 * 改产品的预约数（已确认的）
	 */
	private Integer productAppointNum;

	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getProductId() {

		return productId;
	}

	public void setProductId(String productId) {

		this.productId = productId;
	}

	public String getStartAmountAppointText() {

		return startAmountAppointText;
	}

	public void setStartAmountAppointText(String startAmountAppointText) {

		this.startAmountAppointText = startAmountAppointText;
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

	public String getProductStatusText() {
	
		return productStatusText;
	}

	public void setProductStatusText(String productStatusText) {
	
		this.productStatusText = productStatusText;
	}

	public Integer getProductAppointNum() {
	
		return productAppointNum;
	}

	public void setProductAppointNum(Integer productAppointNum) {
	
		this.productAppointNum = productAppointNum;
	}

}
