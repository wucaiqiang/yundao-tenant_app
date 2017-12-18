
package com.yundao.tenant.app.view.customer;


import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3021View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 动态创建时间
	 */
	private Date createTime;
	/**
	 * 动态内容
	 */
	private String content;
	/**
	 * 方式
	 */
	private String followType;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 发送跟进的时候所在位置
	 */
	private String location;
	/**
	 * 操作人
	 */
	private String operateMan;
	/**
	 * 图片列表
	 */
	private List<String> pictureList;
	public Date getCreateTime() {
	
		return createTime;
	}
	public void setCreateTime(Date createTime) {
	
		this.createTime = createTime;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public String getFollowType() {
	
		return followType;
	}
	public void setFollowType(String followType) {
	
		this.followType = followType;
	}
	public String getState() {
	
		return state;
	}
	public void setState(String state) {
	
		this.state = state;
	}
	public String getLocation() {
	
		return location;
	}
	public void setLocation(String location) {
	
		this.location = location;
	}
	public List<String> getPictureList() {
	
		return pictureList;
	}
	public void setPictureList(List<String> pictureList) {
	
		this.pictureList = pictureList;
	}
	public String getOperateMan() {
	
		return operateMan;
	}
	public void setOperateMan(String operateMan) {
	
		this.operateMan = operateMan;
	}
	
	
}
