
package com.yundao.tenant.app.dto.sale.declaration;


import io.swagger.annotations.ApiModelProperty;

public class DeclarationUpdateBankDto{
    @ApiModelProperty(value = "报单ID")
    private Long id;

    /**
     * 银行卡账号
     */
    @ApiModelProperty(value = "银行卡账号")
    private String account;

    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String bankName;

    /**
     * 银行卡正面
     */
    @ApiModelProperty(value = "银行卡正面")
    private String front;

    /**
     * 银行卡反面
     */
    @ApiModelProperty(value = "银行卡反面")
    private String back;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

