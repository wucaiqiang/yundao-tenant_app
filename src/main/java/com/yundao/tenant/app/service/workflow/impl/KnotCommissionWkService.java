

package com.yundao.tenant.app.service.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.dto.workflow.task.KnotCommissionTaskDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.service.workflow.BaseWkService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月16日 下午7:54:12 
 * @author   欧阳利
 * @version   
 */
@Service("knotCommissionWkService")
public class KnotCommissionWkService implements BaseWkService {

	@Override
	public BaseWkServiceDto getBusiness(Long id) throws BaseException {
		 Result<KnotCommissionTaskDto> knotCommissionTaskDtoResult = HttpUtils.get(TenantUrl.KNOT_COMMISSION_GET_TASK_DTO,
	                ArgsUtils.toIdMap(id), new BaseTypeReference<Result<KnotCommissionTaskDto>>() {
	                });
		 if (knotCommissionTaskDtoResult.getResult() == null) {
	            throw new BaseException(CodeConstant.CODE_1220016);
	     }
		 KnotCommissionTaskDto dto = knotCommissionTaskDtoResult.getResult();
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        infoViews.add(new WkBusinessInfoView("报单编号",dto.getNumber()));
        infoViews.add(new WkBusinessInfoView("理财师",dto.getUsername()));
        infoViews.add(new WkBusinessInfoView("产品名称",dto.getProductName()));
        Double reservationAmount = dto.getDealAmount();
        String appointAmount = reservationAmount == null ? "0" : reservationAmount.intValue() + "万";
        infoViews.add(new WkBusinessInfoView("认购金额",appointAmount));
        infoViews.add(new WkBusinessInfoView("佣金类型", dto.getTypeText()));
        infoViews.add(new WkBusinessInfoView("佣金费率",dto.getRate()+""));
        infoViews.add(new WkBusinessInfoView("发放金额",dto.getAmount()+""));
        String[] params = new String[]{dto.getDeclarationId().toString()};
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
        return HttpUtils.post(TenantUrl.KNOT_COMMISSION_AUDIT_DO, params,
                new BaseTypeReference<Result<Boolean>>() {});

	}

}
