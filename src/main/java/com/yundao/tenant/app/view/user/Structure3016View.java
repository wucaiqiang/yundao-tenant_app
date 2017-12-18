

package com.yundao.tenant.app.view.user;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午10:36:08 
 * @author   wucq
 * @version   
 */
public class Structure3016View implements DataDTO{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String assetManagerId;
	private String mobile;
	public Structure3016View(){}

	public Structure3016View(String name,String assetManagerId) {
		this.name=name;
		this.assetManagerId=assetManagerId;
	}

	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}

	public String getAssetManagerId() {
	
		return assetManagerId;
	}

	public void setAssetManagerId(String assetManagerId) {
	
		this.assetManagerId = assetManagerId;
	}

	public String getMobile() {
	
		return mobile;
	}

	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}

	

}

