

package com.yundao.tenant.app.dto.report.declaration.customer;

import java.util.Date;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 下午5:42:23 
 * @author   欧阳利
 * @version   
 */
public class DeclarationListReqDto  extends AbstractBasePageDto {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("筛选")
    private Integer filterType;
    
    @ApiModelProperty("自定义筛选开始时间")
    private Long customStart;
    
    @ApiModelProperty("自定义筛选结束时间")
    private Long customEnd;
    
    @ApiModelProperty("客户id")
    private Long customerId;

	public Long getCustomerId() {
	
		return customerId;
	}

	public void setCustomerId(Long customerId) {
	
		this.customerId = customerId;
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

