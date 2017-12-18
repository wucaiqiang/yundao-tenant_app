

package com.yundao.tenant.app.dto.product;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年10月13日 下午1:39:21 
 * @author   欧阳利
 * @version   
 */
public class UpdateProductStatusReqDto {

	@ApiModelProperty("产品id")
	private  Long productId;
	@ApiModelProperty("募集形式： 1：直接报单: 2：先预约在报单")
	private Integer declarationModel;  
	@ApiModelProperty("操作1：申请上线; 2:取消申请  3:终止发行   4:重新申请   5:启动预售     6/7:启动募集 / 启动上线发行：募集中(启动募集)     8:暂停预售   9:结束募集   10:开放申购    11:设为封闭中   12:清算退出    ")
	private Integer type;	
	@ApiModelProperty("取消时，需要填原因")
	private String reason;
	
	public Integer getDeclarationModel() {
	
		return declarationModel;
	}
	public void setDeclarationModel(Integer declarationModel) {
	
		this.declarationModel = declarationModel;
	}
	public String getReason() {
	
		return reason;
	}
	public void setReason(String reason) {
	
		this.reason = reason;
	}
	
	public Long getProductId() {
	
		return productId;
	}
	public void setProductId(Long productId) {
	
		this.productId = productId;
	}
	public Integer getType() {
	
		return type;
	}
	public void setType(Integer type) {
	
		this.type = type;
	}
	
}

