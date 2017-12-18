
package com.yundao.tenant.app.view.aa;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.aa.ShareInfoDto;

/**
 * Function: Reason: Date: 2017年11月1日 上午10:43:52
 * 
 * @author 欧阳利
 * @version
 */
public class Structure7View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private List<String> content;
	
	private ShareInfoDto shareInfo;

	public String getTitle() {
	
		return title;
	}

	public void setTitle(String title) {
	
		this.title = title;
	}

	public List<String> getContent() {
	
		return content;
	}

	public void setContent(List<String> content) {
	
		this.content = content;
	}

	public ShareInfoDto getShareInfo() {
	
		return shareInfo;
	}

	public void setShareInfo(ShareInfoDto shareInfo) {
	
		this.shareInfo = shareInfo;
	}





}
