
package com.yundao.tenant.app.view.aa;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年11月1日 上午10:43:52
 * 
 * @author 欧阳利
 * @version
 */
public class Structure8View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@ApiModelProperty("客户名称")
	private String name;

	@ApiModelProperty("测评类型：e.g.保守型")
	private String evaluationTypeText;
	
	@ApiModelProperty("推荐的产品数量 e.g. 7个推荐产品")
	private String content;
	
	@ApiModelProperty("测评日期：e.g. 2017-08-20")
	private String date;
	
	@ApiModelProperty("动作")
	private ActionDTO action;

	public ActionDTO getAction() {
	
		return action;
	}

	public void setAction(ActionDTO action) {
	
		this.action = action;
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

	public String getContent() {
	
		return content;
	}

	public void setContent(String content) {
	
		this.content = content;
	}

	public String getDate() {
	
		return date;
	}

	public void setDate(String date) {
	
		this.date = date;
	}
	
	
}
