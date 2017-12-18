
package com.yundao.tenant.app.view.aa;

import com.yundao.tenant.app.dto.DataDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年11月1日 上午10:43:52
 * 
 * @author 欧阳利
 * @version
 */
public class Structure9View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("客户id")
	private Long id;
	
	@ApiModelProperty("客户名称")
	private String name;

	@ApiModelProperty("是否测评")
	private Boolean hasEvaluate;
	
	@ApiModelProperty("测评类型：e.g.保守型")
	private String evaluationTypeText;
	
	@ApiModelProperty("测评日期：e.g. 2017-08-20")
	private String date;

	@ApiModelProperty("data标题")
	private String dateTitle;

	@ApiModelProperty("是否有预约")
	private Boolean hasReservation;
	

	public Boolean getHasReservation() {
	
		return hasReservation;
	}

	public void setHasReservation(Boolean hasReservation) {
	
		this.hasReservation = hasReservation;
	}

	public String getDateTitle() {
	
		return dateTitle;
	}

	public void setDateTitle(String dateTitle) {
	
		this.dateTitle = dateTitle;
	}

	public Boolean getHasEvaluate() {
	
		return hasEvaluate;
	}

	public void setHasEvaluate(Boolean hasEvaluate) {
	
		this.hasEvaluate = hasEvaluate;
	}

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

	public String getEvaluationTypeText() {
	
		return evaluationTypeText;
	}

	public void setEvaluationTypeText(String evaluationTypeText) {
	
		this.evaluationTypeText = evaluationTypeText;
	}

	public String getDate() {
	
		return date;
	}

	public void setDate(String date) {
	
		this.date = date;
	}
	
	
}
