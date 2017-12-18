
package com.yundao.tenant.app.view.roadshow;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月10日 上午9:58:23
 * 
 * @author wucq
 * @version
 */
public class VideoInfoView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * AES加密高清视频的地址，默认优先返回高清视频地址，如果没有则返回标清的，或者没有。
	 */
	private String videoUrl;
	/**
	 * 视频的封面图
	 */
	private String coverImageUrl;
	/**
	 * 视频的标题
	 */
	private String title;
	/**
	 * 视频的md5
	 */
	private String md5;
	/**
	 * 视频的大小
	 */
	private long totalSize;

	public String getVideoUrl() {

		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {

		this.videoUrl = videoUrl;
	}

	public String getCoverImageUrl() {

		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {

		this.coverImageUrl = coverImageUrl;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public long getTotalSize() {

		return totalSize;
	}

	public void setTotalSize(long totalSize) {

		this.totalSize = totalSize;
	}

	public String getMd5() {
	
		return md5;
	}

	public void setMd5(String md5) {
	
		this.md5 = md5;
	}

}
