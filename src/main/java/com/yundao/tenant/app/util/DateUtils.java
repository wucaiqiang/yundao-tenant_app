package com.yundao.tenant.app.util;

import com.yundao.core.utils.BooleanUtils;

import java.util.Date;

/**
 * 日期工具类
 *
 * @author jan
 * @create 2017-08-08 PM8:22
 **/
public class DateUtils {

    /**
     * 将字符串格式的日期，转换为精确到分钟的格式：YYYY_MM_DD_HH_MM
     *
     * @param strDate 日期
     */
    public static String formatExactMinute(String strDate) {
        return fetchDate(strDate, ":");
    }

    /**
     * 将日期，转换为精确到分钟的格式：YYYY_MM_DD_HH_MM
     *
     * @param date 日期
     */
    public static String formatExactMinute(Date date) {
        if (date == null)
            return null;
        return com.yundao.core.utils.DateUtils.format(date, com.yundao.core.utils.DateUtils.YYYY_MM_DD_HH_MM);
    }

    /**
     * 将字符串格式的日期，转换为精确到日的格式：YYYY_MM_DD
     *
     * @param strDate 日期
     */
    public static String formatExactDay(String strDate) {
        return fetchDate(strDate, " ");
    }

    /**
     * 将日期，转换为精确到日的格式：YYYY_MM_DD
     *
     * @param date 日期
     */
    public static String formatExactDay(Date date) {
        if (date == null)
            return null;
        return com.yundao.core.utils.DateUtils.format(date, com.yundao.core.utils.DateUtils.YYYY_MM_DD);
    }

    /**
     * 将YYYY_MM_DD 格式日期 加上23:59:59
     *
     * @param strDate 日期
     */
    public static String toEndDate(String strDate) {
        if (BooleanUtils.isEmpty(strDate))
            return strDate;
        return strDate.trim() + " " + com.yundao.core.utils.DateUtils.DAY_END;
    }

    private static String fetchDate(String strDate, String splitSymbol) {
        if (BooleanUtils.isEmpty(strDate))
            return strDate;
        strDate = strDate.trim();
        int index = strDate.lastIndexOf(splitSymbol);
        return strDate.substring(0, index);
    }
}
