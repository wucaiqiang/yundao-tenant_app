
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure1013View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品Id
	 */
	private String productId;
	/**
	 * 产品类别
	 */
	private String productTypeText;
	/**
	 * 产品对接人/产品助理
	 */
	private String productDockMan;
	/**
	 * 产品级别：e.g. A，B
	 */
	private String productLevelText;
	/**
	 * 产品规模： e.g. 规模 5000万 没有则为空
	 */
	private String scope;
	/**
	 * 销售 e.g.预约 1800万 / 报单 600万 如果两个值都没有则显示为空
	 */
	private String sale;
	/**
	 * 销售 e.g.预约 1800万 / 报单 600万 如果两个值都没有则显示为空
	 */
	private String content;
	/**
	 * 驳回和取消原因
	 */
	private String reason;
	/**
	 * 驳回和取消原因
	 */
	private List<OperateView> operateList;
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
	public String getProductTypeText() {
	
		return productTypeText;
	}
	public void setProductTypeText(String productTypeText) {
	
		this.productTypeText = productTypeText;
	}
	public String getProductDockMan() {
	
		return productDockMan;
	}
	public void setProductDockMan(String productDockMan) {
	
		this.productDockMan = productDockMan;
	}
	public String getProductLevelText() {
	
		return productLevelText;
	}
	public void setProductLevelText(String productLevelText) {
	
		this.productLevelText = productLevelText;
	}
	public String getScope() {
	
		return scope;
	}
	public void setScope(String scope) {
	
		this.scope = scope;
	}
	public String getSale() {
	
		return sale;
	}
	public void setSale(String sale) {
	
		this.sale = sale;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public String getReason() {
	
		return reason;
	}
	public void setReason(String reason) {
	
		this.reason = reason;
	}
	public List<OperateView> getOperateList() {
	
		return operateList;
	}
	public void setOperateList(List<OperateView> operateList) {
	
		this.operateList = operateList;
	}
	

}
