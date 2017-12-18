package com.yundao.tenant.app.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yundao.tenant.app.enums.encrypt.EncryptMethodEnum;

/**
 * 该方法对请求头公共字段的必选、可选的控制
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonField {

	/**
	 * ticket校验
	 *  certificate:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean certificate() default true;
	/**
	 * 租户ID
	 * tenantCode:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean tenantCode() default true;
	/**
	 * 
	 * appId:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean appId() default false;
	/**
	 * 设备ID
	 * deviceId:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean deviceId() default false;
	/**
	 * 请求的标识
	 * requestId:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean requestId() default false;
	/**
	 * 版本校验
	 * version:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean version() default false;
	/**
	 * 链接请求时间
	 * requestTime:
	 * @author: wucq
	 * @return
	 * @description:
	 */
	boolean requestTime() default false;

}
