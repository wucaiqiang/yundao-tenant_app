package com.yundao.tenant.app.service.workflow;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.view.workflow.WorkflowDetailView;

/**
 * Created by gjl on 2017/9/22.
 */
public interface WorkFlowService {
    /**
     * 获取单个的详情
     * @param id
     * @param type
     * @param taskId
     * @return
     */
    Result<WorkflowDetailView> get(Long id, String type, String taskId,String taskStatus) throws BaseException;

    /**
     * 审核
     * @param auditReqDto
     * @return
     */
    Result<Boolean> audit(WorkFlowAuditReqDto auditReqDto) throws BaseException;
}
