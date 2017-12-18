

package com.yundao.tenant.app.view.product;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月8日 上午10:54:05 
 * @author   wucq
 * @version   
 */
public class AttachmentView implements DataDTO{
	
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 附件地址
	 */
private String url;
/**
 * 附件类型
 */
private String fileType;
/**
 * 附件名称
 */
private String sourceName;
public AttachmentView(){}
public AttachmentView(String url,String fileType,String sourceName){
	this.url=url;
	this.fileType=fileType;
	this.sourceName=sourceName;
}
public String getUrl() {

	return url;
}
public void setUrl(String url) {

	this.url = url;
}
public String getFileType() {

	return fileType;
}
public void setFileType(String fileType) {

	this.fileType = fileType;
}
public String getSourceName() {

	return sourceName;
}
public void setSourceName(String sourceName) {

	this.sourceName = sourceName;
}


}

