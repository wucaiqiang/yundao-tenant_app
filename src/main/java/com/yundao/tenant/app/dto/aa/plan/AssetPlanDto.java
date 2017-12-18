

package com.yundao.tenant.app.dto.aa.plan;

import java.util.List;

import com.yundao.tenant.app.dto.aa.ShareInfoDto;

import io.swagger.annotations.ApiParam;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 下午2:03:06 
 * @author   欧阳利
 * @version   
 */
public class AssetPlanDto {
   @ApiParam("测评id")
   private Long id;
   @ApiParam(" 帮客户测评结果的url地址")
   private String resultUrl;
   @ApiParam("关联超id集合")
   private List<Long> producIdtList;
   @ApiParam("分享")
   private ShareInfoDto shareInfo;
   
//   
//   @ApiParam("理财师id")
//   private Long userId;
//   @ApiParam("理财师名称")
//   private String username;
//   @ApiParam("理财师手机号码")
//   private String userMobile;
//   @ApiParam("客户id")
//   private Long customerId;
//
//   @ApiModelProperty("饼图")
//   private List<PieDto> pie;
//   
//   @ApiParam("客户名称")
//   private String customerName;
//   
//   @ApiModelProperty("评语")
//   private String comment;
//   @ApiModelProperty("组合收益")
//   private Double portfolioIncome;
//   @ApiModelProperty("组合风险")
//   private Double portfolioRisk;
//   @ApiModelProperty("风险测评类型值")
//   private Integer riskValue;
//   @ApiModelProperty("风险测评类型名称")
//   private String riskText;
   



	public String getResultUrl() {

	return resultUrl;
}

public ShareInfoDto getShareInfo() {
	
		return shareInfo;
	}

	public void setShareInfo(ShareInfoDto shareInfo) {
	
		this.shareInfo = shareInfo;
	}

public List<Long> getProducIdtList() {
	
		return producIdtList;
	}

	public void setProducIdtList(List<Long> producIdtList) {
	
		this.producIdtList = producIdtList;
	}

public void setResultUrl(String resultUrl) {

	this.resultUrl = resultUrl;
}

public Long getId() {

	return id;
}

public void setId(Long id) {

	this.id = id;
}



}

