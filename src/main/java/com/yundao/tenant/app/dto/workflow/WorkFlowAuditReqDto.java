package com.yundao.tenant.app.dto.workflow;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gjl on 2017/9/24.
 */
public class WorkFlowAuditReqDto {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "驳回/取消原因")
    private String reason;

    @ApiModelProperty(value = "操作类型：CANCEL:取消 REJECT:驳回 PASS:通过")
    private  String operate;

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
