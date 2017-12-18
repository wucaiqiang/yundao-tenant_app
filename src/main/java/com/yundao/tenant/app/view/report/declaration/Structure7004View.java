

package com.yundao.tenant.app.view.report.declaration;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.report.declaration.DepartmentUserSelectDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:43:52 
 * @author   欧阳利
 * @version   
 */
public class Structure7004View implements DataDTO {
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Long upperDepartmentId;
	private String departmentName;
    private List<DepartmentUserSelectDto> departments;
    
	public String getDepartmentName() {
	
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
	
		this.departmentName = departmentName;
	}
	public Long getUpperDepartmentId() {
	
		return upperDepartmentId;
	}
	public void setUpperDepartmentId(Long upperDepartmentId) {
	
		this.upperDepartmentId = upperDepartmentId;
	}
	public List<DepartmentUserSelectDto> getDepartments() {
	
		return departments;
	}
	public void setDepartments(List<DepartmentUserSelectDto> departments) {
	
		this.departments = departments;
	}
    
    
    
}

