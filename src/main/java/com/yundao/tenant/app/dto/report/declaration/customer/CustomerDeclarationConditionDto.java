

package com.yundao.tenant.app.dto.report.declaration.customer;

import java.util.List;

import com.yundao.tenant.app.dto.sale.declaration.DeclarationUserDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 下午3:05:05 
 * @author   欧阳利
 * @version   
 */
public class CustomerDeclarationConditionDto {
	
    @ApiModelProperty("成单理财师列表")
    List<DeclarationUserDto> financialPlannerList;
    
    List<CustomerType> customerTypeList;

	public List<DeclarationUserDto> getFinancialPlannerList() {
	
		return financialPlannerList;
	}

	public void setFinancialPlannerList(List<DeclarationUserDto> financialPlannerList) {
	
		this.financialPlannerList = financialPlannerList;
	}

	public List<CustomerType> getCustomerTypeList() {
	
		return customerTypeList;
	}

	public void setCustomerTypeList(List<CustomerType> customerTypeList) {
	
		this.customerTypeList = customerTypeList;
	}
    
    
}

