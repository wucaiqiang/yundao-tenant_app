

package com.yundao.tenant.app.dto.workflow;

import com.yundao.tenant.app.view.workflow.WkActionView;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月21日 上午9:07:47 
 * @author   gjl
 * @version   
 */
public class BaseWkServiceDto extends WkActionView {
	List<WkBusinessInfoView> businessInfoViews;
	String[] params;

	public BaseWkServiceDto(List<WkBusinessInfoView> businessInfoViews, String[] params) {
		this.businessInfoViews = businessInfoViews;
		this.params = params;
	}

	public BaseWkServiceDto() {
	}

	public List<WkBusinessInfoView> getBusinessInfoViews() {
		return businessInfoViews;
	}

	public void setBusinessInfoViews(List<WkBusinessInfoView> businessInfoViews) {
		this.businessInfoViews = businessInfoViews;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}
}

