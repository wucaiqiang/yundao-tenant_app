package com.yundao.tenant.app.service.workflow.impl;

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
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.DeclarationStatusEnum;
import com.yundao.tenant.app.enums.workflow.ProductExamineStatusEnum;
import com.yundao.tenant.app.service.workflow.BaseWkService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gjl on 2017/9/22.
 */
@Service("declWkService")
public class DeclWkServiceImpl implements BaseWkService {

    private static Log log = LogFactory.getLog(DeclWkServiceImpl.class);

    @Override
    public BaseWkServiceDto getBusiness(Long id) throws BaseException {
        Result<DeclarationResDto> declarationResDtoResult = HttpUtils.get(TenantUrl.DECLARATION_GET,
                ArgsUtils.toIdMap(id), new BaseTypeReference<Result<DeclarationResDto>>() {
                });
        if (declarationResDtoResult.getResult() == null) {
            throw new BaseException(CodeConstant.CODE_1220016);
        }
        DeclarationResDto declarationResDto = declarationResDtoResult.getResult();
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        infoViews.add(new WkBusinessInfoView("报单编号",declarationResDto.getNumber()));
        infoViews.add(new WkBusinessInfoView("客户名称",declarationResDto.getCustomerName()));
        infoViews.add(new WkBusinessInfoView("产品名称",declarationResDto.getProductName()));
        Double reservationAmount = declarationResDto.getDealAmount();
        String appointAmount = reservationAmount == null ? "0" : reservationAmount.intValue() + "万";
        infoViews.add(new WkBusinessInfoView("认购金额",appointAmount));
        infoViews.add(new WkBusinessInfoView("打款日期", DateUtils.format(declarationResDto.getPayDate(),DateUtils.YYYY_MM_DD)));
        infoViews.add(new WkBusinessInfoView("打款凭证",declarationResDto.getVoucher() == null?"未打款":"已打款"));
        String[] params = new String[]{id.toString()};
        BaseWkServiceDto baseWkServiceDto = new BaseWkServiceDto(infoViews,params);
        DeclarationStatusEnum statusEnum = DeclarationStatusEnum.getEnum(declarationResDto.getStatus());
        if(statusEnum == null){
            log.error("#######################declarationStatus" + declarationResDto.getStatus());
            throw new BaseException(CodeConstant.CODE_1300033);
        }
        baseWkServiceDto.setOperateResult(statusEnum.getShowText()?statusEnum.getName(): null);
        baseWkServiceDto.setHasPassOperate(statusEnum.getHasPassOperate());
        baseWkServiceDto.setHasRejectOperate(statusEnum.getHasRejectOperate());
        return baseWkServiceDto;
    }
    @Override
    public Result<Boolean> audit(WorkFlowAuditReqDto auditReqDto) throws BaseException {
        Map<String,Object> params = new HashMap<>();
        params.put("id",auditReqDto.getId());
        ActionEnum actionEnum = ActionEnum.getEnum(auditReqDto.getOperate());
        params.put("action",actionEnum.getValue());
        params.put("reason",auditReqDto.getReason());
        return HttpUtils.post(TenantUrl.DECLARATION_AUDIT_DO, params,
                new BaseTypeReference<Result<Boolean>>() {});
    }

}
