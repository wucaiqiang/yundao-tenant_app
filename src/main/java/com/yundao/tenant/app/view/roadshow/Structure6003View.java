
package com.yundao.tenant.app.view.roadshow;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.view.NameValueView;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure6003View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<NameValueView> baseInfo;
	public Structure6003View(){}
	public Structure6003View(List<NameValueView> baseInfo){
		this.baseInfo=baseInfo;
	}
	public List<NameValueView> getBaseInfo() {
	
		return baseInfo;
	}
	public void setBaseInfo(List<NameValueView> baseInfo) {
	
		this.baseInfo = baseInfo;
	}
	
}
