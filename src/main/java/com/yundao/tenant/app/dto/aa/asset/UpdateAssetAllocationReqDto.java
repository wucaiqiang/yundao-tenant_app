

package com.yundao.tenant.app.dto.aa.asset;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月8日 下午3:33:22 
 * @author   欧阳利
 * @version   
 */
public class UpdateAssetAllocationReqDto {
	@ApiModelProperty("资产配置id")
	private Long id;

	@ApiModelProperty("提醒选项答案 [{ \"questionId(题目id)\":1 ,\"optionIdList(选项ids)\": [1] }]")
	private String questionAndOptionDtos;
	

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getQuestionAndOptionDtos() {
		return questionAndOptionDtos;
	}

	public void setQuestionAndOptionDtos(String questionAndOptionDtos) {
		this.questionAndOptionDtos = questionAndOptionDtos;
	}
}

