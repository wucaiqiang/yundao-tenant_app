
package com.yundao.tenant.app.dto.user.card;

import java.io.Serializable;
import java.util.List;

/**
 * Function: Reason: Date: 2017年11月24日 下午4:26:45
 * 
 * @author wucq
 * @version
 */
public class CardScanInfoDto implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private List<CardOutputDto> outputs;

	public List<CardOutputDto> getOutputs() {
	
		return outputs;
	}

	public void setOutputs(List<CardOutputDto> outputs) {
	
		this.outputs = outputs;
	}
	
}
