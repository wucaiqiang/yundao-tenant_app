

package com.yundao.tenant.app.view;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月20日 下午1:49:30 
 * @author   wucq
 * @version   
 */
public class TitleImageView implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * icon的图片地址
	 */
	private String imageUrl;
	public TitleImageView(){}
	public TitleImageView(String title,String imageUrl){
		this.title=title;
		this.imageUrl=imageUrl;
	}
	public String getTitle() {
	
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getImageUrl() {
	
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
	
		this.imageUrl = imageUrl;
	}
	

}

