
package com.yundao.tenant.app.view.msg;

import java.util.Date;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.util.DateFormatUtils;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3003View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private Date createTime;
	private String content;
	public String getCreateTime() {
	  if(this.createTime ==null){
		  return "";
	  }
	  return DateFormatUtils.format(this.createTime, "yyyy-MM-dd HH:mm");
	}
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	
}
