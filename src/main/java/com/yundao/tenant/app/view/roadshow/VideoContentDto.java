
package com.yundao.tenant.app.view.roadshow;

import java.util.List;

/**
 * Function: Reason: Date: 2017年11月10日 下午2:45:13
 * 
 * @author wucq
 * @version
 */
public class VideoContentDto implements VideoTypeDto {
	/**
	 * 视频的对象
	 */
	private List<UrlDefinitionDto> videoList;

	public List<UrlDefinitionDto> getVideoList() {

		return videoList;
	}

	public void setVideoList(List<UrlDefinitionDto> videoList) {

		this.videoList = videoList;
	}

}
