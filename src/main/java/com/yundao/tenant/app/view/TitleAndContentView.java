

package com.yundao.tenant.app.view;

import java.io.Serializable;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月12日 上午11:36:46 
 * @author   wucq
 * @version   
 */

public class TitleAndContentView implements Serializable{
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String content;
	public TitleAndContentView(){}
	public TitleAndContentView(String title,String content){
		this.title=title;
		this.content=content;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}

}

