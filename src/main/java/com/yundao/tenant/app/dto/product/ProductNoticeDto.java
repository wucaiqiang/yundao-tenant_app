package com.yundao.tenant.app.dto.product;

import java.util.List;

/**
 * Created by gjl on 2017/7/12.
 */
public class ProductNoticeDto extends BaseProductNotice {
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
    private String noticeTypeName;
    private List<BaseProductNoticeAttach> baseProductNoticeAttach;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNoticeTypeName() {
        return noticeTypeName;
    }

    public void setNoticeTypeName(String noticeTypeName) {
        this.noticeTypeName = noticeTypeName;
    }

    public List<BaseProductNoticeAttach> getBaseProductNoticeAttach() {
        return baseProductNoticeAttach;
    }

    public void setBaseProductNoticeAttach(List<BaseProductNoticeAttach> baseProductNoticeAttach) {
        this.baseProductNoticeAttach = baseProductNoticeAttach;
    }
}
