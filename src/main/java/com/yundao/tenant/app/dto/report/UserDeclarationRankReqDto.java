package com.yundao.tenant.app.dto.report;

import java.util.Date;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

public class UserDeclarationRankReqDto extends AbstractBasePageDto{
   private Long departmentId;
   
   private Integer filterType;
   
   private Long customStart;
   
   private Long customEnd;
   
   private Long userId;
   
   

public Long getUserId() {

	return userId;
}

public void setUserId(Long userId) {

	this.userId = userId;
}

public Long getDepartmentId() {
	return departmentId;
}

public void setDepartmentId(Long departmentId) {
	this.departmentId = departmentId;
}

public Integer getFilterType() {
	return filterType;
}

public void setFilterType(Integer filterType) {
	this.filterType = filterType;
}

public Long getCustomStart() {

	return customStart;
}

public void setCustomStart(Long customStart) {

	this.customStart = customStart;
}

public Long getCustomEnd() {

	return customEnd;
}

public void setCustomEnd(Long customEnd) {

	this.customEnd = customEnd;
}



   
   
}
