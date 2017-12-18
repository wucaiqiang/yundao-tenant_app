
package com.yundao.tenant.app.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Function: Reason: Date: 2017年8月18日 上午11:45:07
 * 
 * @author wucq
 * @version
 */
public class MobileUtils {

	public static String format(String str) {
		if (StringUtils.isNotBlank(str) && str.length() == 11) {
			return str.substring(0, 3) + "-" + str.substring(3, 7) + "-" + str.substring(7);
		}
		return str;
	}
}
