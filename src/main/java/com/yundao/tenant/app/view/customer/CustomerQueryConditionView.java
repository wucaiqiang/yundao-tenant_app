
package com.yundao.tenant.app.view.customer;

import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月16日 下午4:25:42
 * 
 * @author wucq
 * @version
 */
public class CustomerQueryConditionView {
	/**
	 * 分类名称（例如：全部，我关注的，未跟进的）
	 */
	private List<NameIdView> customerTypeList;
	/**
	 * 客户标签集合
	 */
	private List<NameIdView> tagList;
	/**
	 * 是否成交集合
	 */
	private List<NameIdView> bargainList;
	/**
	 * 客户级别集合
	 */
	private List<NameIdView> levelList;
	/**
	 * 投资偏好集合
	 */
	private List<NameIdView> investLikeList;
	/**
	 * 风险特征集合
	 */
	private List<NameIdView> riskList;
	/**
	 * 投资者类型集合
	 */
	private List<NameIdView> investorTypeList;
	/**
	 * 性别类型集合
	 */
	private List<NameIdView> genderList;
	/**
	 * 来源集合
	 */
	private List<NameIdView> sourceList;

	public CustomerQueryConditionView() {
	}


	public List<NameIdView> getCustomerTypeList() {
	
		return customerTypeList;
	}


	public void setCustomerTypeList(List<NameIdView> customerTypeList) {
	
		this.customerTypeList = customerTypeList;
	}


	public List<NameIdView> getTagList() {
	
		return tagList;
	}

	public void setTagList(List<NameIdView> tagList) {
	
		this.tagList = tagList;
	}

	public List<NameIdView> getBargainList() {
	
		return bargainList;
	}

	public void setBargainList(List<NameIdView> bargainList) {
	
		this.bargainList = bargainList;
	}

	public List<NameIdView> getLevelList() {
	
		return levelList;
	}

	public void setLevelList(List<NameIdView> levelList) {
	
		this.levelList = levelList;
	}

	public List<NameIdView> getInvestLikeList() {
	
		return investLikeList;
	}

	public void setInvestLikeList(List<NameIdView> investLikeList) {
	
		this.investLikeList = investLikeList;
	}

	public List<NameIdView> getRiskList() {
	
		return riskList;
	}

	public void setRiskList(List<NameIdView> riskList) {
	
		this.riskList = riskList;
	}

	public List<NameIdView> getInvestorTypeList() {
	
		return investorTypeList;
	}

	public void setInvestorTypeList(List<NameIdView> investorTypeList) {
	
		this.investorTypeList = investorTypeList;
	}

	public List<NameIdView> getGenderList() {
	
		return genderList;
	}

	public void setGenderList(List<NameIdView> genderList) {
	
		this.genderList = genderList;
	}

	public List<NameIdView> getSourceList() {
	
		return sourceList;
	}

	public void setSourceList(List<NameIdView> sourceList) {
	
		this.sourceList = sourceList;
	}
	

}
