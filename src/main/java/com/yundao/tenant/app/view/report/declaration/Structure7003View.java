

package com.yundao.tenant.app.view.report.declaration;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.report.declaration.ReportButtonDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:43:52 
 * @author   欧阳利
 * @version   
 */
public class Structure7003View implements DataDTO {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private List<ReportButtonDto> items;

	public String getTitle() {
	
		return title;
	}

	public void setTitle(String title) {
	
		this.title = title;
	}

	public List<ReportButtonDto> getItems() {
	
		return items;
	}

	public void setItems(List<ReportButtonDto> items) {
	
		this.items = items;
	}
	
    
}

