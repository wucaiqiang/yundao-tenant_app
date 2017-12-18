

package com.yundao.tenant.app.view.index;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.TitleDateView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月20日 上午11:25:45 
 * @author   wucq
 * @version   
 */
public class NoticMessageView implements DataDTO{

	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private List<TitleDateView> noticeList;
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public List<TitleDateView> getNoticeList() {
	
		return noticeList;
	}
	public void setNoticeList(List<TitleDateView> noticeList) {
	
		this.noticeList = noticeList;
	}
	

}

