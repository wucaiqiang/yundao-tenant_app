package com.yundao.tenant.app.model.customer.bank;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class CustomerBankModel extends BaseModel implements Serializable {
    /**
	 * 客户id
	 */
    private Long customerId;

    /**
	 * 银行卡账号
	 */
    private String account;

    /**
	 * 开户行
	 */
    private String bankName;

    /**
	 * 银行卡正面
	 */
    private String front;

    /**
	 * 银行卡反面
	 */
    private String back;

    /**
	 * 是否废弃
	 */
    private Integer isDiscard;

    private static final long serialVersionUID = 1L;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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

    public Integer getIsDiscard() {
        return isDiscard;
    }

    public void setIsDiscard(Integer isDiscard) {
        this.isDiscard = isDiscard;
    }
}