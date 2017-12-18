
package com.yundao.tenant.app.util;


import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;

/**
 * Function: Reason: Date: 2017年11月17日 上午11:23:01
 * 
 * @author wucq
 * @version
 */
public class VideoDurationUtils {

	private static Log logger = LogFactory.getLog(VideoDurationUtils.class);
	private static String secondFormat = "00:%s";
	private static String minuteFormat = "%s:%s";
	private static String hourFormat = "%s:%s:%s";

	/**
	 * 
	 * changeToText:
	 * 
	 * @author: wucq
	 * @param duration
	 *            单位为秒
	 * @description:
	 */
	public static String changeToText(int duration) {
		String result = "";
		try {
			if (duration < 60) {
				result = String.format(secondFormat, zeroPadding(duration));
			} else if (duration < 3600) {
				int minute = duration / 60;
				int second = duration - (minute * 60);
				result = String.format(minuteFormat, zeroPadding(minute), zeroPadding(second));
			} else {
				int hour = duration / 3600;
				int second = (duration - (hour * 3600)) / 60;
				int minute = (duration - (hour * 3600) - (second * 60));
				result = String.format(hourFormat, zeroPadding(hour), zeroPadding(second), zeroPadding(minute));
			}
		} catch (Exception e) {
			logger.error("视频播放时长转换出现异常，异常信息为：", e);
		}
		return result;
	}

	public static String zeroPadding(int time) {
		if (time < 0) {
			return "";
		} else if (time >= 10) {
			return String.valueOf(time);
		} else {
			return "0" + time;
		}
	}

}
