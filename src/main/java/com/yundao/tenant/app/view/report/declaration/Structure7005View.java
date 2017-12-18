

package com.yundao.tenant.app.view.report.declaration;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.report.declaration.DeclarationDepartmentListDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:43:52 
 * @author   欧阳利
 * @version   
 */
public class Structure7005View implements DataDTO {
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	List<DeclarationDepartmentListDto> departments;

	public List<DeclarationDepartmentListDto> getDepartments() {
	
		return departments;
	}

	public void setDepartments(List<DeclarationDepartmentListDto> departments) {
	
		this.departments = departments;
	}
}

