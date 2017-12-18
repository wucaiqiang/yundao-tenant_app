package com.yundao.tenant.app.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化； 这个用到{@link DemoUtils}
 */
public class AESUtils {
	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	public static String sKey = "smkldpsodosldaaa";
	public static String ivParameter = "0392039203920300";
	private static AESUtils instance = null;

	public static AESUtils getInstance() {
		if (instance == null)
			instance = new AESUtils();
		return instance;
	}

	// 加密
	public static String encrypt(String sSrc) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return Base64.encode(encrypted);// 此处使用BASE64做转码。
	}

	// 解密
	public static String decrypt(String sSrc) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 =Base64.decode(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}
	/*public static void tttt(String str)throws Exception{
		byte[] encrypted1 =Base64.decode(str);// 先用base64解密
		String originalString = new String(encrypted1, "utf-8");
		System.out.println(originalString);
	}

	@Test
	public void ttest()throws Exception{
		AESUtils.tttt("aGVsbG8=");
	}
	*/
	/*@Test
	public void test() throws Exception {
		System.out.println(AESUtils.decrypt(
				"GlPM89BCkiB12Ydp6/ozlaCoKlw8LP+ZvpJ0wJTtFn9eMl+byMelkXoknlc9i0WSbpa3FofibDjp08CU+vf+unL04Qo2YBMGuZkoeVS9pGiqYfMdS016IQJVV1OS+g0RJ5mKyPQj9rmwtuh7ls19Uw=="));
	}*/
}