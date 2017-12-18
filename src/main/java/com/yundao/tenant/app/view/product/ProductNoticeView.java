
package com.yundao.tenant.app.view.product;

import java.util.List;

import com.yundao.tenant.app.dto.DataDTO;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ShareInfo;

/**
 * Function: Reason: Date: 2017年8月8日 上午10:51:04
 * 
 * @author wucq
 * @version
 */
public class ProductNoticeView implements DataDTO {
	
	 /**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = 1L;
	private String createDate;
	private String content;
	private String noticeType;
	private String title;
	private List<AttachmentView> attachList;

	protected ActionDTO productDetailAction;
	private ShareInfo shareInfo;

	public String getCreateDate() {

		return createDate;
	}

	public void setCreateDate(String createDate) {

		this.createDate = createDate;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public String getNoticeType() {

		return noticeType;
	}

	public void setNoticeType(String noticeType) {

		this.noticeType = noticeType;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public List<AttachmentView> getAttachList() {

		return attachList;
	}

	public void setAttachList(List<AttachmentView> attachList) {

		this.attachList = attachList;
	}

	public ActionDTO getProductDetailAction() {
	
		return productDetailAction;
	}

	public void setProductDetailAction(ActionDTO productDetailAction) {
	
		this.productDetailAction = productDetailAction;
	}

	public ShareInfo getShareInfo() {
	
		return shareInfo;
	}

	public void setShareInfo(ShareInfo shareInfo) {
	
		this.shareInfo = shareInfo;
	}

}
