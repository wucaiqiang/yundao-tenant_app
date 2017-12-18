
package com.yundao.tenant.app.view.roadshow;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月10日 下午2:46:28
 * 
 * @author wucq
 * @version
 */
public class UrlDefinitionDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * AES加密视频播放地址（MP4格式）
	 */
	private String videoUrl;
	/**
	 * 清晰度 "标清"和"高清"
	 */
	private String definition;

	public UrlDefinitionDto() {
	}

	public UrlDefinitionDto(String videoUrl, String definition) {
		this.videoUrl = videoUrl;
		this.definition = definition;
	}

	public String getVideoUrl() {

		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {

		this.videoUrl = videoUrl;
	}

	public String getDefinition() {

		return definition;
	}

	public void setDefinition(String definition) {

		this.definition = definition;
	}

}
