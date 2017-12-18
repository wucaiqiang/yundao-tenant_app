

package com.yundao.tenant.app.view.report.declaration;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:43:52 
 * @author   欧阳利
 * @version   
 */
public class Structure7009View implements DataDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
    
    private Integer orderCount;
    
    private String recordText;
    
    private Double record;
    
    private Double maxRecord;
    
    private String userName;

	public String getUserName() {
	
		return userName;
	}

	public void setUserName(String userName) {
	
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public String getRecordText() {
		return recordText;
	}

	public void setRecordText(String recordText) {
		this.recordText = recordText;
	}

	public Double getRecord() {
		return record;
	}

	public void setRecord(Double record) {
		this.record = record;
	}

	public Double getMaxRecord() {
	
		return maxRecord;
	}

	public void setMaxRecord(Double maxRecord) {
	
		this.maxRecord = maxRecord;
	}

	


}

