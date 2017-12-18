package com.yundao.tenant.app.dto.customer.opensea;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户池分页请求数据
 *
 * @author jan
 * @create 2017-08-14 AM9:10
 **/
public class CustomerOpenSeaPageReqDto extends AbstractBasePageDto {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "搜索条件")
	private String keyword;
	@ApiModelProperty("是否成交")
	private String isDeals;
	@ApiModelProperty("客户级别")
	private String level;
	@ApiModelProperty("标签")
	private String tagNames;
	@ApiModelProperty("投资偏好")
	private String invests;
	@ApiModelProperty("风险特征")
	private String riskRatings;
	@ApiModelProperty("投资者类型")
	private String investorTypes;
	@ApiModelProperty("性别类型")
	private String gender;
	@ApiModelProperty("来源")
	private String sources;
	@ApiModelProperty("省份的code值")
	private String provinceCodes;
	@ApiModelProperty("城市的code值")
	private String cityCodes;
	@ApiModelProperty(value = "创建日期，开始时间")
	private Long createDateBegin;
	@ApiModelProperty(value = "创建日期，结束时间")
	private Long createDateEnd;
	@ApiModelProperty("生日时间 开始时间")
	private Long birthDayBegin;
	@ApiModelProperty("生日时间 结束时间")
	private Long birthDayEnd;

	public String getKeyword() {

		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

	public String getIsDeals() {
	
		return isDeals;
	}

	public void setIsDeals(String isDeals) {
	
		this.isDeals = isDeals;
	}

	public String getLevel() {
	
		return level;
	}

	public void setLevel(String level) {
	
		this.level = level;
	}

	public String getTagNames() {
	
		return tagNames;
	}

	public void setTagNames(String tagNames) {
	
		this.tagNames = tagNames;
	}

	public String getInvests() {
	
		return invests;
	}

	public void setInvests(String invests) {
	
		this.invests = invests;
	}

	public String getRiskRatings() {
	
		return riskRatings;
	}

	public void setRiskRatings(String riskRatings) {
	
		this.riskRatings = riskRatings;
	}

	public String getInvestorTypes() {
	
		return investorTypes;
	}

	public void setInvestorTypes(String investorTypes) {
	
		this.investorTypes = investorTypes;
	}

	public String getGender() {
	
		return gender;
	}

	public void setGender(String gender) {
	
		this.gender = gender;
	}

	public String getSources() {
	
		return sources;
	}

	public void setSources(String sources) {
	
		this.sources = sources;
	}

	public String getProvinceCodes() {
	
		return provinceCodes;
	}

	public void setProvinceCodes(String provinceCodes) {
	
		this.provinceCodes = provinceCodes;
	}

	public String getCityCodes() {
	
		return cityCodes;
	}

	public void setCityCodes(String cityCodes) {
	
		this.cityCodes = cityCodes;
	}


	public Long getCreateDateBegin() {
	
		return createDateBegin;
	}

	public void setCreateDateBegin(Long createDateBegin) {
	
		this.createDateBegin = createDateBegin;
	}

	public Long getCreateDateEnd() {
	
		return createDateEnd;
	}

	public void setCreateDateEnd(Long createDateEnd) {
	
		this.createDateEnd = createDateEnd;
	}

	public Long getBirthDayBegin() {
	
		return birthDayBegin;
	}

	public void setBirthDayBegin(Long birthDayBegin) {
	
		this.birthDayBegin = birthDayBegin;
	}

	public Long getBirthDayEnd() {
	
		return birthDayEnd;
	}

	public void setBirthDayEnd(Long birthDayEnd) {
	
		this.birthDayEnd = birthDayEnd;
	}
	

}
