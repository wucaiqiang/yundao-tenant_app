
package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年9月5日 下午12:09:25
 * 
 * @author wucq
 * @version
 */
public class VideoView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 视频文件
	 */
	private String videoUrl;
	/**
	 * 封面
	 */
	private String coverUrl;
	public String getVideoUrl() {
	
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
	
		this.videoUrl = videoUrl;
	}
	public String getCoverUrl() {
	
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
	
		this.coverUrl = coverUrl;
	}
	
}
