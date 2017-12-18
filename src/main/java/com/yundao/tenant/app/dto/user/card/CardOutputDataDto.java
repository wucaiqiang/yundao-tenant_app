
package com.yundao.tenant.app.dto.user.card;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:27:27
 * 
 * @author wucq
 * @version
 */
public class CardOutputDataDto implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String dataType;
	private String dataValue;
	public String getDataType() {
	
		return dataType;
	}
	public void setDataType(String dataType) {
	
		this.dataType = dataType;
	}
	public String getDataValue() {
	
		return dataValue;
	}
	public void setDataValue(String dataValue) {
	
		this.dataValue = dataValue;
	}
	
}
