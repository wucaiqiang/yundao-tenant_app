package com.yundao.tenant.app.dto.sale.leads;


import io.swagger.annotations.ApiModelProperty;

/**
 * 线索提醒数据返回dto
 *
 * @author jan
 * @create 2017-09-09 PM1:54
 **/
public class LeadsRemindUnAllotResDto {

    @ApiModelProperty(value = "线索id")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "类型文本")
    private String typeText;

    @ApiModelProperty(value = "渠道")
    private Integer channel;

    @ApiModelProperty(value = "渠道文本")
    private String channelText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCreateDate() {
	
		return createDate;
	}

	public void setCreateDate(String createDate) {
	
		this.createDate = createDate;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getChannelText() {
        return channelText;
    }

    public void setChannelText(String channelText) {
        this.channelText = channelText;
    }
}
