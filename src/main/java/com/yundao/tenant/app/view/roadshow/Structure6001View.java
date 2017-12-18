
package com.yundao.tenant.app.view.roadshow;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure6001View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 路演Id
	 */
	private String roadshowId;
	/**
	 * 封面图url
	 */
	private String imageUrl;
	/**
	 * 视频标题
	 */
	private String title;
	/**
	 * 视频时长：e.g. 21:09
	 */
	private String duration;
	/**
	 * 发布时间 e.g.:2017-08-20
	 */
	private String publishDate;
	/**
	 * 分享格式
	 */
	private ShareInfo shareInfo;
	/**
	 * 关联的产品详情动作
	 */
	private ActionDTO productAction;
	/**
	 * 视频信息
	 */
	private VideoInfoView downloadVideo;
	public String getRoadshowId() {
	
		return roadshowId;
	}
	public void setRoadshowId(String roadshowId) {
	
		this.roadshowId = roadshowId;
	}
	public String getImageUrl() {
	
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
	
		this.imageUrl = imageUrl;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getDuration() {
	
		return duration;
	}
	public void setDuration(String duration) {
	
		this.duration = duration;
	}
	public String getPublishDate() {
	
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
	
		this.publishDate = publishDate;
	}
	public ShareInfo getShareInfo() {
	
		return shareInfo;
	}
	public void setShareInfo(ShareInfo shareInfo) {
	
		this.shareInfo = shareInfo;
	}
	public ActionDTO getProductAction() {
	
		return productAction;
	}
	public void setProductAction(ActionDTO productAction) {
	
		this.productAction = productAction;
	}
	public VideoInfoView getDownloadVideo() {
	
		return downloadVideo;
	}
	public void setDownloadVideo(VideoInfoView downloadVideo) {
	
		this.downloadVideo = downloadVideo;
	}
	
}
