
package com.yundao.tenant.app.dto.common;

/**
 * Function: Reason: Date: 2017年8月9日 上午11:37:29
 * 
 * @author wucq
 * @version
 */
public class  DataAndPermissionDto<T>  {

	/**
	 * serialVersionUID:
	 */
	private T data;
	private DataPermissionDto permission;
	public DataAndPermissionDto(){
	}
	public DataAndPermissionDto(T data){
		this.data=data;
	}
	public DataAndPermissionDto(T data,DataPermissionDto permission){
		this.data=data;
		this.permission=permission;
	}
	
	public T getData() {
	
		return data;
	}
	public void setData(T data) {
	
		this.data = data;
	}
	public DataPermissionDto getPermission() {
	
		return permission;
	}
	public void setPermission(DataPermissionDto permission) {
	
		this.permission = permission;
	}

	
}
