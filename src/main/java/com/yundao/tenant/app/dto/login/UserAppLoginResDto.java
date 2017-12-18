package com.yundao.tenant.app.dto.login;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询用户详情
 * 
 * @author 欧阳利 2017年6月22日
 */
public class UserAppLoginResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户的ticket，用来做登录态的。
	 */
	private String ticket;
	/**
	 * 公司名
	 */
	private String companyName;
	/**
	 * 租户的编号Code 后面的请求都要带上这个Code
	 */
	private String tenantCode;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 这个人的真姓名
	 */
	private String name;

	/**
	 * 这个人的头像
	 */
	private String avatar;

	/**
	 * 工作角色集合。 eg“理财师 产品经理”
	 */
	private List<String> roleList;
	/**
	 * 是否能进入App
	 */
	private Boolean enableEnter;
	/**
	 * 不能进入APP的时候，要弹提示语
	 */
	private String tipText;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 名片简介
	 */
	private String cardIntroduce;
	/**
	 * 名片头衔
	 */
	private String cardHeaderBit;
	/**
	 * 邮箱	
	 */
	private String email;
	/**
	 * 地址
	 */
	private String address;

	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	public String getTicket() {

		return ticket;
	}

	public void setTicket(String ticket) {

		this.ticket = ticket;
	}

	public String getCompanyName() {

		return companyName;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public String getTenantCode() {

		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {

		this.tenantCode = tenantCode;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
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

	public List<String> getRoleList() {

		return roleList;
	}

	public void setRoleList(List<String> roleList) {

		this.roleList = roleList;
	}

	public Boolean getEnableEnter() {
	
		return enableEnter;
	}

	public void setEnableEnter(Boolean enableEnter) {
	
		this.enableEnter = enableEnter;
	}

	public String getTipText() {
	
		return tipText;
	}

	public void setTipText(String tipText) {
	
		this.tipText = tipText;
	}

	public String getDepartment() {
	
		return department;
	}

	public void setDepartment(String department) {
	
		this.department = department;
	}

	public String getCardIntroduce() {
	
		return cardIntroduce;
	}

	public void setCardIntroduce(String cardIntroduce) {
	
		this.cardIntroduce = cardIntroduce;
	}

	public String getCardHeaderBit() {
	
		return cardHeaderBit;
	}

	public void setCardHeaderBit(String cardHeaderBit) {
	
		this.cardHeaderBit = cardHeaderBit;
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

