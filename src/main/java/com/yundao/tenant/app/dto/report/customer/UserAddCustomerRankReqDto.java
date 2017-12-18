

package com.yundao.tenant.app.dto.report.customer;


import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年12月2日 上午11:02:03 
 * @author   欧阳利
 * @version   
 */
public class UserAddCustomerRankReqDto extends AbstractBasePageDto{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("部门id")
	private Long departmentId;
	@ApiModelProperty("部门code")
	private String departmentCode;
	@ApiModelProperty("用户id")
	private Long userId;
	@ApiModelProperty("过滤类型")
	private Integer filterType;
	@ApiModelProperty("开始日期")
	private Long customStart;
	@ApiModelProperty("结束日期")
    private Long customEnd;


	public String getDepartmentCode() {
	
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
	
		this.departmentCode = departmentCode;
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

