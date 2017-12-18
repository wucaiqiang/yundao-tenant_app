

package com.yundao.tenant.app.dto.report.declaration;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 下午5:05:43 
 * @author   欧阳利
 * @version   
 */
public class DeclarationTopUserDto {
	@ApiModelProperty("员工名称")
    private String name;
	@ApiModelProperty("员工业绩文案")
    private String recordText;
	@ApiModelProperty("员工业绩")
	private Double record;
	@ApiModelProperty("表最大值")
	private Double maxRecord;
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
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

