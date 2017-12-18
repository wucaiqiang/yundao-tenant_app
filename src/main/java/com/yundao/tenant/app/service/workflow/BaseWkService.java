package com.yundao.tenant.app.service.workflow;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;

import java.util.List;

/**
 * Created by gjl on 2017/9/22.
 */
public interface BaseWkService {
    /**
     * 获取对应业务的试图
     * @param id
     * @return
     * @throws BaseException
     */
    BaseWkServiceDto getBusiness(Long id) throws BaseException;

    /**
     * 对应业务的审核
     * @param auditReqDto
     * @return
     */
    Result<Boolean> audit(WorkFlowAuditReqDto auditReqDto) throws BaseException;
}
