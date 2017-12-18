
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure1005View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private boolean scrollMark;

	public Structure1005View() {
	}

	public Structure1005View(String title, boolean scrollMark) {
		this.title=title;
		this.scrollMark=scrollMark;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public boolean isScrollMark() {

		return scrollMark;
	}

	public void setScrollMark(boolean scrollMark) {

		this.scrollMark = scrollMark;
	}

}
