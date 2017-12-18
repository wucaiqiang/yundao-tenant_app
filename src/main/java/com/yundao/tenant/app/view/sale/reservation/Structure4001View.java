package com.yundao.tenant.app.view.sale.reservation;

import com.yundao.tenant.app.dto.DataDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by lan on 2017/9/4.
 */
public class Structure4001View implements DataDTO {

    @ApiModelProperty("预约Id")
    private String appointId;
    @ApiModelProperty("客户名称")
    private String customerName;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("预约金额 e.g. 300万")
    private String appintAmountText;
    @ApiModelProperty("当前预约状态 e.g. 已撤销, 已确认, 确认中,已驳回,已取消中的其中一个")
    private String stateText;
    @ApiModelProperty("预约的金额 e.g. 1000万")
    private String leftValue;
    @ApiModelProperty("预约金额")
    private String leftName;
    @ApiModelProperty("预计打款的日期：2018-06-09")
    private String rightValue;
    @ApiModelProperty("预计打款日期")
    private String rightName;
    @ApiModelProperty("预约时间 2018-06-09 15:20")
    private String appointmentDateText;

    public String getAppointId() {
        return appointId;
    }

    public void setAppointId(String appointId) {
        this.appointId = appointId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAppintAmountText() {
        return appintAmountText;
    }

    public void setAppintAmountText(String appintAmountText) {
        this.appintAmountText = appintAmountText;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getAppointmentDateText() {
        return appointmentDateText;
    }

    public void setAppointmentDateText(String appointmentDateText) {
        this.appointmentDateText = appointmentDateText;
    }
}
