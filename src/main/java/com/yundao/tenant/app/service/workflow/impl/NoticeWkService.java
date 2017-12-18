package com.yundao.tenant.app.service.workflow.impl;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.log.Slf4jLog;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.product.BaseProductNoticeAttach;
import com.yundao.tenant.app.dto.product.ProductNoticeDto;
import com.yundao.tenant.app.dto.product.ProductPartDetailDto;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;
import com.yundao.tenant.app.dto.workflow.WorkFlowAuditReqDto;
import com.yundao.tenant.app.enums.workflow.ActionEnum;
import com.yundao.tenant.app.enums.workflow.NoticeActionEnum;
import com.yundao.tenant.app.enums.workflow.NoticeExamineStatusEnum;
import com.yundao.tenant.app.enums.workflow.ProductExamineStatusEnum;
import com.yundao.tenant.app.service.workflow.BaseWkService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.FileSupport;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.product.AttachmentView;
import com.yundao.tenant.app.view.workflow.WkBusinessInfoView;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gjl on 2017/9/22.
 */
@Service("noticeWkService")
public class NoticeWkService implements BaseWkService {

    private static Log log = LogFactory.getLog(NoticeWkService.class);

    @Override
    public BaseWkServiceDto getBusiness(Long id) throws BaseException {
        List<WkBusinessInfoView> infoViews = new ArrayList<>();
        Result<ProductNoticeDto> result = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_GET, ArgsUtils.toIdMap(id),
                new BaseTypeReference<Result<ProductNoticeDto>>() {});
        if(!result.getSuccess() || result.getResult() == null){
            throw new BaseException(CodeConstant.CODE_1300023);
        }
        ProductNoticeDto productNotice = result.getResult();
        //获取产品详情数据
        infoViews.add(new WkBusinessInfoView("公告标题",productNotice.getTitle()));
        infoViews.add(new WkBusinessInfoView("产品名称",productNotice.getProductName()));
        infoViews.add(new WkBusinessInfoView("公告类型",productNotice.getNoticeTypeName()));
        infoViews.add(new WkBusinessInfoView("公告摘要",productNotice.getContent()));
        List<BaseProductNoticeAttach> attaches = productNotice.getBaseProductNoticeAttach();
        if(attaches != null)
        for (BaseProductNoticeAttach attach : attaches) {
            AttachmentView attachmentView = new AttachmentView();
            String fileFormat = FileSupport.getFileFormat(attach.getUrl());
            if (StringUtils.isBlank(fileFormat)) {
                log.info("产品附件，ID为：%s，文件格式为：%s,手机端不支持。。。", attach.getId(), fileFormat);
                continue;
            }
            attachmentView.setFileType(fileFormat);
            attachmentView.setSourceName(attach.getSourceName());
            attachmentView.setUrl(attach.getUrl());
            infoViews.add(new WkBusinessInfoView("公告附件",attach.getSourceName(),null,attachmentView));
        }
        String[] params = new String[]{id.toString(),null};
        BaseWkServiceDto baseWkServiceDto = new BaseWkServiceDto(infoViews,params);
        NoticeExamineStatusEnum statusEnum = NoticeExamineStatusEnum.getEnum(productNotice.getStatus());
        if(statusEnum == null){
            log.error("#######################productStatus" + productNotice.getStatus());
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
        NoticeActionEnum actionEnum = NoticeActionEnum.getEnum(auditReqDto.getOperate());
        params.put("status",actionEnum.getValue());
        params.put("reason",auditReqDto.getReason());
        return HttpUtils.post(TenantUrl.NOTICE_EXAMINE, params,
                new BaseTypeReference<Result<Boolean>>() {});
    }
}
