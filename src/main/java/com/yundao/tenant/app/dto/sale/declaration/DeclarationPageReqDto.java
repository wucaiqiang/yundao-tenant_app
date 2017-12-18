

package com.yundao.tenant.app.dto.sale.declaration;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月2日 下午1:50:09 
 * @author   wucq
 * @version   
 */
public class DeclarationPageReqDto extends AbstractBasePageDto {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("栏目ID,为空则请求全部")
    private String filterType;
	public String getFilterType() {
	
		return filterType;
	}
	public void setFilterType(String filterType) {
	
		this.filterType = filterType;
	}

}

