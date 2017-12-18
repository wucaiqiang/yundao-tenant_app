

package com.yundao.tenant.app.service.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationResDto;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.dto.workflow.task.RefundTaskDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.service.workflow.BaseWkService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月16日 下午7:53:21 
 * @author   欧阳利
 * @version   
 */
@Service("refundWkService")
public class RefundWkServiceImpl implements BaseWkService {

	private static Log log = LogFactory.getLog(RefundWkServiceImpl.class);
	 
	@Override
	public BaseWkServiceDto getBusiness(Long id) throws BaseException {
        Result<RefundTaskDto> refundTaskDtoResult = HttpUtils.get(TenantUrl.REFUND_GET_TASK,
                ArgsUtils.toIdMap(id), new BaseTypeReference<Result<RefundTaskDto>>() {
                });
        
        
        if (refundTaskDtoResult.getResult() == null) {
            throw new BaseException(CodeConstant.CODE_1220016);
        }
        RefundTaskDto refundTaskDto = refundTaskDtoResult.getResult();
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        infoViews.add(new WkBusinessInfoView("报单编号",refundTaskDto.getNumber()));
        infoViews.add(new WkBusinessInfoView("客户名称",refundTaskDto.getCustomerName()));
        infoViews.add(new WkBusinessInfoView("产品名称",refundTaskDto.getProductName()));
        Double reservationAmount = refundTaskDto.getDealAmount();
        String appointAmount = reservationAmount == null ? "0" : reservationAmount.intValue() + "万";
        infoViews.add(new WkBusinessInfoView("认购金额",appointAmount));
        infoViews.add(new WkBusinessInfoView("打款日期", DateUtils.format(refundTaskDto.getPayDate(), DateUtils.YYYY_MM_DD)));
        infoViews.add(new WkBusinessInfoView("退款原因",refundTaskDto.getReason()));
        String[] params = new String[]{refundTaskDto.getDeclarationId().toString()};
        BaseWkServiceDto baseWkServiceDto = new BaseWkServiceDto(infoViews,params);
        return baseWkServiceDto;
	}

	@Override
	public Result<Boolean> audit(WorkFlowAuditReqDto auditReqDto) throws BaseException {
        Map<String,Object> params = new HashMap<>();
        params.put("id",auditReqDto.getId());
        ActionEnum actionEnum = ActionEnum.getEnum(auditReqDto.getOperate());
        params.put("action",actionEnum.getValue());
        params.put("reason",auditReqDto.getReason());
        return HttpUtils.post(TenantUrl.REFUND_AUDIT_DO, params,
                new BaseTypeReference<Result<Boolean>>() {});
	}

}

