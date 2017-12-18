

package com.yundao.tenant.app.controller.aa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.aa.QuestionDto;
import com.yundao.tenant.app.dto.aa.RiskQuestion;
import com.yundao.tenant.app.dto.aa.asset.AddAssetAllocationReqDto;
import com.yundao.tenant.app.dto.aa.asset.AssetAllocationPageReqDto;
import com.yundao.tenant.app.dto.aa.plan.AssetPlanDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.service.aa.AssetService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.util.HttpUtils;

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
@Api(value = "资产配置管理")
@ResponseBody
public class AssetController {

	@Autowired
	AssetService assetService;
	@Autowired
	ShareService shareService;

	@RequestMapping(value = "/gets_risk_question", method = RequestMethod.POST)
	@ApiOperation(value = "获取风险评估题目")
	public Result<RiskQuestion> getRiskQuestions() throws Exception {
		 Result<List<QuestionDto>> result = HttpUtils.get(TenantUrl.GETS_RISK_QUESTION, null, new BaseTypeReference<Result<List<QuestionDto>>>() {
	        });
		 RiskQuestion r = new RiskQuestion();
		 r.setQuestions(result.getResult());
		 return Result.newSuccessResult(r);
		
	}
	
	@RequestMapping(value = "/get_index", method = RequestMethod.POST)
 	@ApiOperation(value = "资产配置首页")
	public Result<ItemListDTO> getIndex()throws BaseException{
		return assetService.getIndex();
	}
	
	
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加资产配置(理财师帮客户测评)")
	public Result<AssetPlanDto> addAssetAllocation(@ModelAttribute AddAssetAllocationReqDto reqDto) throws Exception {
    	return assetService.addAssetAllocation(reqDto);
//    	Result<AssetPlanDto> result = null;
//    	if(reqDto.getId() == null){
//    		result =  HttpUtils.post(TenantUrl.ASSET_ADD, ArgsUtils.toMap(reqDto),
//					new BaseTypeReference<Result<AssetPlanDto>>() {
//					});
//    	}else{
//    		result =  HttpUtils.post(TenantUrl.ASSET_UPDATE, ArgsUtils.toMap(reqDto),
//					new BaseTypeReference<Result<AssetPlanDto>>() {
//					});
//    	}
//    	if(result.getSuccess()){
//    		AssetPlanDto dto = result.getResult();
//    		String resultUrl = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),true);
//    		//dto.setResultUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=true");
//    		dto.setResultUrl(resultUrl);
//    		if(reqDto.getId() == null){
//    			dto.setProducIdtList(new ArrayList<Long>());
//    		}else{
//    			// 查询测评关联的产品id集合
//    			Result<List<Long>> productResult = HttpUtils.get(TenantUrl.ASSET_PRODUCT_GET_PRODUCTIDS, ArgsUtils.toIdMap(reqDto.getId()),
//    					new BaseTypeReference<Result<List<Long>>>() {
//    					});
//    			dto.setProducIdtList(productResult.getResult());
//    		}
//    		// 设置分享地址
//    		ShareInfoDto shareInfo = new ShareInfoDto();
//    		String url = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),false);
//    		//shareInfo.setUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=false");
//    		shareInfo.setUrl(url);
//    		dto.setShareInfo(shareInfo);
//    	}
//    	
//    	return result;
	}

    
    @RequestMapping(value = "/get_page", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询资产配置(历史配置)")
 	public Result<ItemListDTO> getPage(@ModelAttribute AssetAllocationPageReqDto reqDto)throws Exception{
 		return assetService.getPage(reqDto);
 	}
    
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除资产配置")
    public Result<Integer> delete(String ids)throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();  
    	map.put("ids", ids);
    	return  HttpUtils.post(TenantUrl.ASSET_DELETE, map,
				new BaseTypeReference<Result<Integer>>() {
				});
    }
	
}

