

package com.yundao.tenant.app.service.aa;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 下午1:51:41 
 * @author   欧阳利
 * @version   
 */
public interface AssetProductService {

	/**
	 * 添加测评的产品
	 * addAssetAllocationProduct:
	 * @author: 欧阳利
	 * @param id
	 * @param productIds
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<Integer> addAssetAllocationProduct(Long id, String productIds)throws BaseException;
	
}

