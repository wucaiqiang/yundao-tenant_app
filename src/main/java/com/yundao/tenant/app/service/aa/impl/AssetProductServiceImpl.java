

package com.yundao.tenant.app.service.aa.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.service.aa.AssetProductService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 下午1:51:59 
 * @author   欧阳利
 * @version   
 */
@Service
public class AssetProductServiceImpl implements AssetProductService {

	
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
	public Result<Integer> addAssetAllocationProduct(Long id, String productIds)throws BaseException{
		 Map<String, Object> map = new HashMap<String, Object>();  
		 map.put("id", id);
		 map.put("productIds", productIds);
		 Result<Integer> result = HttpUtils.post(TenantUrl.ASSET_PRODUCT_UPDATE, map,
					new BaseTypeReference<Result<Integer>>() {
					});
		return result;
	}
}

