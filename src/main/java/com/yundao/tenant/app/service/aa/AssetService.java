

package com.yundao.tenant.app.service.aa;

import java.io.IOException;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.aa.asset.AddAssetAllocationReqDto;
import com.yundao.tenant.app.dto.aa.asset.AssetAllocationPageReqDto;
import com.yundao.tenant.app.dto.aa.plan.AssetPlanDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 上午9:27:25 
 * @author   欧阳利
 * @version   
 */
public interface AssetService {

	public Result<ItemListDTO> getIndex()throws BaseException;

	
	public Result<ItemListDTO> getPage(AssetAllocationPageReqDto reqDto)throws BaseException,IOException;
	
	
	public Result<AssetPlanDto> addAssetAllocation(@ModelAttribute AddAssetAllocationReqDto reqDto) throws Exception;
}

