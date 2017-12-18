package com.yundao.tenant.app.model.customer.investtype;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class CustomerInvestTypeModel extends BaseModel implements Serializable {
    /**
	 * 客户id
	 */
    private Long customerId;

    /**
	 * 投资偏好
	 */
    private Long productTypeId;

    /**
	 * 排序
	 */
    private Integer seqencing;

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

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getSeqencing() {
        return seqencing;
    }

    public void setSeqencing(Integer seqencing) {
        this.seqencing = seqencing;
    }

    public Integer getIsDiscard() {
        return isDiscard;
    }

    public void setIsDiscard(Integer isDiscard) {
        this.isDiscard = isDiscard;
    }
}