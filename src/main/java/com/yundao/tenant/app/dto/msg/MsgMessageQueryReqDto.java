
package com.yundao.tenant.app.dto.msg;

import java.util.Date;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: Reason: Date: 2017年9月7日 下午7:47:29
 * 
 * @author wucq
 * @version
 */
public class MsgMessageQueryReqDto extends AbstractBasePageDto {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 默认请求代办，type:pending代办,notice通知
	 */
	@ApiModelProperty(value = "代办:pending,通知:notice")
	private String type;
	@ApiModelProperty(value = "开始时间")
	private Date beginDate;
	@ApiModelProperty(value = "结束时间")
	private Date endDate;

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public Date getBeginDate() {
	
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
	
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
	
		return endDate;
	}

	public void setEndDate(Date endDate) {
	
		this.endDate = endDate;
	}

}
