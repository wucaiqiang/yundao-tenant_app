
package com.yundao.tenant.app.view.sale.leads;


import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.view.NameValueView;

/**
 * Function: Reason: Date: 2017年8月4日 上午10:02:39
 * 
 * @author wucq
 * @version
 */
public class Structure3018View implements DataDTO {
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 线索Id
	 */
	private String lineId;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 类型：e.g. 注册
	 */
	private String type;
	/**
	 * 客户编号
	 */
	private String customerNo;
	/**
	 * 客户ID
	 */
	private String customerId;
	/**
	 *  渠道 e.g. 服务号
	 */
	private String channel;
	/**
	 * 理财师名字 e.g. 大帅
	 */
	private String assetManagerName;
	/**
	 *内容 e.g. 预约理财师
	 */
	private String content;
	/**
	 * 是否可以分配回访
	 */
	private boolean distributionCallbackOperate;
	/**
	 * 是否可以分配给理财师
	 */
	private boolean distributionAssetManagerOperate;
	/**
	 * 查看回访记录
	 */
	private boolean viewCallbackOperate;
	/**
	 * 回访记录内容  <有就有，没有就没有>
	 */
	private List<NameValueView> contentList;
	public String getLineId() {
	
		return lineId;
	}
	public void setLineId(String lineId) {
	
		this.lineId = lineId;
	}
	public String getName() {
	
		return name;
	}
	public void setName(String name) {
	
		this.name = name;
	}
	public String getType() {
	
		return type;
	}
	public void setType(String type) {
	
		this.type = type;
	}
	public String getCustomerNo() {
	
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
	
		this.customerNo = customerNo;
	}
	public String getChannel() {
	
		return channel;
	}
	public void setChannel(String channel) {
	
		this.channel = channel;
	}
	public String getAssetManagerName() {
	
		return assetManagerName;
	}
	public void setAssetManagerName(String assetManagerName) {
	
		this.assetManagerName = assetManagerName;
	}
	public String getContent() {
	
		return content;
	}
	public void setContent(String content) {
	
		this.content = content;
	}
	public boolean isDistributionCallbackOperate() {
	
		return distributionCallbackOperate;
	}
	public void setDistributionCallbackOperate(boolean distributionCallbackOperate) {
	
		this.distributionCallbackOperate = distributionCallbackOperate;
	}
	public boolean isDistributionAssetManagerOperate() {
	
		return distributionAssetManagerOperate;
	}
	public void setDistributionAssetManagerOperate(boolean distributionAssetManagerOperate) {
	
		this.distributionAssetManagerOperate = distributionAssetManagerOperate;
	}
	public boolean isViewCallbackOperate() {
	
		return viewCallbackOperate;
	}
	public void setViewCallbackOperate(boolean viewCallbackOperate) {
	
		this.viewCallbackOperate = viewCallbackOperate;
	}
	public List<NameValueView> getContentList() {
	
		return contentList;
	}
	public void setContentList(List<NameValueView> contentList) {
	
		this.contentList = contentList;
	}
	public String getCustomerId() {
	
		return customerId;
	}
	public void setCustomerId(String customerId) {
	
		this.customerId = customerId;
	}
	
	
}
