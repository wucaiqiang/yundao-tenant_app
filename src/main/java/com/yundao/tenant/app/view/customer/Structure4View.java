
package com.yundao.tenant.app.view.customer;


import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.workflow.ContentDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure4View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "标题")
	private String title;
	
	@ApiModelProperty(value = "内容列表")
	private List<ContentDto> contentList;
	
	@ApiModelProperty(value = "内容列表")
	private Integer auditStatus;
	
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "申请人Id")
    private Long applyUserId;
    
    @ApiModelProperty(value = "申请人名称")
    private String applyUserRealName;
    
    @ApiModelProperty(value = "流程启动时间")
    private Date applyTime;
    
    @ApiModelProperty(value = "对象id")
    private String id;

    @ApiModelProperty(value = "类型 预约 报单等")
    private String type;

    @ApiModelProperty(value = "是否完成")
    private Integer isComplete;
    
    @ApiModelProperty("是否有取消通过参照")
    private Boolean hasCancelOperate = false;
    
    @ApiModelProperty("是否有驳回通过参照")
    private Boolean hasRejectOperate = false;
    
    @ApiModelProperty("是否有审批通过参照")
    private Boolean hasPassOperate = false;

    @ApiModelProperty("是否有否决参照")
    private Boolean hasVetoOperate = false;

	public Boolean getHasVetoOperate() {
	
		return hasVetoOperate;
	}

	public void setHasVetoOperate(Boolean hasVetoOperate) {
	
		this.hasVetoOperate = hasVetoOperate;
	}

	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}

	public Boolean getHasCancelOperate() {
	
		return hasCancelOperate;
	}

	public void setHasCancelOperate(Boolean hasCancelOperate) {
	
		this.hasCancelOperate = hasCancelOperate;
	}

	public Boolean getHasRejectOperate() {
	
		return hasRejectOperate;
	}

	public void setHasRejectOperate(Boolean hasRejectOperate) {
	
		this.hasRejectOperate = hasRejectOperate;
	}

	public Boolean getHasPassOperate() {
	
		return hasPassOperate;
	}

	public void setHasPassOperate(Boolean hasPassOperate) {
	
		this.hasPassOperate = hasPassOperate;
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
	

}
