
package com.yundao.tenant.app.view.report.declaration;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.report.report.Value;
import com.yundao.tenant.app.dto.report.report.XTitle;
import com.yundao.tenant.app.dto.report.report.YConfigeData;

/**
 * Function: Reason: Date: 2017年11月1日 上午10:43:52
 * 
 * @author 欧阳利
 * @version
 */
public class Structure7001View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	private Boolean isIndex;
	
	private String title;
	
	private String unit;
	
	private List<Value> values;

	private YConfigeData yConfigeData;

	private List<XTitle> xTitles;



	public String getUnit() {
	
		return unit;
	}

	public void setUnit(String unit) {
	
		this.unit = unit;
	}

	public Boolean getIsIndex() {
	
		return isIndex;
	}

	public void setIsIndex(Boolean isIndex) {
	
		this.isIndex = isIndex;
	}

	public String getTitle() {
	
		return title;
	}

	public void setTitle(String title) {
	
		this.title = title;
	}

	public List<Value> getValues() {
	
		return values;
	}

	public void setValues(List<Value> values) {
	
		this.values = values;
	}

	public YConfigeData getyConfigeData() {

		return yConfigeData;
	}

	public void setyConfigeData(YConfigeData yConfigeData) {

		this.yConfigeData = yConfigeData;
	}

	public List<XTitle> getxTitles() {

		return xTitles;
	}

	public void setxTitles(List<XTitle> xTitles) {

		this.xTitles = xTitles;
	}
}
