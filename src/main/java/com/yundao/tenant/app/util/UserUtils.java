package com.yundao.tenant.app.util;
/**
 * Project Name:a-crm-ms
 * File Name:UserUtils.java
 * Package Name:com.zcmall.crmms.utils
 * Date:2016年10月22日下午5:42:59
 *
*/

import com.yundao.tenant.app.dto.UserBaseData;

/**
 * ClassName:UserUtils Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年10月22日 下午5:42:59
 * 
 * @author wucq
 * @version
 */
public class UserUtils {
	/**
	 * 获取当前登录用户信息 getCurrentUser:
	 * 
	 * @author: wucq
	 * @return
	 * @description:
	 */
	public static UserBaseData getCurrentUser() {
		UserBaseData userBaseData = ThreadContextUtils.getUserBaseData();// 保存线程数据
		return userBaseData;
	}

	public static Long getUserId() {
		UserBaseData userBaseData = ThreadContextUtils.getUserBaseData();// 保存线程数据
		if (userBaseData != null) {
			return userBaseData.getHeaderUserId();
		}
		return null;
	}
}
