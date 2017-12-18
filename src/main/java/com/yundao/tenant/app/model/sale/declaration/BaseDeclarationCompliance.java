package com.yundao.tenant.app.model.sale.declaration;

import com.yundao.core.base.model.BaseModel;

import java.io.Serializable;

public class BaseDeclarationCompliance extends BaseModel implements Serializable {
    /**
	 * 报单id
	 */
    private Long declarationId;

    /**
	 * url
	 */
    private String url;

    /**
	 * 文件名
	 */
    private String name;

    /**
	 * 类型
	 */
    private String type;

    /**
	 * 格式；1：图片，2：视频
	 */
    private Integer formatType;

    private Integer sequence;

    private static final long serialVersionUID = 1L;

    public Long getDeclarationId() {
        return declarationId;
    }

    public void setDeclarationId(Long declarationId) {
        this.declarationId = declarationId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFormatType() {
        return formatType;
    }

    public void setFormatType(Integer formatType) {
        this.formatType = formatType;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}