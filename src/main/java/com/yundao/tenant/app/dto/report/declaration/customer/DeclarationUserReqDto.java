

package com.yundao.tenant.app.dto.report.declaration.customer;

import java.util.Date;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 下午3:44:01 
 * @author   欧阳利
 * @version   
 */
public class DeclarationUserReqDto {
    private Integer filterType;
    
    private Long customStart;
    
    private Long customEnd;



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

