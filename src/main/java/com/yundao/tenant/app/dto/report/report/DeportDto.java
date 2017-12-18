

package com.yundao.tenant.app.dto.report.report;

import java.util.List;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:25:49 
 * @author   欧阳利
 * @version   
 */
public class DeportDto {
   private List<Value> value;
   
   private YConfigeData yConfigeData;
   
   private List<XTitle> xTitles;

public List<Value> getValue() {

	return value;
}

public void setValue(List<Value> value) {

	this.value = value;
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

