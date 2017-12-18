
package com.yundao.tenant.app.view.roadshow;

import java.util.Date;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.util.DateFormatUtils;

/**
 * Function: Reason: Date: 2017年11月10日 下午3:51:04
 * 
 * @author wucq
 * @version
 */
public class Structure6005View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String content;

	public Structure6005View() {
	}

	public Structure6005View(String content) {
		this.content = content;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

}
