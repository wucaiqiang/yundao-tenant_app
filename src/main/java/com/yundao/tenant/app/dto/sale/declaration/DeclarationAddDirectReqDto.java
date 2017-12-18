
package com.yundao.tenant.app.dto.sale.declaration;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年9月6日 下午4:18:41
 * 
 * @author wucq
 * @version
 */
public class DeclarationAddDirectReqDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 客户ID
	 */
	private Long customerId;
	/**
	 * 认购金额
	 */
	private String renGouAmount;
	/**
	 * 打款时间 时间戳
	 */
	private Long giveAmountDate;
	/**
	 * 备注
	 */
	private String remark;


	public String getRenGouAmount() {

		return renGouAmount;
	}

	public void setRenGouAmount(String renGouAmount) {

		this.renGouAmount = renGouAmount;
	}

	public Long getGiveAmountDate() {

		return giveAmountDate;
	}

	public void setGiveAmountDate(Long giveAmountDate) {

		this.giveAmountDate = giveAmountDate;
	}

	public String getRemark() {

		return remark;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}


	public Long getProductId() {

		return productId;
	}

	public void setProductId(Long productId) {

		this.productId = productId;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

}
