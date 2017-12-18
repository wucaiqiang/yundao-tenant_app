
package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Function: Reason: Date: 2017年9月5日 上午11:46:38
 * 
 * @author wucq
 * @version
 */
public class SubscribeView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 认购金额文本
	 */
	private String renGouAmountText;
	/**
	 * 认购金额
	 */
	private String renGouAmount;
	/**
	 * 打开日期
	 */
	private Date giveAmountDate;
	/**
	 * 打开凭证的图片集合
	 */
	private List<String> giveAmountCertificateUrlList;
	/**
	 * 合同附件图片集合
	 */
	private List<String> contractAttachUrlList;

	public String getRenGouAmountText() {

		return renGouAmountText;
	}

	public void setRenGouAmountText(String renGouAmountText) {

		this.renGouAmountText = renGouAmountText;
	}

	public String getRenGouAmount() {

		return renGouAmount;
	}

	public void setRenGouAmount(String renGouAmount) {

		this.renGouAmount = renGouAmount;
	}

	public Long getGiveAmountDate() {
		if (giveAmountDate != null) {
			return giveAmountDate.getTime();
		}
		return null;
	}

	public void setGiveAmountDate(Date giveAmountDate) {

		this.giveAmountDate = giveAmountDate;
	}

	public List<String> getGiveAmountCertificateUrlList() {

		return giveAmountCertificateUrlList;
	}

	public void setGiveAmountCertificateUrlList(List<String> giveAmountCertificateUrlList) {

		this.giveAmountCertificateUrlList = giveAmountCertificateUrlList;
	}

	public List<String> getContractAttachUrlList() {

		return contractAttachUrlList;
	}

	public void setContractAttachUrlList(List<String> contractAttachUrlList) {

		this.contractAttachUrlList = contractAttachUrlList;
	}

}
