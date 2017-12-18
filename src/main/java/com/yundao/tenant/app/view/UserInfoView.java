
package com.yundao.tenant.app.view;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.dto.login.UserAppLoginResDto;

/**
 * Function: Reason: Date: 2017年8月1日 上午9:52:16
 * 
 * @author wucq
 * @version
 */
public class UserInfoView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否是原始密码
	 */
	private Boolean isOriginalPass;
	private List<UserAppLoginResDto> userInfoList;

	public UserInfoView() {
	}

	public UserInfoView(Boolean isOriginalPass,List<UserAppLoginResDto> userInfoList) {
		this.isOriginalPass=isOriginalPass;
		this.userInfoList = userInfoList;
	}

	public List<UserAppLoginResDto> getUserInfoList() {

		return userInfoList;
	}

	public void setUserInfoList(List<UserAppLoginResDto> userInfoList) {

		this.userInfoList = userInfoList;
	}

	public Boolean getIsOriginalPass() {
	
		return isOriginalPass;
	}

	public void setIsOriginalPass(Boolean isOriginalPass) {
	
		this.isOriginalPass = isOriginalPass;
	}
	

}
