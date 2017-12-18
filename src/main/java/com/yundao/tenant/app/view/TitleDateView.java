

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月20日 上午11:26:41 
 * @author   wucq
 * @version   
 */
public class TitleDateView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String date;
	public TitleDateView(){}
	public TitleDateView(String title,String date){
		this.title=title;
		this.date=date;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getDate() {
	
		return date;
	}
	public void setDate(String date) {
	
		this.date = date;
	}
	

}

