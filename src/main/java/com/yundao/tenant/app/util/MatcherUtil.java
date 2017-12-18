package com.yundao.tenant.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MatcherUtil {

	public static boolean matcher(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		// 查找字符串中是否有匹配正则表达式的字符/字符串
		return matcher.find();
	}

	/**
	 * 判断double是否是整数
	 * @param obj
	 * @return
	 */
	public static boolean isIntegerForDouble(double obj) {
		double eps = 1e-10;  // 精度范围
		return obj-Math.floor(obj) < eps;
	}

}
