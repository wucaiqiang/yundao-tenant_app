package com.yundao.tenant.app.view.sale.reservation;

import com.yundao.tenant.app.dto.common.ActionDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/8/31.
 */
public class ReservationDetailView {

    @ApiModelProperty(value = "预约状态：确认中 = 1,已确认 = 2,已驳回 = 3,已取消 = 4")
    private Integer state;
    @ApiModelProperty(value = "预约状态文本：确认中,已确认,已驳回,已取消")
    private String stateText;
    @ApiModelProperty(value = "在驳回状态和取消状态，返回它们的原因")
    private String cancelReason;
    @ApiModelProperty(value = "这个订单是否已经报单过，true:报单过，false:未报单")
    private Boolean hasOrder;

    @ApiModelProperty(value = "预约金额 e.g. 1000, 这里不要返回万字。")
    private String appointAmount;

    @ApiModelProperty(value = "预计打款时间显示文本e.g. 2017-8-15")
    private String giveAmountDateText;

    @ApiModelProperty(value = "预计打款时间戳")
    private Long giveAmountDate;

    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "客户详情动作")
    private ActionDTO customerAction;

    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "产品的起投金额 e.g. 300万起投")
    private String startAmountAppointText;

    @ApiModelProperty(value = "跳转到产品详情的动作")
    private ActionDTO productAction;

    @ApiModelProperty(value = "备注, 预约时提交的备注")
    private String remarks;

    @ApiModelProperty(value = "预约时间 e.g. 2018-06-09 15:20")
    private String appointDateText;

    @ApiModelProperty(value = "预约编号 e.g. 23423rqewfs")
    private String appointNO;
    @ApiModelProperty(value = "新增报单模式：0：不能报单，1:直接报单，2:先预约再报单")
    private Integer addOrderMode;
    @ApiModelProperty(value = "产品状态：0:未上线，1：上线准备中，2:预售中，3:募集中，4：募集结束，5：存续封闭中，6：清算退出")
    private Integer productStatus;
    @ApiModelProperty(value = "改产品的预约数（已确认的）")
    private Integer productAppointNum;
    @ApiModelProperty(value = "产品状态文本")
    private String productStatusText;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Boolean getHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(Boolean hasOrder) {
        this.hasOrder = hasOrder;
    }

    public String getAppointAmount() {
        return appointAmount;
    }

    public void setAppointAmount(String appointAmount) {
        this.appointAmount = appointAmount;
    }

    public String getGiveAmountDateText() {
        return giveAmountDateText;
    }

    public void setGiveAmountDateText(String giveAmountDateText) {
        this.giveAmountDateText = giveAmountDateText;
    }

    public Long getGiveAmountDate() {
        return giveAmountDate;
    }

    public void setGiveAmountDate(Long giveAmountDate) {
        this.giveAmountDate = giveAmountDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStartAmountAppointText() {
        return startAmountAppointText;
    }

    public void setStartAmountAppointText(String startAmountAppointText) {
        this.startAmountAppointText = startAmountAppointText;
    }

    public ActionDTO getProductAction() {
        return productAction;
    }

    public void setProductAction(ActionDTO productAction) {
        this.productAction = productAction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAppointDateText() {
        return appointDateText;
    }

    public void setAppointDateText(String appointDateText) {
        this.appointDateText = appointDateText;
    }

    public String getAppointNO() {
        return appointNO;
    }

    public void setAppointNO(String appointNO) {
        this.appointNO = appointNO;
    }

    public ActionDTO getCustomerAction() {
        return customerAction;
    }

    public void setCustomerAction(ActionDTO customerAction) {
        this.customerAction = customerAction;
    }


	public Integer getAddOrderMode() {
	
		return addOrderMode;
	}

	public void setAddOrderMode(Integer addOrderMode) {
	
		this.addOrderMode = addOrderMode;
	}

	public Integer getProductStatus() {
	
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
	
		this.productStatus = productStatus;
	}

	public Integer getProductAppointNum() {
	
		return productAppointNum;
	}

	public void setProductAppointNum(Integer productAppointNum) {
	
		this.productAppointNum = productAppointNum;
	}

	public String getProductStatusText() {
	
		return productStatusText;
	}

	public void setProductStatusText(String productStatusText) {
	
		this.productStatusText = productStatusText;
	}
    
}
