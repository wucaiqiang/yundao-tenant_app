
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure1016View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * “内部可见” or “外部可见”
	 */
	private String title;
	/**
	 * 可见性 inside：内部可见 outside：外部可见
	 */
	private String visiable;

	public Structure1016View() {
	}

	public Structure1016View(String title, String visiable) {
		this.title = title;
		this.visiable = visiable;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getVisiable() {

		return visiable;
	}

	public void setVisiable(String visiable) {

		this.visiable = visiable;
	}

}
