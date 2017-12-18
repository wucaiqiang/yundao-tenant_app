
package com.yundao.tenant.app.view.sale.declaration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Function: Reason: Date: 2017年9月5日 上午11:46:38
 * 
 * @author wucq
 * @version
 */
public class ComplianceView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 投资者基本信息表(自然人) 图片
	 */
	private List<String> investorBaseInfoFormUrlList;
	/**
	 * 投资者风险匹配告知书及投资者确认函
	 */
	private List<String> investorRiskConfirmationUrlList;
	/**
	 * 风险承受能力调查问卷(自然人)
	 */
	private List<String> riskToleranceQuestionnaireUrlList;
	/**
	 * 基金回访确认书
	 */
	private List<String> fundCallbackUrlList;
	/**
	 * 其他合规文件
	 */
	private List<String> otherContractUrlList;
	/**
	 * 合规双录
	 */
	private VideoView orderComplianceMultimediaFile;
	public List<String> getInvestorBaseInfoFormUrlList() {
	
		return investorBaseInfoFormUrlList;
	}
	public void setInvestorBaseInfoFormUrlList(List<String> investorBaseInfoFormUrlList) {
	
		this.investorBaseInfoFormUrlList = investorBaseInfoFormUrlList;
	}
	public List<String> getInvestorRiskConfirmationUrlList() {
	
		return investorRiskConfirmationUrlList;
	}
	public void setInvestorRiskConfirmationUrlList(List<String> investorRiskConfirmationUrlList) {
	
		this.investorRiskConfirmationUrlList = investorRiskConfirmationUrlList;
	}
	public List<String> getRiskToleranceQuestionnaireUrlList() {
	
		return riskToleranceQuestionnaireUrlList;
	}
	public void setRiskToleranceQuestionnaireUrlList(List<String> riskToleranceQuestionnaireUrlList) {
	
		this.riskToleranceQuestionnaireUrlList = riskToleranceQuestionnaireUrlList;
	}
	public List<String> getFundCallbackUrlList() {
	
		return fundCallbackUrlList;
	}
	public void setFundCallbackUrlList(List<String> fundCallbackUrlList) {
	
		this.fundCallbackUrlList = fundCallbackUrlList;
	}
	public List<String> getOtherContractUrlList() {
	
		return otherContractUrlList;
	}
	public void setOtherContractUrlList(List<String> otherContractUrlList) {
	
		this.otherContractUrlList = otherContractUrlList;
	}
	public VideoView getOrderComplianceMultimediaFile() {
	
		return orderComplianceMultimediaFile;
	}
	public void setOrderComplianceMultimediaFile(VideoView orderComplianceMultimediaFile) {
	
		this.orderComplianceMultimediaFile = orderComplianceMultimediaFile;
	}
	

}
