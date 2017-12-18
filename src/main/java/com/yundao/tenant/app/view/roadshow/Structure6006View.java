
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
public class Structure6006View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private Date publishDate;

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getPublishDate() {

		if (publishDate != null) {
			return DateFormatUtils.format(publishDate, "yyyy-MM-dd");
		}
		return "";
	}

	public void setPublishDate(Date publishDate) {

		this.publishDate = publishDate;
	}

}
