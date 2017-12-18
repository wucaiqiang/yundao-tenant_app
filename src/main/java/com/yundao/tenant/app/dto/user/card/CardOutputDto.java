
package com.yundao.tenant.app.dto.user.card;

import java.io.Serializable;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:27:27
 * 
 * @author wucq
 * @version
 */
public class CardOutputDto implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String outputLabel;
	private CardOutputDataDto outputValue;
	public String getOutputLabel() {
	
		return outputLabel;
	}
	public void setOutputLabel(String outputLabel) {
	
		this.outputLabel = outputLabel;
	}
	public CardOutputDataDto getOutputValue() {
	
		return outputValue;
	}
	public void setOutputValue(CardOutputDataDto outputValue) {
	
		this.outputValue = outputValue;
	}
	
}
