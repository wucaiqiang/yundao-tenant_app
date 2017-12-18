

package com.yundao.tenant.app.view.question;

import java.io.Serializable;
import java.util.Date;

import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.util.DateFormatUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月21日 上午10:49:24 
 * @author   wucq
 * @version   
 */
public class AfpRiskEvalationView implements Serializable{

	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 风险类型 e.g. 保守型
	 */
	private String type;
	/**
	 * 推荐的产品数e.g. 7个推荐产品
	 */
	private String productNum;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 跳转到理财师给客户做的测评结果页
	 */
	private ActionDTO action;
	public String getType() {
	
		return type;
	}
	public void setType(String type) {
	
		this.type = type;
	}
	public String getProductNum() {
	
		return productNum;
	}
	public void setProductNum(String productNum) {
	
		this.productNum = productNum+"个推荐产品";
	}
	public String getCreateDate() {
	    if(createDate !=null){
	    	return DateFormatUtils.format(createDate, "yyyy-MM-dd");
	    }
		return "";
	}
	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}
	public ActionDTO getAction() {
	
		return action;
	}
	public void setAction(ActionDTO action) {
	
		this.action = action;
	}

}

