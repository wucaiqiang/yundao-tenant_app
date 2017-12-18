

package com.yundao.tenant.app.service.aa.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.aa.ShareInfoDto;
import com.yundao.tenant.app.dto.aa.asset.AddAssetAllocationReqDto;
import com.yundao.tenant.app.dto.aa.asset.AssetAllocationPageReqDto;
import com.yundao.tenant.app.dto.aa.asset.AssetAllocationPageResDto;
import com.yundao.tenant.app.dto.aa.plan.AssetPlanDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.service.aa.AssetService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.service.user.detail.UserDetailService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.SymbolUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.aa.Structure6View;
import com.yundao.tenant.app.view.aa.Structure7View;
import com.yundao.tenant.app.view.aa.Structure8View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 上午9:27:44 
 * @author   欧阳利
 * @version   
 */
@Service
public class  AssetServiceImpl extends AbstractService implements AssetService {
	
	@Autowired
	ShareService shareService;
	@Autowired
	UserDetailService userDetailService;
	
	public Result<ItemListDTO> getIndex()throws BaseException{
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
        // 帮助客户完成
		helpCustomerFinishInfo(viewItems);
		// 分享给客户完成
		shareCustomerFinishInfo(viewItems);
		ItemListDTO dto = new ItemListDTO(viewItems);
		return Result.newSuccessResult(dto);
	}
	


	public Result<ItemListDTO> getPage(AssetAllocationPageReqDto reqDto)throws BaseException,IOException{
		// 查询数据
		reqDto.setUserId(UserUtils.getUserId());
		Result<PaginationSupport<AssetAllocationPageResDto>> result = HttpUtils.get(TenantUrl.ASSET_GET_PAGE, ArgsUtils.toMap(reqDto),
				new BaseTypeReference<Result<PaginationSupport<AssetAllocationPageResDto>>>() {
				});
		
		if(!result.getSuccess()){
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		PaginationSupport<AssetAllocationPageResDto> page = result.getResult();
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		
		
		List<AssetAllocationPageResDto> datas = page.getDatas();
		if(!BooleanUtils.isEmpty(datas)){
			for(AssetAllocationPageResDto dto: datas){
				Structure8View structure8View = new Structure8View();
				structure8View.setContent(dto.getProductCount()+"个");
				structure8View.setDate(DateUtils.format(dto.getCreateDate(), DateUtils.YYYY_MM_DD));
				structure8View.setEvaluationTypeText(dto.getRiskText());
				structure8View.setId(dto.getId());
				structure8View.setName(dto.getCustomerName());
				// 设置分享地址
	    		ShareInfoDto shareInfoDto = new ShareInfoDto();
	    		String url = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),false);
	    		//shareInfoDto.setUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=false");
	    		shareInfoDto.setUrl(url);
	    		shareInfoDto.setUrl(URLEncoder.encode(shareInfoDto.getUrl(), "UTF-8"));
	    		shareInfoDto.setContent(UserUtils.getCurrentUser().getHeaderRealName()+"为您评估风险偏好，量身打造适合您的资产配置方案");
	    		shareInfoDto.setTitle(UserUtils.getCurrentUser().getHeaderRealName()+" 为您量身定制的资产配置方案");
	    		shareInfoDto.setImgUrl(ConfigUtils.getValue("user.default.avater.url"));
	    		
				String shareInfo = JsonUtils.objectToJson(shareInfoDto);
				String productIds = "";
				if(!BooleanUtils.isEmpty(dto.getProductIds())){
					productIds = SymbolUtils.longToStr(dto.getProductIds());
				}
				//String url = URLEncoder.encode(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=true", "UTF-8");
				String shareUrl = URLEncoder.encode(String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),true), "UTF-8");
				String jumpUrl = Schema.EVALUATE_MANAGER_RESULT 
						 + String.format(Schema.EVALUATE_MANAGER_RESULT_PARAMS, dto.getId(), shareInfo,productIds,shareUrl);
				viewItems.add(new ViewItem(8, jumpUrl, structure8View));
			}
			
		}
		
		ItemListDTO dto = new ItemListDTO(viewItems);
		dto.setPaginaton(page);
		return Result.newSuccessResult(dto);
	}
	
	/**
	 * 帮助客户完成
	 * setHelpInfo:
	 * @author: 欧阳利
	 * @param viewItems
	 * @description:
	 */
	private void helpCustomerFinishInfo(List<ViewItem> viewItems){
		Structure6View structure6View = new Structure6View();
		structure6View.setTitle("帮助客户完成");
		structure6View.setTip("您有两种方式为您的客户提供资产配置建议:");
		
		List<String> content = new ArrayList<String>();
		content.add("1、向客户收集投资信息");
		content.add("2、帮客户填写风险偏好测评");
		content.add("3、系统自动生成资产配置建议，你可以添加推荐产品");
		content.add("4、分享配置建议给客户参考");
		structure6View.setContent(content);
		
		List<String> needInfo = new ArrayList<String>();
		needInfo.add("1、年龄范围");
		needInfo.add("2、家庭净资产范围");
		needInfo.add("3、年收入范围");
		needInfo.add("4、过去的投资分布");
		needInfo.add("5、投资期限偏好");
		needInfo.add("6、投资历史经验");
		needInfo.add("7、投资目的");
		needInfo.add("8、风险和收益组合偏好");
		structure6View.setNeedInfo(needInfo);
		viewItems.add(new ViewItem(6, "", structure6View));
	}
	
	
	/**
	 * 分享给客户完成
	 * shareCustomerFinishInfo:
	 * @author: 欧阳利
	 * @param viewItems
	 * @throws BaseException
	 * @description:
	 */
	private void shareCustomerFinishInfo(List<ViewItem> viewItems)throws BaseException{
		Structure7View structure7View = new Structure7View();
		structure7View.setTitle("分享给客户完成");
		List<String> content = new ArrayList<String>();
		content.add("1、将智能资产配置工具分享给客户");
		content.add("2、客户注册登录后，自己完成风险偏好测评");
		content.add("3、系统会将注册的客户自动归属到您名下");
		content.add("4、您可在客户完成记录或客户详情中查看客户测评情况");
		structure7View.setContent(content);
		
		ShareInfoDto shareInfo = new ShareInfoDto();
		shareInfo.setTitle("评估适合您的资产配置方案");
		shareInfo.setImgUrl(ConfigUtils.getValue("user.default.avater.url"));
		shareInfo.setContent("来自"+UserUtils.getCurrentUser().getHeaderRealName()+"的分享，为您评估风险偏好，量身打造资产配置方案");
		shareInfo.setUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/assets/allocation/register?cfp="+userDetailService.getCurrentCardUUID());
		structure7View.setShareInfo(shareInfo);
		viewItems.add(new ViewItem(7, "", structure7View));
	}
	
	
	public Result<AssetPlanDto> addAssetAllocation(@ModelAttribute AddAssetAllocationReqDto reqDto) throws Exception{
    	Result<AssetPlanDto> result = null;
    	if(reqDto.getId() == null){
    		result =  HttpUtils.post(TenantUrl.ASSET_ADD, ArgsUtils.toMap(reqDto),
					new BaseTypeReference<Result<AssetPlanDto>>() {
					});
    	}else{
    		result =  HttpUtils.post(TenantUrl.ASSET_UPDATE, ArgsUtils.toMap(reqDto),
					new BaseTypeReference<Result<AssetPlanDto>>() {
					});
    	}
    	if(result.getSuccess()){
    		AssetPlanDto dto = result.getResult();
    		String resultUrl = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),true);
    		//dto.setResultUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=true");
    		dto.setResultUrl(resultUrl);
    		if(reqDto.getId() == null){
    			dto.setProducIdtList(new ArrayList<Long>());
    		}else{
    			// 查询测评关联的产品id集合
    			Result<List<Long>> productResult = HttpUtils.get(TenantUrl.ASSET_PRODUCT_GET_PRODUCTIDS, ArgsUtils.toIdMap(reqDto.getId()),
    					new BaseTypeReference<Result<List<Long>>>() {
    					});
    			dto.setProducIdtList(productResult.getResult());
    		}
    		// 设置分享地址
    		ShareInfoDto shareInfo = new ShareInfoDto();
    		shareInfo.setContent(UserUtils.getCurrentUser().getHeaderRealName()+"为您评估风险偏好，量身打造适合您的资产配置方案");
    		shareInfo.setTitle(UserUtils.getCurrentUser().getHeaderRealName()+" 为您量身定制的资产配置方案");
    		shareInfo.setImgUrl(ConfigUtils.getValue("user.default.avater.url"));
    		String url = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(), dto.getId(),false);
    		//shareInfo.setUrl(H5Constant.PROTOCOL+shareService.getCCShareDomainUrl()+"/risk/scheme?id="+dto.getId()+"&isEdit=false");
    		shareInfo.setUrl(url);
    		dto.setShareInfo(shareInfo);
    	}
    	
    	return result;
	}
	
}

