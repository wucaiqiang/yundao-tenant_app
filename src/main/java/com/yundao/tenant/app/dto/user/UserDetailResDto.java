package com.yundao.tenant.app.dto.user;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询用户详情
 * 
 * @author 欧阳利
 * 2017年6月22日
 */
public class UserDetailResDto {
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long id;
	
	/**
	 * 部门id
	 */
	@ApiModelProperty(value = "部门id")
	private Long departmentId;
	
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称")
	private String departmentName;
	
    /**
	 * 登录用户名
	 */
	@ApiModelProperty(value = "登录用户名")
    private String username;
    
    /**
     * 真实姓名
     */
	@ApiModelProperty(value = "真实姓名")
    private String realName;

    /**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
    private String mobile;

    /**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
    private String email;

	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日")
    private Date birth;
	
    /**
     * 性别，0：未知；1：男；2：女
     */
	@ApiModelProperty(value = " 性别，0：未知；1：男；2：女")
	private Integer sex;
	
	/**
	 * 注册来源
	 */
	@ApiModelProperty(value = "注册来源")
	private String registerSource;
	
	/**
	 * 工号
	 */
	@ApiModelProperty(value = "工号")
	private String jobNumber;
	
	/**
	 * 是否启用
	 */
	@ApiModelProperty(value = "是否启用   0：未启用；1：启用")
	private Integer isEnabled;
	
	/**
	 * 是否锁定
	 */
	@ApiModelProperty(value = "是否锁定   0：未锁定；1：锁定；")
	private Integer isLock;
	
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickName;
	
	/**
	 * 租户
	 */
	@ApiModelProperty(value = "租户id")
	private Long tenantId;
	
	/**
	 * 租户名称
	 */
	@ApiModelProperty(value = " 租户名称")
	private String tenantName;
	
	/**
	 * 是否离职
	 */
	@ApiModelProperty(value = "是否离职  0：未离职；1：离职；")
	private Integer isDimission;
	
	/**
	 * 领导id
	 */
	@ApiModelProperty(value = "领导id")
	private Long leaderId;
	
	/**
	 * 领导名称
	 */
	@ApiModelProperty(value = "领导名称")
	private String leaderName;
	
	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "用户id集合")
	private List<Long> roleIds;
	/**
	 * 角色中文名
	 */
	private List<String> roleTexts;

	private String ticket;
	/**
	 * 名片简介
	 */
	private String cardIntroduce;
	/**
	 * 名片头衔
	 */
	private String cardHeaderBit;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 头像
	 */
	private String avatar;
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getIsDimission() {
		return isDimission;
	}

	public void setIsDimission(Integer isDimission) {
		this.isDimission = isDimission;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public List<String> getRoleTexts() {
	
		return roleTexts;
	}

	public void setRoleTexts(List<String> roleTexts) {
	
		this.roleTexts = roleTexts;
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

	public String getAddress() {
	
		return address;
	}

	public void setAddress(String address) {
	
		this.address = address;
	}

	public String getAvatar() {
	
		return avatar;
	}

	public void setAvatar(String avatar) {
	
		this.avatar = avatar;
	}
	


}
