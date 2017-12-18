package com.yundao.tenant.app.dto.sale.leads;


import io.swagger.annotations.ApiModelProperty;

/**
 * 更新线索状态请求dto
 *
 * @author jan
 * @create 2017-09-07 PM7:46
 **/
public class LeadsUpdateStatusReqDto {

    @ApiModelProperty(value = "销售线索id")
    private String lineId;

    @ApiModelProperty(value = "已联系 ：contacted,关闭 ：close")
    private String handleType;
    
    @ApiModelProperty(value = "备注")
    private String reason;

	public String getLineId() {
	
		return lineId;
	}

	public void setLineId(String lineId) {
	
		this.lineId = lineId;
	}

	public String getHandleType() {
	
		return handleType;
	}

	public void setHandleType(String handleType) {
	
		this.handleType = handleType;
	}

	public String getReason() {
	
		return reason;
	}

	public void setReason(String reason) {
	
		this.reason = reason;
	}

}
