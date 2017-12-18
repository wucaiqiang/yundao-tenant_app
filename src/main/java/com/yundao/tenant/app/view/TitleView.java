
package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年10月23日 上午10:17:54
 * 
 * @author wucq
 * @version
 */
public class TitleView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public TitleView() {
	}

	public TitleView(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

}
