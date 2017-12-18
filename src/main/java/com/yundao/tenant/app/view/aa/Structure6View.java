
package com.yundao.tenant.app.view.aa;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年11月1日 上午10:43:52
 * 
 * @author 欧阳利
 * @version
 */
public class Structure6View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private String tip;
	
	private String title;
	
	private List<String> content;
	
	private List<String> needInfo;

	public String getTip() {
	
		return tip;
	}

	public void setTip(String tip) {
	
		this.tip = tip;
	}

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

	public List<String> getNeedInfo() {
	
		return needInfo;
	}

	public void setNeedInfo(List<String> needInfo) {
	
		this.needInfo = needInfo;
	}




}
