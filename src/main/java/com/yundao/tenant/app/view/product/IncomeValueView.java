
package com.yundao.tenant.app.view.product;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.util.NumberUtil;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:02:10
 * 
 * @author wucq
 * @version
 */
public class IncomeValueView implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String startAmount;
	private String endAmount;
	private String timeLimit;
	private Double fixedRate;
	private Double floatRate;
	private String end = "以上";

	public IncomeValueView() {
	}

	public IncomeValueView(String startAmount, String endAmount, String timeLimit, Double fixedRate, Double floatRate) {
		this.startAmount = startAmount;
		this.endAmount = endAmount;
		this.timeLimit = timeLimit;
		this.fixedRate = fixedRate;
		this.floatRate = floatRate;
	}

	public String getStartAmount() {
		if (StringUtils.isBlank(startAmount)) {
			return "";
		}
		if (NumberUtils.toInt(endAmount) != 0) {
			return startAmount;
		}
		return startAmount + "万";
	}

	public void setStartAmount(String startAmount) {

		this.startAmount = startAmount;
	}

	public String getEndAmount() {

		if (NumberUtils.toInt(endAmount) == 0 && NumberUtils.toInt(startAmount) != 0) {
			return end;
		}

		if (NumberUtils.toInt(endAmount) == 0) {
			return "";
		}
		if (StringUtils.isNotBlank(startAmount) && NumberUtils.toInt(startAmount) != 0) {
			return "-" + endAmount + "万";
		}
		return endAmount + "万";
	}

	public void setEndAmount(String endAmount) {

		this.endAmount = endAmount;
	}

	public String getTimeLimit() {

		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {

		this.timeLimit = timeLimit;
	}

	public String getFixedRate() {
		if (fixedRate == null) {
			return "";
		}
		return NumberUtil.trimDoubleZero(String.valueOf(fixedRate)) + "%";
	}

	public void setFixedRate(Double fixedRate) {

		this.fixedRate = fixedRate;
	}

	public String getFloatRate() {
		if (floatRate == null) {
			return "";
		}
		return NumberUtil.trimDoubleZero(String.valueOf(floatRate)) + "%";
	}

	public void setFloatRate(Double floatRate) {

		this.floatRate = floatRate;
	}

	public String getEnd() {

		return end;
	}

	public void setEnd(String end) {

		this.end = end;
	}

}
