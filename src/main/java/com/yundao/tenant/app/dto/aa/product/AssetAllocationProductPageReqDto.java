

package com.yundao.tenant.app.dto.aa.product;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 下午4:27:57 
 * @author   欧阳利
 * @version   
 */
public class AssetAllocationProductPageReqDto extends AbstractBasePageDto {

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	private Long assetAllocationId;

	public Long getAssetAllocationId() {
	
		return assetAllocationId;
	}

	public void setAssetAllocationId(Long assetAllocationId) {
	
		this.assetAllocationId = assetAllocationId;
	}
	
}

