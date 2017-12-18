

package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月1日 下午4:44:49 
 * @author   wucq
 * @version   
 */
public class ProductListView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 左边的值 
	 */
	private String leftValue;
	/**
	 * 
	 */
	private String leftName;
	/**
	 * 中间值
	 */
	private String middleValue;
	/**
	 * 
	 */
	private String middleName;
	/**
	 * 右边值 
	 */
	private String rightValue;
	/**
	 * 
	 */
	private String rightName;
	/**
	 * 产品类别
	 */
	private String productTypeText;
	/**
	 * 产品规模
	 */
	private String scope;
	/**
	 * 销售
	 */
	private String sale;
	/**
	 * 
	 */
	private List<String> tags;
	public String getProductName() {
	
		return productName;
	}
	public void setProductName(String productName) {
	
		this.productName = productName;
	}
	public String getLeftValue() {
	
		return leftValue;
	}
	public void setLeftValue(String leftValue) {
	
		this.leftValue = leftValue;
	}
	public String getLeftName() {
	
		return leftName;
	}
	public void setLeftName(String leftName) {
	
		this.leftName = leftName;
	}
	public String getMiddleValue() {
	
		return middleValue;
	}
	public void setMiddleValue(String middleValue) {
	
		this.middleValue = middleValue;
	}
	public String getMiddleName() {
	
		return middleName;
	}
	public void setMiddleName(String middleName) {
	
		this.middleName = middleName;
	}
	public String getRightValue() {
	
		return rightValue;
	}
	public void setRightValue(String rightValue) {
	
		this.rightValue = rightValue;
	}
	public String getRightName() {
	
		return rightName;
	}
	public void setRightName(String rightName) {
	
		this.rightName = rightName;
	}
	public List<String> getTags() {
	
		return tags;
	}
	public void setTags(List<String> tags) {
	
		this.tags = tags;
	}
	public String getProductTypeText() {
	
		return productTypeText;
	}
	public void setProductTypeText(String productTypeText) {
	
		this.productTypeText = productTypeText;
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
	

}

