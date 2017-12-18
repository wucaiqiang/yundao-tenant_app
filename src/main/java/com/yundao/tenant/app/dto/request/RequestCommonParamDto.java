package com.yundao.tenant.app.dto.request;

import com.yundao.tenant.app.dto.DataDTO;

public class RequestCommonParamDto implements DataDTO {

	private static final long serialVersionUID = 6451655176172785944L;

	private String deviceId;
	private String deviceName;
	private String deviceResolution;
	private String deviceOS;
	private String certificate;
	private String nonce;
	private String userId;

	public RequestCommonParamDto(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public RequestCommonParamDto(String deviceId, String certificate) {
		this(deviceId);
		this.certificate = certificate;
	}
	
	public RequestCommonParamDto(String deviceId, String certificate, String userId) {
		this(deviceId, certificate);
		this.userId = userId;
	}
	
	public RequestCommonParamDto(String deviceId, String deviceName, String deviceResolution, String deviceOS,
			String certificate, String nonce, String userId) {
		this(deviceId, certificate);
		this.deviceName = deviceName;
		this.deviceResolution = deviceResolution;
		this.deviceOS = deviceOS;
		this.nonce = nonce;
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceResolution() {
		return deviceResolution;
	}

	public void setDeviceResolution(String deviceResolution) {
		this.deviceResolution = deviceResolution;
	}

	public String getDeviceOS() {
		return deviceOS;
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
