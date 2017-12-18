
package com.yundao.tenant.app.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月7日 下午7:50:00
 * 
 * @author wucq
 * @version
 */
public class SearchView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<String> keywords;

	public SearchView() {
	}

	public SearchView(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getKeywords() {
		if (keywords != null && !keywords.isEmpty()) {
			// 去重
			Set<String> set = new HashSet<>(keywords);
			keywords.clear();
			keywords.addAll(set);
		}
		return keywords;
	}

	public void setKeywords(List<String> keywords) {

		this.keywords = keywords;
	}
}
