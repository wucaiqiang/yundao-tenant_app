package com.yundao.tenant.app.dto.product;

import java.util.List;

import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.dto.roadshow.RoadshowResDto;

import io.swagger.annotations.ApiModelProperty;

public class ProductPartDetailDto extends BaseProduct {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品对接人名称
	 */
	private String receiverName;
	/**
	 * 对接人手机号码
	 */
	private String receiverMobile;
	/**
	 * 产品助理名称
	 */
	private String assistantName;
	/**
	 * 发行通道中文名
	 */
	private String issuedChannelText;
	/**
	 * 投资标的中文名
	 */
	private String investDomainText;
	private String issuedStatusText;
	/**
	 * 币种中文名
	 */
	private String currencyCodeText;
	/**
	 * 产品类型中文名
	 */
	private String typeName;

	private BaseProductSale productSale;
	/**
	 * 认购费
	 */
	private String proBuyFee;
	/**
	 * 管理费
	 */
	private String proManageFee;

	/**
	 * 创建者id
	 */
	private Long createUserId;

	/**
	 * 佣金规则
	 */
	private List<ProductCommissionRuleDto> commissionDtos;
	/**
	 * 收益
	 */
	private List<ProductIncomeRuleDto> incomeDtos;
	/**
	 * 产品附件
	 */
	private List<ProductAttachListResDto> attachDtos;
	/**
	 * 产品公告
	 */
	private List<ProductNoticeDto> noticeModels;

	private String levelText;

	private String riskLevelText;
	/**
	 * 产品的预约数
	 */
	private Integer reservationNum;
	/**
	 * 路演信息
	 */
	private List<RoadshowResDto> roadshows;
	/**
	 * 产品信息字段
	 */
	private List<FieldDetailDto> productFieldDtos;

	public String getLevelText() {
		return levelText;
	}

	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}

	public String getRiskLevelText() {
		return riskLevelText;
	}

	public void setRiskLevelText(String riskLevelText) {
		this.riskLevelText = riskLevelText;
	}

	public String getTypeName() {

		return typeName;
	}

	public void setTypeName(String typeName) {

		this.typeName = typeName;
	}

	public BaseProductSale getProductSale() {

		return productSale;
	}

	public void setProductSale(BaseProductSale productSale) {

		this.productSale = productSale;
	}

	public List<ProductCommissionRuleDto> getCommissionDtos() {

		return commissionDtos;
	}

	public void setCommissionDtos(List<ProductCommissionRuleDto> commissionDtos) {

		this.commissionDtos = commissionDtos;
	}

	public List<ProductIncomeRuleDto> getIncomeDtos() {

		return incomeDtos;
	}

	public void setIncomeDtos(List<ProductIncomeRuleDto> incomeDtos) {

		this.incomeDtos = incomeDtos;
	}

	public List<ProductNoticeDto> getNoticeModels() {

		return noticeModels;
	}

	public void setNoticeModels(List<ProductNoticeDto> noticeModels) {

		this.noticeModels = noticeModels;
	}

	public String getProBuyFee() {

		return proBuyFee;
	}

	public void setProBuyFee(String proBuyFee) {

		this.proBuyFee = proBuyFee;
	}

	public String getProManageFee() {

		return proManageFee;
	}

	public void setProManageFee(String proManageFee) {

		this.proManageFee = proManageFee;
	}

	public String getReceiverName() {
		if (BooleanUtils.isEmpty(receiverName)) {
			return "--";
		}
		return receiverName;
	}

	public void setReceiverName(String receiverName) {

		this.receiverName = receiverName;
	}

	public String getAssistantName() {
		if (BooleanUtils.isEmpty(assistantName)) {
			return "--";
		}
		return assistantName;
	}

	public void setAssistantName(String assistantName) {

		this.assistantName = assistantName;
	}

	public String getIssuedChannelText() {

		return issuedChannelText;
	}

	public void setIssuedChannelText(String issuedChannelText) {

		this.issuedChannelText = issuedChannelText;
	}

	public String getInvestDomainText() {

		return investDomainText;
	}

	public void setInvestDomainText(String investDomainText) {

		this.investDomainText = investDomainText;
	}

	public List<ProductAttachListResDto> getAttachDtos() {

		return attachDtos;
	}

	public void setAttachDtos(List<ProductAttachListResDto> attachDtos) {

		this.attachDtos = attachDtos;
	}

	public String getCurrencyCodeText() {

		return currencyCodeText;
	}

	public void setCurrencyCodeText(String currencyCodeText) {

		this.currencyCodeText = currencyCodeText;
	}

	public String getReceiverMobile() {

		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {

		this.receiverMobile = receiverMobile;
	}

	public String getIssuedStatusText() {

		return issuedStatusText;
	}

	public void setIssuedStatusText(String issuedStatusText) {

		this.issuedStatusText = issuedStatusText;
	}

	public Integer getReservationNum() {

		return reservationNum;
	}

	public void setReservationNum(Integer reservationNum) {

		this.reservationNum = reservationNum;
	}

	@Override
	public Long getCreateUserId() {
		return createUserId;
	}

	@Override
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public List<RoadshowResDto> getRoadshows() {
	
		return roadshows;
	}

	public void setRoadshows(List<RoadshowResDto> roadshows) {
	
		this.roadshows = roadshows;
	}

	public List<FieldDetailDto> getProductFieldDtos() {
	
		return productFieldDtos;
	}

	public void setProductFieldDtos(List<FieldDetailDto> productFieldDtos) {
	
		this.productFieldDtos = productFieldDtos;
	}

}
