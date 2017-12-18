package com.yundao.tenant.app.dto.sale.declaration;

import com.yundao.tenant.app.dto.AbstractBasePageDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/9/4.
 */
public class DeclarationSelectListQueryDto extends AbstractBasePageDto {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("客户ID")
	private String customerId;
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
	
	

}
