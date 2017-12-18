

package com.yundao.tenant.app.controller.aa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.service.aa.AssetProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 上午11:56:10 
 * @author   欧阳利
 * @version   
 */
@Controller
@RequestMapping(value = "/asset")
@Api(value = "资产配置管理-->产品管理")
@ResponseBody
public class AssetProductController {

	@Autowired
	AssetProductService assetProductService;
	
 	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
 	@ApiOperation(value = "资产配置修改产品")
 	public Result<Integer> addAssetAllocationProduct(@RequestParam Long id,@RequestParam  String productIds)throws BaseException{
 		return assetProductService.addAssetAllocationProduct(id, productIds);
 	}

}

