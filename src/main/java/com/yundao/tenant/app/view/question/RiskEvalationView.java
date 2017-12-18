
package com.yundao.tenant.app.view.question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.view.TitleAndContentView;

/**
 * Function: Reason: Date: 2017年9月12日 上午11:34:58
 * 
 * @author wucq
 * @version
 */
public class RiskEvalationView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String type;
	private String score;
	private Date testTime;
	private List<TitleAndContentView> testSubjects;
	/**
	 * 测评标题 e.g. 我为客户配置的方案
	 */
	private String evaluateExamResultTitle;
	/**
	 * 我为客户配置的风险测评方案
	 */
	private List<AfpRiskEvalationView> evaluateExamResultList;

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getScore() {

		return score;
	}

	public void setScore(String score) {

		this.score = score;
	}

	public String getTestTime() {
		if (testTime != null) {
			return DateFormatUtils.format(testTime, "yyyy-MM-dd");
		}
		return "";
	}

	public void setTestTime(Date testTime) {

		this.testTime = testTime;
	}

	public List<TitleAndContentView> getTestSubjects() {

		return testSubjects;
	}

	public void setTestSubjects(List<TitleAndContentView> testSubjects) {

		this.testSubjects = testSubjects;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public String getEvaluateExamResultTitle() {
	
		return evaluateExamResultTitle;
	}

	public void setEvaluateExamResultTitle(String evaluateExamResultTitle) {
	
		this.evaluateExamResultTitle = evaluateExamResultTitle;
	}

	public List<AfpRiskEvalationView> getEvaluateExamResultList() {
	
		return evaluateExamResultList;
	}

	public void setEvaluateExamResultList(List<AfpRiskEvalationView> evaluateExamResultList) {
	
		this.evaluateExamResultList = evaluateExamResultList;
	}
	

}
