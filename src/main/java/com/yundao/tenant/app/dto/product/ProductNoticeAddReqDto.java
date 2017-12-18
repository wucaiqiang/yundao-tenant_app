
package com.yundao.tenant.app.dto.product;

import java.util.Date;

import com.yundao.core.code.config.CommonCode;
import com.yundao.core.validator.group.Update;
import com.yundao.core.validator.number.Number;

import io.swagger.annotations.ApiModelProperty;

public class ProductNoticeAddReqDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "标题")
    private String noticeTitle;

    @ApiModelProperty(value = "内容")
    private String noticeDes;

    @ApiModelProperty(value = "公告类型")
    private Long noticeType;

    @ApiModelProperty(value = "定时发送时间")
    private Long publishDate;

    @ApiModelProperty(value = "是否定时发送（0：不是，1：是）")
    private Integer publishType;

    @ApiModelProperty(value = "附件json串，属性fileName，fileUrls")
    private String attachList;

	public Long getProductId() {
	
		return productId;
	}

	public void setProductId(Long productId) {
	
		this.productId = productId;
	}

	public String getNoticeTitle() {
	
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
	
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeDes() {
	
		return noticeDes;
	}

	public void setNoticeDes(String noticeDes) {
	
		this.noticeDes = noticeDes;
	}

	public Long getNoticeType() {
	
		return noticeType;
	}

	public void setNoticeType(Long noticeType) {
	
		this.noticeType = noticeType;
	}

	public Long getPublishDate() {
	
		return publishDate;
	}

	public void setPublishDate(Long publishDate) {
	
		this.publishDate = publishDate;
	}

	public Integer getPublishType() {
	
		return publishType;
	}

	public void setPublishType(Integer publishType) {
	
		this.publishType = publishType;
	}

	public String getAttachList() {
	
		return attachList;
	}

	public void setAttachList(String attachList) {
	
		this.attachList = attachList;
	}

}
