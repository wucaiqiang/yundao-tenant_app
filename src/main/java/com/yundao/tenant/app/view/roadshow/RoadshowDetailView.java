
package com.yundao.tenant.app.view.roadshow;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.common.ViewItem;

/**
 * Function: Reason: Date: 2017年11月10日 下午2:38:11
 * 
 * @author wucq
 * @version
 */
public class RoadshowDetailView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 路演ID
	 */
	private String roadshowId;
	/**
	 * 内容类型 VIDEO:视频, ONLY_PDF:只有PDF, ONLY_AUDIO:只有音频， PDF_AUDIO：PDF+音频
	 */
	private String contentType;
	/**
	 * VideoContentDto:视频 VideoAudioDto:只有音频 VideoPDFDto:只有PDF
	 * VideoAudioPDFDto:PDF+音频
	 */
	private VideoTypeDto content;
	/**
	 * 视频信息
	 */
	private VideoInfoView downloadVideo;
	private ShareInfo shareInfo;
	private List<ViewItem> viewItems;

	public String getRoadshowId() {

		return roadshowId;
	}

	public void setRoadshowId(String roadshowId) {

		this.roadshowId = roadshowId;
	}

	public String getContentType() {

		return contentType;
	}

	public void setContentType(String contentType) {

		this.contentType = contentType;
	}

	public VideoTypeDto getContent() {

		return content;
	}

	public void setContent(VideoTypeDto content) {

		this.content = content;
	}

	public VideoInfoView getDownloadVideo() {

		return downloadVideo;
	}

	public void setDownloadVideo(VideoInfoView downloadVideo) {

		this.downloadVideo = downloadVideo;
	}

	public ShareInfo getShareInfo() {

		return shareInfo;
	}

	public void setShareInfo(ShareInfo shareInfo) {

		this.shareInfo = shareInfo;
	}

	public List<ViewItem> getViewItems() {

		return viewItems;
	}

	public void setViewItems(List<ViewItem> viewItems) {

		this.viewItems = viewItems;
	}

}
