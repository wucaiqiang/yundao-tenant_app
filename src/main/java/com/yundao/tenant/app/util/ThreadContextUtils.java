package com.yundao.tenant.app.util;

import com.yundao.tenant.app.dto.UserBaseData;

/**
 * 本地线程保持者
 * 
 * @author wupengfei wupf86@126.com
 * 
 */
public abstract class ThreadContextUtils {
	private static final ThreadLocal<UserBaseData> THREAD_CURRENT_USER = new ThreadLocal<UserBaseData>() {
		@Override
		public UserBaseData initialValue() {
			return new UserBaseData();
		}
	};

	/**
	 * 获取本地线程上下文
	 * 
	 * @return
	 */
	public static UserBaseData getUserBaseData() {
		UserBaseData context = THREAD_CURRENT_USER.get();
		if (context == null) {
			context = new UserBaseData();
			THREAD_CURRENT_USER.set(context);
		}
		return context;
	}

	/**
	 * 清空本地线程上下文
	 * 
	 */
	public static void remove() {
		UserBaseData userBaseData = getUserBaseData();
		userBaseData=null;
		THREAD_CURRENT_USER.set(null);
	}

}