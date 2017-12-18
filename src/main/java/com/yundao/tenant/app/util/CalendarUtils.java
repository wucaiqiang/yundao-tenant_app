package com.yundao.tenant.app.util;

import java.util.Calendar;
import java.util.Date;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.DateUtils;

/**
 * ClassName:CalendarUtils Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年10月17日 下午8:06:27
 * 
 * @author wucq
 * @version
 */
public class CalendarUtils {
	public static Log logger = LogFactory.getLog(CalendarUtils.class);

	/**
	 * 当前时候取前或后几天，几周，几月的时间的开始时间 getDayBegin:
	 * 
	 * @author: wucq
	 * @param date
	 * @param type
	 * @param operateNum
	 * @return
	 * @description:
	 */
	public static Date getDayBegin(Date date, Integer type, Integer operateNum) {
		// type 0:天，1：周 ，2：月
		// operateNum 负数向前减，正数向后加
		Calendar dateCalenda = Calendar.getInstance();
		dateCalenda.setTime(date);// 设置时间
		if (type == 0) {
			dateCalenda.set(Calendar.DATE, dateCalenda.get(Calendar.DATE) + operateNum);
		}
		if (type == 1) {
			dateCalenda.set(Calendar.WEEK_OF_YEAR, dateCalenda.get(Calendar.WEEK_OF_YEAR) + operateNum);
			dateCalenda.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 取当前周的开始时间，即星期天的开始时间
		}
		if (type == 2) {
			dateCalenda.set(Calendar.MONTH, dateCalenda.get(Calendar.MONTH) + operateNum);
			dateCalenda.set(Calendar.DAY_OF_MONTH, 1);// 取当前月1号时间
		}
		dateCalenda.set(Calendar.HOUR_OF_DAY, dateCalenda.getActualMinimum(Calendar.HOUR_OF_DAY));
		dateCalenda.set(Calendar.MINUTE, dateCalenda.getActualMinimum(Calendar.MINUTE));
		dateCalenda.set(Calendar.SECOND, dateCalenda.getActualMinimum(Calendar.SECOND));
		dateCalenda.set(Calendar.MILLISECOND, dateCalenda.getActualMinimum(Calendar.MILLISECOND));
		return dateCalenda.getTime();
	}

	/**
	 * 当前时候取前或后几天，几周，几月的时间的结束时间 getDayEnd:
	 * 
	 * @author: wucq
	 * @param date
	 * @param type
	 * @param operateNum
	 * @return
	 * @description:
	 */
	public static Date getDayEnd(Date date, Integer type, Integer operateNum) {
		// type 0:天，1：周 ，2：月
		// operateNum 负数向前减，正数向后加
		Calendar dateCalenda = Calendar.getInstance();
		dateCalenda.setTime(date);// 设置时间
		if (type == 0) {
			dateCalenda.set(Calendar.DATE, dateCalenda.get(Calendar.DATE) + operateNum);
		}
		if (type == 1) {
			dateCalenda.set(Calendar.WEEK_OF_YEAR, dateCalenda.get(Calendar.WEEK_OF_YEAR) + operateNum);
			dateCalenda.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);// 取当前周的开始时间，即星期六的结束时间
		}
		if (type == 2) {
			dateCalenda.set(Calendar.MONTH, dateCalenda.get(Calendar.MONTH) + operateNum);
			dateCalenda.set(Calendar.DAY_OF_MONTH, dateCalenda.getActualMaximum(Calendar.DAY_OF_MONTH));// 取当前月最大那天的结束时间
		}
		dateCalenda.set(Calendar.HOUR_OF_DAY, dateCalenda.getActualMaximum(Calendar.HOUR_OF_DAY));
		dateCalenda.set(Calendar.MINUTE, dateCalenda.getActualMaximum(Calendar.MINUTE));
		dateCalenda.set(Calendar.SECOND, dateCalenda.getActualMaximum(Calendar.SECOND));
		dateCalenda.set(Calendar.MILLISECOND, dateCalenda.getActualMaximum(Calendar.MILLISECOND));
		return dateCalenda.getTime();
	}

	/**
	 * 取当前天的开始时间 getDayBegin:
	 * 
	 * @author: wucq
	 * @param date
	 * @return
	 * @description:
	 */
	public static Date getDayBegin(Date date) {
		Calendar dateCalenda = Calendar.getInstance();
		dateCalenda.setTime(date);// 设置时间
		dateCalenda.set(Calendar.HOUR_OF_DAY, dateCalenda.getActualMinimum(Calendar.HOUR_OF_DAY));
		dateCalenda.set(Calendar.MINUTE, dateCalenda.getActualMinimum(Calendar.MINUTE));
		dateCalenda.set(Calendar.SECOND, dateCalenda.getActualMinimum(Calendar.SECOND));
		dateCalenda.set(Calendar.MILLISECOND, dateCalenda.getActualMinimum(Calendar.MILLISECOND));
		return dateCalenda.getTime();
	}

	/**
	 * 取当前天的结束时间 getDayEnd:
	 * 
	 * @author: wucq
	 * @param date
	 * @return
	 * @description:
	 */
	public static Date getDayEnd(Date date) {
		Calendar dateCalenda = Calendar.getInstance();
		dateCalenda.setTime(date);// 设置时间
		dateCalenda.set(Calendar.HOUR_OF_DAY, dateCalenda.getActualMaximum(Calendar.HOUR_OF_DAY));
		dateCalenda.set(Calendar.MINUTE, dateCalenda.getActualMaximum(Calendar.MINUTE));
		dateCalenda.set(Calendar.SECOND, dateCalenda.getActualMaximum(Calendar.SECOND));
		dateCalenda.set(Calendar.MILLISECOND, dateCalenda.getActualMaximum(Calendar.MILLISECOND));
		return dateCalenda.getTime();
	}

	// yyyy-MM-dd HH:mm:ss
	public static String changeDateToText(Date dataDate) {
		try {
			Date dtoData = DateUtils.parse(DateUtils.format(dataDate, DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
			int second = DateUtils.getDistanceTimes(new Date(), dtoData);
			int day = second / 60 / 60 / 24;
			if (day == 0) {
				return DateUtils.format(dataDate, "HH:mm");
			} else if (day == 1) {
				return "昨天 " + DateUtils.format(dataDate, "HH:mm");
			} else {
				return DateUtils.format(dataDate, "MM-dd HH:mm");
			}
		} catch (Exception e) {
			logger.error("时间转换出现异常，异常信息为：",e);
		}
		return "";

	}

	/**
	 * 与今天相差多少天，-1：昨天 ，0：今天，1：明天
	 * 
	 * @param dataDate
	 * @return
	 * @throws Exception
	 */
	public static int longToToday(Date dataDate) throws Exception {
		Date dtoData = DateUtils.parse(DateUtils.format(dataDate, DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
		Date today = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
		int second = DateUtils.getDistanceTimes(dtoData, today);
		int day = second / 60 / 60 / 24;
		return day;
	}

	public static int birthLongToToday(Date birth) throws Exception {
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTime(birth);
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(new Date());
		if (birthCal.get(Calendar.MONTH) != todayCal.get(Calendar.MONTH)) {
			return -31;
		} else {
			int diffe = birthCal.get(Calendar.DATE) - todayCal.get(Calendar.DATE);
			return diffe;
		}
	}
}
