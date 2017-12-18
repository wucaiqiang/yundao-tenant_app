package com.yundao.tenant.app.service.workflow.impl;

import com.alibaba.fastjson.TypeReference;
import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.sale.reservation.ReservationDetailDto;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.ReservationStatusEnum;
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
@Service("reserWkService")
public class ReserWkServiceImpl implements BaseWkService {
    private static Log log = LogFactory.getLog(ReserWkServiceImpl.class);
    @Override
    public BaseWkServiceDto getBusiness(Long id) throws BaseException {
        Result<ReservationDetailDto> result = HttpUtils.getFastJson(TenantUrl.RESERVATION_SINGLE_DETAIL,
                ArgsUtils.toIdMap(id), new TypeReference<Result<ReservationDetailDto>>() {
                });
        if(!result.getSuccess() || result.getResult() == null){
            throw new BaseException(CodeConstant.CODE_1300030);
        }
        ReservationDetailDto detailDto = result.getResult();
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        infoViews.add(new WkBusinessInfoView("预约编号",detailDto.getNumber()));
        infoViews.add(new WkBusinessInfoView("客户名称",detailDto.getCustomerName()));
        infoViews.add(new WkBusinessInfoView("产品名称",detailDto.getProductName()));
        Double reservationAmount = detailDto.getReservationAmount();
        String appointAmount = reservationAmount == null ? "0" : reservationAmount.intValue() + "万";
        infoViews.add(new WkBusinessInfoView("预约金额",appointAmount));
        infoViews.add(new WkBusinessInfoView("预计打款日期", DateUtils.format(detailDto.getEstimatePayDate(),DateUtils.YYYY_MM_DD)));
        String[] params = new String[]{id.toString()};
        BaseWkServiceDto baseWkServiceDto = new BaseWkServiceDto(infoViews,params);
        ReservationStatusEnum statusEnum = ReservationStatusEnum.getEnum(detailDto.getStatus());
        if(statusEnum == null){
            log.error("#######################reservationStatus" + detailDto.getStatus());
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
        return HttpUtils.post(TenantUrl.RESERVATION_AUDIT_DO, params,
                new BaseTypeReference<Result<Boolean>>() {});
    }
}
