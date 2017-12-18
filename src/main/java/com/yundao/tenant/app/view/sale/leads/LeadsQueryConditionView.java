
package com.yundao.tenant.app.view.sale.leads;

import java.io.Serializable;
import java.util.List;

import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月16日 下午4:25:42
 * 
 * @author wucq
 * @version
 */
public class LeadsQueryConditionView implements Serializable {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分类名称：未处理的线索，全部线索
	 */
	private List<NameIdView> operationList;
	/**
	 * 类型集合
	 */
	private List<NameIdView> typeList;
	/**
	 * 渠道集合
	 */
	private List<NameIdView> channelList;
	/**
	 * 客户有效性集合
	 */
	private List<NameIdView> customerValidList;

	public List<NameIdView> getOperationList() {

		return operationList;
	}

	public void setOperationList(List<NameIdView> operationList) {

		this.operationList = operationList;
	}

	public List<NameIdView> getTypeList() {

		return typeList;
	}

	public void setTypeList(List<NameIdView> typeList) {

		this.typeList = typeList;
	}

	public List<NameIdView> getChannelList() {

		return channelList;
	}

	public void setChannelList(List<NameIdView> channelList) {

		this.channelList = channelList;
	}

	public List<NameIdView> getCustomerValidList() {

		return customerValidList;
	}

	public void setCustomerValidList(List<NameIdView> customerValidList) {

		this.customerValidList = customerValidList;
	}

}
