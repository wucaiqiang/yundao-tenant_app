package com.yundao.tenant.app.dto.sale.declaration;

import com.yundao.tenant.app.model.sale.declaration.DeclarationModel;

/**
 * 
 * date: 2017年9月2日 下午1:59:19
 * @author:wucq
 * @description:
 */
public class DeclarationDto extends DeclarationModel {
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private String productName;

    private String customerName;

    private String statusName;

    private String createUserName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
