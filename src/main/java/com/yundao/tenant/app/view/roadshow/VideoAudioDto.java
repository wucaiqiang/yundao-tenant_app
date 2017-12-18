
package com.yundao.tenant.app.view.roadshow;

import java.util.List;

/**
 * Function: Reason: Date: 2017年11月10日 下午2:45:13
 * 
 * @author wucq
 * @version
 */
public class VideoAudioDto implements VideoTypeDto {
	/**
	 * 音频的地址
	 */
	private String audioUrl;
	/**
	 * 图片地址（比例16:9）要配张图片
	 */
	private String coverUrl;

	public String getAudioUrl() {

		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {

		this.audioUrl = audioUrl;
	}

	public String getCoverUrl() {

		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {

		this.coverUrl = coverUrl;
	}

}
