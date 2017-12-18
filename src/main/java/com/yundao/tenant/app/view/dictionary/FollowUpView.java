

package com.yundao.tenant.app.view.dictionary;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月24日 下午1:45:37 
 * @author   wucq
 * @version   
 */
public class FollowUpView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 跟进方式
	 */
	private List<NameIdView> modes;
	/**
	 * 跟进状态
	 */
	private List<NameIdView> stateList;
	public List<NameIdView> getModes() {
	
		return modes;
	}
	public void setModes(List<NameIdView> modes) {
	
		this.modes = modes;
	}
	public List<NameIdView> getStateList() {
	
		return stateList;
	}
	public void setStateList(List<NameIdView> stateList) {
	
		this.stateList = stateList;
	}

}

