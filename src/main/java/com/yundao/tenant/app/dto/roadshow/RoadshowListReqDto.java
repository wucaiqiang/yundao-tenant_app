
package com.yundao.tenant.app.dto.roadshow;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

public class RoadshowListReqDto extends AbstractBasePageDto {
	private static final long serialVersionUID = 1L;
	private Long columnId;
	public Long getColumnId() {
	
		return columnId;
	}
	public void setColumnId(Long columnId) {
	
		this.columnId = columnId;
	}
	
}
