
package com.yundao.tenant.app.view.product;

import java.util.Date;
import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;

/**
 * Function: Reason: Date: 2017年8月4日 下午2:37:20
 * 
 * @author wucq
 * @version
 */
public class Structure1014View implements DataDTO {

	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 产品公告名称
	 */
	private String noticeName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品公告Id
	 */
	private String noticeId;
	/**
	 * 产品公告类型 e.g. 周报
	 */
	private String noticeTypeText;
	/**
	 * 创建人：白居易
	 */
	private String creator;
	/**
	 * 创建日期：2017-8-20
	 */
	private Date createTime;
	/**
	 * 3附件
	 */
	private String attachNum;
	/**
	 * 未发布 ，未发布 申请发布被驳回
	 */
	private String stateContent;
	/**
	 * 如果有驳回和取消原因则可点击查看原因
	 */
	private String reason;
	/**
	 * 预计发布时间：2017-8-20 18:00 发布成功显示发布日期 e.g.发布日期：2017-8-20
	 */
	private String content;
	/**
	 * 操作类型 ：0：不能进行操作 ，1：申请发布，2：取消申请发布，3，重新申请发布
	 */
	private List<OperateView> operateList;

	public String getNoticeName() {

		return noticeName;
	}

	public void setNoticeName(String noticeName) {

		this.noticeName = noticeName;
	}

	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getNoticeId() {

		return noticeId;
	}

	public void setNoticeId(String noticeId) {

		this.noticeId = noticeId;
	}

	public String getNoticeTypeText() {

		return noticeTypeText;
	}

	public void setNoticeTypeText(String noticeTypeText) {

		this.noticeTypeText = noticeTypeText;
	}

	public String getCreator() {

		return creator;
	}

	public void setCreator(String creator) {

		this.creator = creator;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}

	public String getAttachNum() {

		return attachNum;
	}

	public void setAttachNum(String attachNum) {

		this.attachNum = attachNum;
	}

	public String getStateContent() {

		return stateContent;
	}

	public void setStateContent(String stateContent) {

		this.stateContent = stateContent;
	}

	public String getReason() {

		return reason;
	}

	public void setReason(String reason) {

		this.reason = reason;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public List<OperateView> getOperateList() {
	
		return operateList;
	}

	public void setOperateList(List<OperateView> operateList) {
	
		this.operateList = operateList;
	}

}
