
package com.yundao.tenant.app.dto.user.card;

import java.io.Serializable;
import java.util.List;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:28:53
 * 
 * @author wucq
 * @version
 */
public class CardDataValueDto implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 地址结果数组
	 */
	private List<String> addr;
	/**
	 * 公司结果数组
	 */
	private List<String> company;
	/**
	 * 部门结果数组
	 */
	private List<String> department;
	/**
	 * 职位结果数组
	 */
	private List<String> title;
	/**
	 * 手机结果数组
	 */
	private List<String> tel_cell;
	/**
	 * 座机结果数组
	 */
	private List<String> tel_work;
	/**
	 * 邮箱结果数组
	 */
	private List<String> email;
	/**
	 * 请求对应的唯一表示
	 */
	private String request_id;
	/**
	 * 识别成功与否
	 */
	private boolean success;
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public List<String> getAddr() {
	
		return addr;
	}
	public void setAddr(List<String> addr) {
	
		this.addr = addr;
	}
	public List<String> getCompany() {
	
		return company;
	}
	public void setCompany(List<String> company) {
	
		this.company = company;
	}
	public List<String> getDepartment() {
	
		return department;
	}
	public void setDepartment(List<String> department) {
	
		this.department = department;
	}
	public List<String> getTitle() {
	
		return title;
	}
	public void setTitle(List<String> title) {
	
		this.title = title;
	}
	public List<String> getTel_cell() {
	
		return tel_cell;
	}
	public void setTel_cell(List<String> tel_cell) {
	
		this.tel_cell = tel_cell;
	}
	public List<String> getTel_work() {
	
		return tel_work;
	}
	public void setTel_work(List<String> tel_work) {
	
		this.tel_work = tel_work;
	}
	public List<String> getEmail() {
	
		return email;
	}
	public void setEmail(List<String> email) {
	
		this.email = email;
	}
	public String getRequest_id() {
	
		return request_id;
	}
	public void setRequest_id(String request_id) {
	
		this.request_id = request_id;
	}
	public boolean isSuccess() {
	
		return success;
	}
	public void setSuccess(boolean success) {
	
		this.success = success;
	}
	

}
