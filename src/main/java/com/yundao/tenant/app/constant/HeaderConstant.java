package com.yundao.tenant.app.constant;

public interface HeaderConstant extends com.yundao.core.constant.HeaderConstant {

	
	/**
	 * 设备ID
	 */
	String X_REQUEST_DEVICE_ID = "X-Request-DeviceId";
	/**
	 * 设备ticket
	 */
	String X_REQUEST_CERTIFICATE = "X-Request-Certificate";
	/**
	 * app的唯一Id
	 */
	String X_REQUEST_APPID = "X-Request-AppId";
	/**
	 * 租户ID
	 */
	String X_REQUEST_TENANTCODE = "X-Request-TenantCode";
	/**
	 * 设备名称, 例如：ZTE N918S
	 */
	String X_REQUEST_DEVICE_NAME = "X-Request-DeviceName";
	/**
	 * 设备分辨率,宽*高，例如：720*1280
	 */
	String X_REQUEST_DEVICE_RESOLUTION = "X-Request-DeviceResolution";
	/**
	 * 设备操作系统版本，例如：Android 的4.4.4; iPhone的 9.2;
	 */
	String X_REQUEST_DEVICE_OS = "X-Request-DeviceOS";
	/**
	 * 设备操作系统,例如：Android IOS
	 */
	String X_REQUEST_DEVICE_OS_PLATFORM = "X-Request-DeviceOSPlatform";


}
