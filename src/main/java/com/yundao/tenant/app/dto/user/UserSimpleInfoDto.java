package com.yundao.tenant.app.dto.user;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.yundao.tenant.app.util.PinyinUtils;

/**
 * 用户信息
 *
 * @author jan
 * @create 2017-07-20 PM9:10
 **/
public class UserSimpleInfoDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id
	 */
	private Long id;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 角色名 e.g. 理财师
	 */
	private String roleNames;
	/**
	 * 用户名称
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 所属部门名称
	 */
	private String departmentName;
	/**
	 * 客户姓名的拼音
	 */
	private String pinyin;
	/**
	 * 客户姓名拼音首字母，要大写，如果没有拼音则用“#”
	 */
	private String firstPinyin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public String getRoleNames() {

		return roleNames;
	}

	public void setRoleNames(String roleNames) {

		this.roleNames = roleNames;
	}

	public String getRealName() {

		return realName;
	}

	public void setRealName(String realName) {

		this.realName = realName;
	}

	public String getAvatar() {

		return avatar;
	}

	public void setAvatar(String avatar) {

		this.avatar = avatar;
	}

	public String getDepartmentName() {

		return departmentName;
	}

	public void setDepartmentName(String departmentName) {

		this.departmentName = departmentName;
	}

	public String getPinyin() {
		if (StringUtils.isNotBlank(this.realName)) {
			return PinyinUtils.toPinyin(this.realName);
		}
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getFirstPinyin() {
		if (StringUtils.isNotBlank(this.realName)) {
			return PinyinUtils.getFirstLetter(this.realName);
		}
		return firstPinyin;
	}

	public void setFirstPinyin(String firstPinyin) {

		this.firstPinyin = firstPinyin;
	}

}
