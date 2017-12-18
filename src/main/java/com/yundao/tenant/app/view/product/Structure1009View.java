
package com.yundao.tenant.app.view.product;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure1009View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String fileUrl;
	private String fileType;
	private String visiable;
	private ShareInfo shareInfo;
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getFileUrl() {
	
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
	
		this.fileUrl = fileUrl;
	}
	public String getFileType() {
	
		return fileType;
	}
	public void setFileType(String fileType) {
	
		this.fileType = fileType;
	}
	public String getVisiable() {
	
		return visiable;
	}
	public void setVisiable(String visiable) {
	
		this.visiable = visiable;
	}
	public ShareInfo getShareInfo() {
	
		return shareInfo;
	}
	public void setShareInfo(ShareInfo shareInfo) {
	
		this.shareInfo = shareInfo;
	}
	
}
