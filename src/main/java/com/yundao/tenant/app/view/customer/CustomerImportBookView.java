
package com.yundao.tenant.app.view.customer;

import java.io.Serializable;
import java.util.List;

/**
 * Function: Reason: Date: 2017年11月27日 上午11:37:49
 * 
 * @author wucq
 * @version
 */
public class CustomerImportBookView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Integer resultType;
	private String message;
	private List<NameFailReasonView> customerFailList;
	public Integer getResultType() {
	
		return resultType;
	}
	public void setResultType(Integer resultType) {
	
		this.resultType = resultType;
	}
	public String getMessage() {
	
		return message;
	}
	public void setMessage(String message) {
	
		this.message = message;
	}
	public List<NameFailReasonView> getCustomerFailList() {
	
		return customerFailList;
	}
	public void setCustomerFailList(List<NameFailReasonView> customerFailList) {
	
		this.customerFailList = customerFailList;
	}
	

}
