
package com.yundao.tenant.app.view.customer;

import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.workflow.ContentDto;
import com.yundao.tenant.app.util.DateFormatUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure5View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "任务id")
	private String taskId;
	@ApiModelProperty(value = "类型 预约 报单等")
	private String type;

	@ApiModelProperty(value = "对象id")
	private String id;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "内容列表")
	private List<ContentDto> contentList;

	@ApiModelProperty(value = "申请人名称")
	private String applyUserRealName;

	@ApiModelProperty(value = "流程启动时间")
	private Date applyTime;
	
	@ApiModelProperty(value = "审批时间")
	private Date operateTime;

	@ApiModelProperty(value = "e.g. 已通过，已驳回，已取消")
	private String operateResult;
	

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}


	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public List<ContentDto> getContentList() {

		return contentList;
	}

	public void setContentList(List<ContentDto> contentList) {

		this.contentList = contentList;
	}


	public String getTaskId() {

		return taskId;
	}

	public void setTaskId(String taskId) {

		this.taskId = taskId;
	}


	public String getApplyUserRealName() {

		return "申请人："+applyUserRealName;
	}

	public void setApplyUserRealName(String applyUserRealName) {

		this.applyUserRealName = applyUserRealName;
	}

	public String getApplyTime() {
        if(this.applyTime ==null){
        	return "";
        }
        return "申请时间："+DateFormatUtils.format(this.applyTime, "yyyy-MM-dd HH:mm");
	}

	public void setApplyTime(Date applyTime) {

		this.applyTime = applyTime;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getOperateTime() {
	    if(this.operateTime ==null){
	    	return "";
	    }
	    return "审批时间："+DateFormatUtils.format(this.operateTime, "yyyy-MM-dd HH:mm");
	}

	public void setOperateTime(Date operateTime) {
	
		this.operateTime = operateTime;
	}

	public String getOperateResult() {
	
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
	
		this.operateResult = operateResult;
	}



}
