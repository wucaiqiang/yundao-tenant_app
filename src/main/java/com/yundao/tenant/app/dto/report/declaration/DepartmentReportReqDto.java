

package com.yundao.tenant.app.dto.report.declaration;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午9:42:47 
 * @author   欧阳利
 * @version   
 */
public class DepartmentReportReqDto {
	@ApiModelProperty("部门id")
    private Long departmentId;
	 @ApiModelProperty("筛选1:自定义，2：本月, 3：上个月, 4：本季度, 5：上一季度, 6：本年度, 7：上一年度")
    private String filterType;
    @ApiModelProperty("自定义筛选开始时间")
    private Long customStart;
    @ApiModelProperty("自定义筛选结束时间")
    private Long customEnd;
	public Long getDepartmentId() {
	
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
	
		this.departmentId = departmentId;
	}
	public String getFilterType() {
	
		return filterType;
	}
	public void setFilterType(String filterType) {
	
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

