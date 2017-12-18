

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
public class DictionaryItemsView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 跟进方式
	 */
	private List<NameIdView> items;
	public List<NameIdView> getItems() {
	
		return items;
	}
	public void setItems(List<NameIdView> items) {
	
		this.items = items;
	}

}

