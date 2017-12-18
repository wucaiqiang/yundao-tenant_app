

package com.yundao.tenant.app.dto.report.declaration;

import java.util.Date;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月4日 上午8:56:29 
 * @author   欧阳利
 * @version   
 */
public class DepartUserDeclarationReportReqDto extends AbstractBasePageDto{
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private Long departmentId;
    
    private Long userId;
    
    private Integer filterType;
    
    private Long customStart;
    
    private Long customEnd;
    private String statType;
    




	public String getStatType() {
	
		return statType;
	}

	public void setStatType(String statType) {
	
		this.statType = statType;
	}

	public Long getDepartmentId() {
	
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
	
		this.departmentId = departmentId;
	}


	public Long getUserId() {
	
		return userId;
	}

	public void setUserId(Long userId) {
	
		this.userId = userId;
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

