
package com.yundao.tenant.app.service.share;

import com.yundao.core.exception.BaseException;

/**
 * Function: Reason: Date: 2017年9月29日 下午5:52:00
 * 
 * @author wucq
 * @version
 */
public interface ShareService {
	/**
	 * 获取当前租户C端分享域名地址
	 * getCCShareDomainUrl:
	 * 
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public String getCCShareDomainUrl() throws BaseException;
}
