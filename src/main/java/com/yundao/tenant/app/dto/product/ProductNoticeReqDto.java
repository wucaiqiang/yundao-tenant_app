
package com.yundao.tenant.app.dto.product;

import java.util.Date;

import com.yundao.core.code.config.CommonCode;
import com.yundao.core.validator.group.Update;
import com.yundao.core.validator.number.Number;

import io.swagger.annotations.ApiModelProperty;

public class ProductNoticeReqDto {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "新增不需要传")
    @Number(isBlank = false, message = "{" + CommonCode.COMMON_1079+ "}", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "公告类型")
    private Long noticeTypeId;

    @ApiModelProperty(value = "定时发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "是否定时发送（0：不是，1：是）")
    private Integer isTimingSend;

    @ApiModelProperty(value = "附件json串，属性fileName，fileUrl")
    private String files;

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public Long getProductId (){
        return productId;
    }

    public void setProductId (Long productId){
        this.productId = productId;
    }

    public String getTitle (){
        return title;
    }

    public void setTitle (String title){
        this.title = title;
    }

    public String getContent (){
        return content;
    }

    public void setContent (String content){
        this.content = content;
    }

    public Long getNoticeTypeId (){
        return noticeTypeId;
    }

    public void setNoticeTypeId (Long noticeTypeId){
        this.noticeTypeId = noticeTypeId;
    }


    public Date getSendTime (){
        return sendTime;
    }

    public void setSendTime (Date sendTime){
        this.sendTime = sendTime;
    }


    public Integer getIsTimingSend (){
        return isTimingSend;
    }

    public void setIsTimingSend (Integer isTimingSend){
        this.isTimingSend = isTimingSend;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
