package com.yundao.tenant.app.dto.common;

public class ShareInfo {
	private String title;
	private String content;
	private String imgUrl;
	private String url;
	
	public ShareInfo(){}
	
	public ShareInfo(String title, String content, String imgUrl, String url) {
		super();
		this.title = title;
		this.content = content;
		this.imgUrl = imgUrl;
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
