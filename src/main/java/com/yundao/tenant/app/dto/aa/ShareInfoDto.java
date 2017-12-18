

package com.yundao.tenant.app.dto.aa;

import io.swagger.annotations.ApiModelProperty;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月10日 上午9:58:58 
 * @author   欧阳利
 * @version   
 */
public class ShareInfoDto {
    private String title;
    
    private String content;
    @ApiModelProperty("员工头像")
    private String imgUrl;
    
    private String url;

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

	public String getImgUrl() {
	
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
	
		this.imgUrl = imgUrl;
	}

	public String getUrl() {
	
		return url;
	}

	public void setUrl(String url) {
	
		this.url = url;
	}
    
    
}

