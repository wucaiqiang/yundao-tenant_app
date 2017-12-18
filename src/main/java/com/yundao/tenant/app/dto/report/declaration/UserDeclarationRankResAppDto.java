package com.yundao.tenant.app.dto.report.declaration;

public class UserDeclarationRankResAppDto {
    private String name;
    
    private Integer orderCount;
    
    private String recordText;
    
    private Double record;
    
    private Double maxRecord;

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
