package com.yundao.tenant.app.model.product;

import java.util.List;

import com.yundao.tenant.app.dto.product.BaseProductNotice;
import com.yundao.tenant.app.dto.product.BaseProductNoticeAttach;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gjl on 2017/7/12.
 */
public class ProductNoticeModel extends BaseProductNotice {
    
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
    private String noticeTypeName;
    
    @ApiModelProperty("申请人名称")
    private String applyUserRealName;
    
    @ApiModelProperty("是否是申请人")
    private boolean isApplyUser;
    
    @ApiModelProperty("创建人名称")
    private String createUserRealName;
    
    
    public String getCreateUserRealName() {
	
		return createUserRealName;
	}

	public void setCreateUserRealName(String createUserRealName) {
	
		this.createUserRealName = createUserRealName;
	}
    
    public boolean isApplyUser() {
	
		return isApplyUser;
	}

	public void setApplyUser(boolean isApplyUser) {
	
		this.isApplyUser = isApplyUser;
	}

	private List<BaseProductNoticeAttach> baseProductNoticeAttach;

    public String getApplyUserRealName() {
	
		return applyUserRealName;
	}

	public void setApplyUserRealName(String applyUserRealName) {
	
		this.applyUserRealName = applyUserRealName;
	}

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
