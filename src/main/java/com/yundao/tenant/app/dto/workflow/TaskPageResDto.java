package com.yundao.tenant.app.dto.workflow;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户池分页数据
 *
 * @author jan
 * @create 2017-08-14 AM8:56
 **/
public class TaskPageResDto {

	@ApiModelProperty(value = "标题")
	private String title;
	
	@ApiModelProperty(value = "内容列表")
	private List<ContentDto> contentList;
	
	@ApiModelProperty(value = "流程最新状态")
	private Integer auditStatus;
	@ApiModelProperty(value = "任务审核动作")
	private Integer actionValue;
	
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "申请人Id")
    private Long applyUserId;
    
    @ApiModelProperty(value = "申请人名称")
    private String applyUserRealName;
    
    @ApiModelProperty(value = "申请人名称")
    private Date applyTime;
    @ApiModelProperty(value = "任务审批时间")
    private Date endTime;
    
    @ApiModelProperty(value = "对象id")
    private Long objectId;

    @ApiModelProperty(value = "类型 预约 报单等")
    private String type;

    @ApiModelProperty(value = "是否完成")
    private Integer isComplete;
    
    private String operateResult;
    

    @ApiModelProperty(value = "应许操作值")
    private List<ActionDto> actionDtos;

	public List<ActionDto> getActionDtos() {
	
		return actionDtos;
	}

	public void setActionDtos(List<ActionDto> actionDtos) {
	
		this.actionDtos = actionDtos;
	}

	public String getOperateResult() {
	
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
	
		this.operateResult = operateResult;
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

	public Integer getAuditStatus() {
	
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
	
		this.auditStatus = auditStatus;
	}

	public String getTaskId() {
	
		return taskId;
	}

	public void setTaskId(String taskId) {
	
		this.taskId = taskId;
	}

	public Long getApplyUserId() {
	
		return applyUserId;
	}

	public void setApplyUserId(Long applyUserId) {
	
		this.applyUserId = applyUserId;
	}


	public String getApplyUserRealName() {
	
		return applyUserRealName;
	}

	public void setApplyUserRealName(String applyUserRealName) {
	
		this.applyUserRealName = applyUserRealName;
	}

	public Date getApplyTime() {
	
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
	
		this.applyTime = applyTime;
	}

	public Long getObjectId() {
	
		return objectId;
	}

	public void setObjectId(Long objectId) {
	
		this.objectId = objectId;
	}

	public String getType() {
	
		return type;
	}

	public void setType(String type) {
	
		this.type = type;
	}

	public Integer getIsComplete() {
	
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
	
		this.isComplete = isComplete;
	}

	public Date getEndTime() {
	
		return endTime;
	}

	public void setEndTime(Date endTime) {
	
		this.endTime = endTime;
	}

	public Integer getActionValue() {
	
		return actionValue;
	}

	public void setActionValue(Integer actionValue) {
	
		this.actionValue = actionValue;
	}

}
