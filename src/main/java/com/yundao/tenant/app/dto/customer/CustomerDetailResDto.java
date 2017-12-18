

package com.yundao.tenant.app.dto.customer;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.dto.common.DataAndPermissionDto;
import com.yundao.tenant.app.dto.tag.TagDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月9日 上午11:00:38 
 * @author   wucq
 * @version   
 */
public class CustomerDetailResDto implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户ID
	 */
	private Long id;
	/**
	 * 客户名称
	 */
	private String name;
	/**
	 * 客户手机号码
	 */
	private String mobile;
	/**
	 * 客户编号
	 */
	private String number;
	/**
	 * 用户所属理财师ID
	 */
    private Long userId;
    /**
	 * 用户所属理财师名称
	 */
	private String afpName;
	/**
	 * 是否关注
	 */
	private boolean focus;
	/**
	 * 客户标签
	 */
	private List<TagDto> tags;
	/**
	 * 客户联系方式
	 */
	private DataAndPermissionDto<CustomerContactDto> contact;
	/**
	 *  除了联系方式外其它资料
	 */
	private DataAndPermissionDto<CustomerInfoDto> info;
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public boolean isFocus() {
	
		return focus;
	}
	public void setFocus(boolean focus) {
	
		this.focus = focus;
	}
	
	public void setContact(DataAndPermissionDto<CustomerContactDto> contact) {
	
		this.contact = contact;
	}
	public List<TagDto> getTags() {
	
		return tags;
	}
	public void setTags(List<TagDto> tags) {
	
		this.tags = tags;
	}
	public Long getUserId() {
	
		return userId;
	}
	public void setUserId(Long userId) {
	
		this.userId = userId;
	}
	public DataAndPermissionDto<CustomerContactDto> getContact() {
	
		return contact;
	}
	public DataAndPermissionDto<CustomerInfoDto> getInfo() {
	
		return info;
	}
	public void setInfo(DataAndPermissionDto<CustomerInfoDto> info) {
	
		this.info = info;
	}
	public String getMobile() {
	
		return mobile;
	}
	public void setMobile(String mobile) {
	
		this.mobile = mobile;
	}
	public Long getId() {
	
		return id;
	}
	public void setId(Long id) {
	
		this.id = id;
	}
	public String getNumber() {
	
		return number;
	}
	public void setNumber(String number) {
	
		this.number = number;
	}
	public String getAfpName() {
	
		return afpName;
	}
	public void setAfpName(String afpName) {
	
		this.afpName = afpName;
	}
	
	
}

