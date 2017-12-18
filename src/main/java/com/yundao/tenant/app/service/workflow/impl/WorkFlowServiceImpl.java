package com.yundao.tenant.app.service.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.listener.SystemListener;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.TextConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.workflow.AuditDetailResDTO;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.TaskActionResDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.ProcessDefineKeyEnum;
import com.yundao.tenant.app.service.workflow.BaseWkService;
import com.yundao.tenant.app.service.workflow.WorkFlowService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;
import com.yundao.tenant.app.view.workflow.WkHistoryView;
import com.yundao.tenant.app.view.workflow.WorkflowDetailView;

/**
 * Created by gjl on 2017/9/22.
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService{
    @Override
    public Result<WorkflowDetailView> get(Long id, String type, String taskId,String taskStatus) throws BaseException {
        ProcessDefineKeyEnum processDefineKeyEnum = ProcessDefineKeyEnum.getKey(type);
        if(processDefineKeyEnum == null){
            throw new BaseException(CodeConstant.CODE_1300028);
        }
        BaseWkService wkService = SystemListener.getBean(processDefineKeyEnum.getServiceName(),BaseWkService.class);
        if(wkService == null){
            throw new BaseException(CodeConstant.CODE_1300029);
        }
        BaseWkServiceDto wkServiceBusiness = wkService.getBusiness(id);
        //获取审核列表
        String title = processDefineKeyEnum.getName();
        ActionDTO actionDTO = null;
        if(!BooleanUtils.isBlank(processDefineKeyEnum.getActionUrl())){
            String actionUrl = String.format(processDefineKeyEnum.getActionUrl(),wkServiceBusiness.getParams());
            actionDTO = new ActionDTO(actionUrl);
        }

        //审批记录
        Map<String,Object> params = new HashMap<>();
        params.put("taskId",taskId);
        Result<List<AuditDetailResDTO>> auditDetailResDTOS = HttpUtils.get(TenantUrl.GET_TASK_HISTORY, params,new BaseTypeReference<Result<List<AuditDetailResDTO>>>() {});
        List<WkHistoryView> historyViews = chanageView(auditDetailResDTOS);
        //审批人和审批时间
        Result<TaskActionResDto> taskActionResDtoResult = HttpUtils.get(TenantUrl.GET_ACTION_AND_APPLY_INFO, params,new BaseTypeReference<Result<TaskActionResDto>>() {});
        if(taskActionResDtoResult.getResult() == null){
            throw new BaseException(CodeConstant.CODE_1300031);
        } 
        TaskActionResDto task = taskActionResDtoResult.getResult();
        String submitter = String.format(TextConstant.APPLY_NAME,task.getApplyUserRealName());
        String submitTime = DateUtils.format(task.getApplyTime(),DateUtils.YYYY_MM_DD_HH_MM);
        if(StringUtils.isNotBlank(taskStatus) && "DONE".equals(taskStatus)){
        	//我参与的已办任务，设备审批操作为false，不显示
        	wkServiceBusiness.setHasPassOperate(false);
        	wkServiceBusiness.setHasRejectOperate(false);
        	wkServiceBusiness.setHasVetoOperate(false);
        }
        if(StringUtils.isNotBlank(taskStatus) && "TODO".equals(taskStatus)){
        	setAction( task.getActions(), wkServiceBusiness);
        }

        //设置只显示一个"公告附件"
        List<WkBusinessInfoView> businessInfoViews = wkServiceBusiness.getBusinessInfoViews();
        if(businessInfoViews != null){
            boolean canRemoveNoticeAttachTitle = false;
            for(int i = 0 ;i<businessInfoViews.size();i++){
                WkBusinessInfoView bv = businessInfoViews.get(i);
                String name = bv.getName();
                if(!canRemoveNoticeAttachTitle){
                    if("公告附件".equals(name)){
                        canRemoveNoticeAttachTitle = true;
                    }
                }else{
                    if("公告附件".equals(name)){
                        bv.setName("     ");
                    }else{
                        canRemoveNoticeAttachTitle = false;
                    }
                }
            }
        }


        WorkflowDetailView detailView = new WorkflowDetailView(title,submitter,submitTime,actionDTO,wkServiceBusiness.getBusinessInfoViews(),historyViews,wkServiceBusiness);
        return Result.newSuccessResult(detailView);
    }
    
	
	/**
	 * 设置操作
	 * setAction:
	 * @author: 欧阳利
	 * @param actionDtos
	 * @param view4
	 * @description:
	 */
	private void setAction(List<Integer> actionList,BaseWkServiceDto wkServiceBusiness){
		if(BooleanUtils.isEmpty(actionList)){
			return;
		}
    	wkServiceBusiness.setHasPassOperate(false);
    	wkServiceBusiness.setHasRejectOperate(false);
    	wkServiceBusiness.setHasVetoOperate(false);
		for(Integer actionValue : actionList){
			if(actionValue.equals(ActionEnum.NO_PASS.getValue())){
				wkServiceBusiness.setHasVetoOperate(true);
			}
			if(actionValue.equals(ActionEnum.REJECT.getValue())){
				wkServiceBusiness.setHasRejectOperate(true);
			}
			if(actionValue.equals(ActionEnum.PASS.getValue())){
				wkServiceBusiness.setHasPassOperate(true);
			}
		}
	}
    

    @Override
    public Result<Boolean> audit(WorkFlowAuditReqDto auditReqDto) throws BaseException {
        if(auditReqDto.getId() == null){
            throw new BaseException(CodeConstant.CODE_1200000);
        }
        ProcessDefineKeyEnum processDefineKeyEnum = ProcessDefineKeyEnum.getKey(auditReqDto.getType());
        if(processDefineKeyEnum == null){
            throw new BaseException(CodeConstant.CODE_1300028);
        }
        BaseWkService wkService = SystemListener.getBean(processDefineKeyEnum.getServiceName(),BaseWkService.class);
        if(wkService == null){
            throw new BaseException(CodeConstant.CODE_1300029);
        }
        if(BooleanUtils.isEmpty(auditReqDto.getOperate())){
            throw new BaseException(CodeConstant.CODE_1300032);
        }
        return wkService.audit(auditReqDto);
    }

    private List<WkHistoryView> chanageView(Result<List<AuditDetailResDTO>> auditDetailResDTOS) {
        List<AuditDetailResDTO> audits = auditDetailResDTOS.getResult();
        if(audits == null || audits.isEmpty()){
            return null;
        }
        List<WkHistoryView> result = new ArrayList<>(audits.size());
        for (AuditDetailResDTO audit : audits) {
            String endTime = audit.getEndTime() != null ? com.yundao.core.utils.DateUtils.format(audit.getEndTime(), com.yundao.core.utils.DateUtils.YYYY_MM_DD_HH_MM_SS) : null;
            String reason = BooleanUtils.isEmpty(audit.getComment()) ? null : String.format(TextConstant.REASON_NAME,audit.getComment());
            String operatorName = BooleanUtils.isEmpty(audit.getActionUserName()) ? null : String.format(TextConstant.OPERATING_NAME,audit.getActionUserName());
            String resultText = BooleanUtils.isEmpty(audit.getActionText()) ? null : String.format(TextConstant.RESULT_NAME,audit.getActionText());
            result.add(new WkHistoryView(endTime,audit.getName(),resultText,operatorName,reason));
        }
        return result;
    }
}
