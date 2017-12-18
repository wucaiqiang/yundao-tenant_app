
package com.yundao.tenant.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;

/**
 * Function: Reason: Date: 2017年8月4日 上午11:24:29
 * 
 * @author wucq
 * @version
 */
public class DateFormatUtils {
	private static Log logger=LogFactory.getLog(DateFormatUtils.class);
	/**
	 * 
	 * format:
	 * 
	 * @author: wucq
	 * @param date
	 * @param partten
	 *            yyyy-MM-dd HH:mm:ss:SSS
	 * @return
	 * @description:
	 */
	public static String format(Date date, String partten) {
		if (date == null || StringUtils.isBlank(partten)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(partten);
		return format.format(date);

	}

	public static Date parse(String source, String partten) {
		if (StringUtils.isBlank(source) || StringUtils.isBlank(partten)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(partten);
		Date date = null;
		try {
			date = format.parse(source);
		} catch (ParseException e) {
			logger.error("时间转换异常，异常信息为：",e);
		}
		return date;

	}
}
