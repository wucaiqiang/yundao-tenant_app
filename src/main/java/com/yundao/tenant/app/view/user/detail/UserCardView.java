
package com.yundao.tenant.app.view.user.detail;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.dto.common.ShareInfo;

/**
 * Function: Reason: Date: 2017年9月21日 下午3:05:52
 * 
 * @author wucq
 * @version
 */
public class UserCardView implements Serializable {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String company;
	private String des;
	private String headerBit;
	private String name;
	private String avatar;
	private String leftBtnText;
	private String rightBtnText;
	private String mobile;
	private String departmentName;
	private List<String> roleList;
	private ShareInfo shareInfo;
	private String email;
	private String address;

	public String getCompany() {

		return company;
	}

	public void setCompany(String company) {

		this.company = company;
	}

	public String getDes() {

		return des;
	}

	public void setDes(String des) {

		this.des = des;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getAvatar() {

		return avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public String getLeftBtnText() {

		return leftBtnText;
	}

	public void setLeftBtnText(String leftBtnText) {

		this.leftBtnText = leftBtnText;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public ShareInfo getShareInfo() {

		return shareInfo;
	}

	public void setShareInfo(ShareInfo shareInfo) {

		this.shareInfo = shareInfo;
	}

	public String getRightBtnText() {

		return rightBtnText;
	}

	public void setRightBtnText(String rightBtnText) {

		this.rightBtnText = rightBtnText;
	}

	public List<String> getRoleList() {

		return roleList;
	}

	public void setRoleList(List<String> roleList) {

		this.roleList = roleList;
	}

	public String getDepartmentName() {

		return departmentName;
	}

	public void setDepartmentName(String departmentName) {

		this.departmentName = departmentName;
	}

	public String getHeaderBit() {
	
		return headerBit;
	}

	public void setHeaderBit(String headerBit) {
	
		this.headerBit = headerBit;
	}

	public String getEmail() {
	
		return email;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}

	public String getAddress() {
	
		return address;
	}

	public void setAddress(String address) {
	
		this.address = address;
	}
	

}
