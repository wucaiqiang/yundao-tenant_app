

package com.yundao.tenant.app.view.report;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.NameTextView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月19日 下午8:09:52 
 * @author   wucq
 * @version   
 */
public class ReportView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标题：报单总额，预约总额
	 */
	private String title;
	/**
	 * 总额
	 */
	private String totalAmount;
	/**
	 * 第一名
	 */
	private NameTextView first;
	/**
	 * 第二名
	 */
	private NameTextView second;
	/**
	 * 第三名
	 */
	private NameTextView third;
	public String getTotalAmount() {
	
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
	
		this.totalAmount = totalAmount;
	}
	public NameTextView getFirst() {
	
		return first;
	}
	public void setFirst(NameTextView first) {
	
		this.first = first;
	}
	public NameTextView getSecond() {
	
		return second;
	}
	public void setSecond(NameTextView second) {
	
		this.second = second;
	}
	public NameTextView getThird() {
	
		return third;
	}
	public void setThird(NameTextView third) {
	
		this.third = third;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}

}

