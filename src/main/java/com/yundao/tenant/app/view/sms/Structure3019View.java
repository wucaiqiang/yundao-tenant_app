
package com.yundao.tenant.app.view.sms;

import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:36:08
 * 
 * @author wucq
 * @version
 */
public class Structure3019View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String smsId;
	private String content;
	private String date;
	private Integer state;
	private List<String> customerNameList;
	public String getSmsId() {
	
		return smsId;
	}
	public void setSmsId(String smsId) {
	
		this.smsId = smsId;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	
	public String getDate() {
	
		return date;
	}
	public void setDate(String date) {
	
		this.date = date;
	}
	public Integer getState() {
	
		return state;
	}
	public void setState(Integer state) {
	
		this.state = state;
	}
	public List<String> getCustomerNameList() {
	
		return customerNameList;
	}
	public void setCustomerNameList(List<String> customerNameList) {
	
		this.customerNameList = customerNameList;
	}
	

}
