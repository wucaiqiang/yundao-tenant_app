

package com.yundao.tenant.app.dto.report.declaration.customer;

import java.util.Date;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 上午10:58:12 
 * @author   欧阳利
 * @version   
 */
public class DeclarationRankReqDto extends AbstractBasePageDto  {
	
	
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
    
    @ApiModelProperty("成单理财师Id")
    private Long userId;
    
    @ApiModelProperty("客户类型，默认为空，所有客户, 1:首次成单客户  2： 多次成单客户")
    private Integer customerType;
    
    private Long departmentId;
    
    

	public void setCustomStart(Long customStart) {
	
		this.customStart = customStart;
	}

	public void setCustomEnd(Long customEnd) {
	
		this.customEnd = customEnd;
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




	public Long getUserId() {
	
		return userId;
	}

	public void setUserId(Long userId) {
	
		this.userId = userId;
	}

	public Integer getCustomerType() {
	
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
	
		this.customerType = customerType;
	}

	public Long getCustomStart() {
	
		return customStart;
	}

	public Long getCustomEnd() {
	
		return customEnd;
	}
    
    
    
}

