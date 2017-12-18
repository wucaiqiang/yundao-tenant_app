

package com.yundao.tenant.app.dto.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月20日 下午4:48:43 
 * @author   wucq
 * @version   
 */
public class BeginEndDateDto implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Date beginDate;
	private Date endDate;
	private String timeType;
	public Date getBeginDate() {
	
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
	
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
	
		return endDate;
	}
	public void setEndDate(Date endDate) {
	
		this.endDate = endDate;
	}
	public String getTimeType() {
	
		return timeType;
	}
	public void setTimeType(String timeType) {
	
		this.timeType = timeType;
	}
	
}

