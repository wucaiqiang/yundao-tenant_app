package com.yundao.tenant.app.dto.permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 多个数据对象编码权限校验
 *
 * @author jan
 * @create 2017-08-09 PM5:37
 **/
public class PermissionMultiCodeDto {

    /**
     * 数据对象编码
     */
    private String dataObjectCode;

    /**
     * 创建者id（数据的拥有者id）
     */
    private List<Long> ownerIds;
    
    
    public void setOwnerId(Long ownerId) {
    	List<Long> ownerIds = new ArrayList<Long>();
    	ownerIds.add(ownerId);
    	this.ownerIds=ownerIds;
	}
	public PermissionMultiCodeDto(){}
    public PermissionMultiCodeDto(String dataObjectCode,List<Long> ownerIds){
    	this.dataObjectCode=dataObjectCode;
    	this.ownerIds=ownerIds;
    }
    
    public PermissionMultiCodeDto(String dataObjectCode,Long ownerId){
    	this.dataObjectCode=dataObjectCode;
    	List<Long> ownerIds = new ArrayList<Long>();
    	ownerIds.add(ownerId);
    	this.ownerIds=ownerIds;
    }

    public String getDataObjectCode() {
        return dataObjectCode;
    }

    public void setDataObjectCode(String dataObjectCode) {
        this.dataObjectCode = dataObjectCode;
    }
	public List<Long> getOwnerIds() {
		return ownerIds;
	}
	public void setOwnerIds(List<Long> ownerIds) {
		this.ownerIds = ownerIds;
	}


}
