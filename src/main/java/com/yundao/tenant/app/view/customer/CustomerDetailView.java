
package com.yundao.tenant.app.view.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月18日 下午5:02:56
 * 
 * @author wucq
 * @version
 */
public class CustomerDetailView implements Serializable {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户编号
	 */
	private String customerCode;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 性别
	 */
	private NameIdView gender;
	/**
	 * 性别列表集合
	 */
	private List<NameIdView> genderList;
	/**
	 * 客户级别
	 */
	private NameIdView customerLevel;
	/**
	 * 客户级别列表集合
	 */
	private List<NameIdView> customerLevelList;
	/**
	 * 生日时间戳
	 */
	private Date birthday;
	/**
	 * 来源
	 */
	private NameIdView from;
	/**
	 * 来源列表
	 */
	private List<NameIdView> fromList;
	/**
	 * 证件类型
	 */
	private NameIdView certificateType;
	/**
	 * 证件类型集合
	 */
	private List<NameIdView> certificateTypeList;
	/**
	 * 证件号码
	 */
	private String certificateNumber;
	/**
	 * 证件正面照
	 */
	private String identityFrontCardUrl;
	/**
	 * 证件反面照
	 */
	private String identityBackCardUrl;
	/**
	 * 资产证明图片数组
	 */
	private List<String> assetsCertificateUrlList;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 微信号
	 */
	private String weChat;
	/**
	 * qq号
	 */
	private String qq;
	/**
	 * 邮箱号
	 */
	private String mail;
	/**
	 * 省
	 */
	private NameIdView province;
	/**
	 * 城市
	 */
	private NameIdView city;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 行业
	 */
	private String industry;
	/**
	 * 机构
	 */
	private String organization;
	/**
	 * 职位
	 */
	private String job;
	/**
	 * 选择的投资偏好集合
	 */
	private List<NameIdView> investLikeSelectList;
	/**
	 * 投资偏好集合
	 */
	private List<NameIdView> investLikeList;
	/**
	 * 投资人类型
	 */
	private NameIdView investManType;
	/**
	 * 投资人类型集合
	 */
	private List<NameIdView> investManTypeList;
	/**
	 * 风险特征
	 */
	private NameIdView riskType;
	/**
	 * 风险特征集合
	 */
	private List<NameIdView> riskTypeList;
	/**
	 * 客户的多套银行卡集合
	 */
	private List<CustomerBankView> bankList;

	public String getCustomerCode() {

		return customerCode;
	}

	public void setCustomerCode(String customerCode) {

		this.customerCode = customerCode;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public NameIdView getGender() {

		return gender;
	}

	public void setGender(NameIdView gender) {

		this.gender = gender;
	}

	public List<NameIdView> getGenderList() {

		return genderList;
	}

	public void setGenderList(List<NameIdView> genderList) {

		this.genderList = genderList;
	}

	public NameIdView getCustomerLevel() {

		return customerLevel;
	}

	public void setCustomerLevel(NameIdView customerLevel) {

		this.customerLevel = customerLevel;
	}

	public List<NameIdView> getCustomerLevelList() {

		return customerLevelList;
	}

	public void setCustomerLevelList(List<NameIdView> customerLevelList) {

		this.customerLevelList = customerLevelList;
	}

	public Long getBirthday() {
		if (birthday != null) {
			return birthday.getTime();
		}
		return null;
	}

	public void setBirthday(Date birthday) {

		this.birthday = birthday;
	}

	public NameIdView getFrom() {

		return from;
	}

	public void setFrom(NameIdView from) {

		this.from = from;
	}

	public List<NameIdView> getFromList() {

		return fromList;
	}

	public void setFromList(List<NameIdView> fromList) {

		this.fromList = fromList;
	}

	public NameIdView getCertificateType() {

		return certificateType;
	}

	public void setCertificateType(NameIdView certificateType) {

		this.certificateType = certificateType;
	}

	public List<NameIdView> getCertificateTypeList() {

		return certificateTypeList;
	}

	public void setCertificateTypeList(List<NameIdView> certificateTypeList) {

		this.certificateTypeList = certificateTypeList;
	}

	public String getCertificateNumber() {

		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {

		this.certificateNumber = certificateNumber;
	}

	public String getIdentityFrontCardUrl() {

		return identityFrontCardUrl;
	}

	public void setIdentityFrontCardUrl(String identityFrontCardUrl) {

		this.identityFrontCardUrl = identityFrontCardUrl;
	}

	public String getIdentityBackCardUrl() {

		return identityBackCardUrl;
	}

	public void setIdentityBackCardUrl(String identityBackCardUrl) {

		this.identityBackCardUrl = identityBackCardUrl;
	}

	public List<String> getAssetsCertificateUrlList() {

		return assetsCertificateUrlList;
	}

	public void setAssetsCertificateUrlList(List<String> assetsCertificateUrlList) {

		this.assetsCertificateUrlList = assetsCertificateUrlList;
	}

	public String getRemark() {

		return remark;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}

	public String getWeChat() {

		return weChat;
	}

	public void setWeChat(String weChat) {

		this.weChat = weChat;
	}

	public String getQq() {

		return qq;
	}

	public void setQq(String qq) {

		this.qq = qq;
	}

	public String getMail() {

		return mail;
	}

	public void setMail(String mail) {

		this.mail = mail;
	}

	public NameIdView getProvince() {

		return province;
	}

	public void setProvince(NameIdView province) {

		this.province = province;
	}

	public NameIdView getCity() {

		return city;
	}

	public void setCity(NameIdView city) {

		this.city = city;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public String getIndustry() {

		return industry;
	}

	public void setIndustry(String industry) {

		this.industry = industry;
	}

	public String getOrganization() {

		return organization;
	}

	public void setOrganization(String organization) {

		this.organization = organization;
	}

	public String getJob() {

		return job;
	}

	public void setJob(String job) {

		this.job = job;
	}

	public List<NameIdView> getInvestLikeSelectList() {

		return investLikeSelectList;
	}

	public void setInvestLikeSelectList(List<NameIdView> investLikeSelectList) {

		this.investLikeSelectList = investLikeSelectList;
	}

	public List<NameIdView> getInvestLikeList() {

		return investLikeList;
	}

	public void setInvestLikeList(List<NameIdView> investLikeList) {

		this.investLikeList = investLikeList;
	}

	public NameIdView getInvestManType() {

		return investManType;
	}

	public void setInvestManType(NameIdView investManType) {

		this.investManType = investManType;
	}

	public List<NameIdView> getInvestManTypeList() {

		return investManTypeList;
	}

	public void setInvestManTypeList(List<NameIdView> investManTypeList) {

		this.investManTypeList = investManTypeList;
	}

	public NameIdView getRiskType() {

		return riskType;
	}

	public void setRiskType(NameIdView riskType) {

		this.riskType = riskType;
	}

	public List<NameIdView> getRiskTypeList() {

		return riskTypeList;
	}

	public void setRiskTypeList(List<NameIdView> riskTypeList) {

		this.riskTypeList = riskTypeList;
	}

	public List<CustomerBankView> getBankList() {

		return bankList;
	}

	public void setBankList(List<CustomerBankView> bankList) {

		this.bankList = bankList;
	}

}
