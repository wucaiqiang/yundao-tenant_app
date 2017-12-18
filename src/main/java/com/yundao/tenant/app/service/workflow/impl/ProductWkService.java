package com.yundao.tenant.app.service.workflow.impl;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.product.ProductPartDetailDto;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.ProductExamineStatusEnum;
import com.yundao.tenant.app.exception.SpringExceptionResolver;
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
@Service("productWkService")
public class ProductWkService implements BaseWkService {

    private static Log log = LogFactory.getLog(ProductWkService.class);

    @Override
    public BaseWkServiceDto getBusiness(Long id) throws BaseException {
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        Result<ProductPartDetailDto> result = HttpUtils.get(TenantUrl.GET_DETAIL_BY_ID, ArgsUtils.toIdMap(id),
                new BaseTypeReference<Result<ProductPartDetailDto>>() {});
        if(!result.getSuccess() || result.getResult() == null){
            throw new BaseException(CodeConstant.CODE_1300023);
        }
        ProductPartDetailDto product = result.getResult();
        //获取产品详情数据
        infoViews.add(new WkBusinessInfoView("产品名称",product.getName()));
        infoViews.add(new WkBusinessInfoView("产品类别",product.getTypeName()));
        infoViews.add(new WkBusinessInfoView("产品等级",product.getLevelText()));
        infoViews.add(new WkBusinessInfoView("风险等级",product.getRiskLevelText()));
        infoViews.add(new WkBusinessInfoView("对接人",product.getReceiverName()));
        infoViews.add(new WkBusinessInfoView("产品助理",product.getAssistantName()));
        String[] params = new String[]{id.toString(),product.getName()};
        BaseWkServiceDto baseWkServiceDto = new BaseWkServiceDto(infoViews,params);
        ProductExamineStatusEnum statusEnum = ProductExamineStatusEnum.getEnum(product.getAuditStatus());
        if(statusEnum == null){
            log.error("#######################productStatus" + product.getAuditStatus());
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
        return HttpUtils.post(TenantUrl.AUDIT_PRODUCT_EXAMINE, params,
                new BaseTypeReference<Result<Boolean>>() {});
    }
}
