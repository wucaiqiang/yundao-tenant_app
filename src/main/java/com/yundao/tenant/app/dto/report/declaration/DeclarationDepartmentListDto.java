

package com.yundao.tenant.app.dto.report.declaration;

import com.yundao.tenant.app.dto.common.ActionDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月2日 下午6:12:53 
 * @author   欧阳利
 * @version   
 */
public class DeclarationDepartmentListDto {
    private Long departmentId;
    
    private Long userId;
    
    private String title;
    
    @ApiModelProperty("部门业绩文案。eg.5000万")
    private String recordText;
    
    @ApiModelProperty("部门业绩占比。eg.80%")
    private String recordPerText;
    
    

    
    private  ActionDTO  recordDetailAction;

	

	public void setRecordDetailAction(ActionDTO recordDetailAction) {
	
		this.recordDetailAction = recordDetailAction;
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

	public String getTitle() {
	
		return title;
	}

	public void setTitle(String title) {
	
		this.title = title;
	}

	public String getRecordText() {
	
		return recordText;
	}

	public void setRecordText(String recordText) {
	
		this.recordText = recordText;
	}

	public String getRecordPerText() {
	
		return recordPerText;
	}

	public void setRecordPerText(String recordPerText) {
	
		this.recordPerText = recordPerText;
	}

	public ActionDTO getRecordDetailAction() {
	
		return recordDetailAction;
	}

    
    
}

