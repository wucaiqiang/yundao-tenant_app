
package com.yundao.tenant.app.view.roadshow;

/**
 * Function: Reason: Date: 2017年11月10日 下午2:45:13
 * 
 * @author wucq
 * @version
 */
public class VideoAudioPDFDto implements VideoTypeDto {
	/**
	 * PDF下载地址
	 */
	private String pdfUrl;
	/**
	 * 音频的地址
	 */
	private String audioUrl;
	public String getPdfUrl() {
	
		return pdfUrl;
	}
	public void setPdfUrl(String pdfUrl) {
	
		this.pdfUrl = pdfUrl;
	}
	public String getAudioUrl() {
	
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
	
		this.audioUrl = audioUrl;
	}

}
