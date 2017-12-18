package com.yundao.tenant.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yundao.core.config.system.SystemFileConfig;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.constant.HeaderConstant;
import com.yundao.core.threadlocal.filter.RequestCommonParams;
import com.yundao.core.utils.HttpUtils;

/**
 * 部署测试类
 *
 * @author wupengfei wupf86@126.com
 */
public class FileTest {

	/***
	 * 部署模型
	 *
	 * @throws Exception
	 */
	@Test
	public void fileHandle() throws Exception {
		Map<String, Object> methodParams = new HashMap<>();

		Map<String, String> header = new HashMap<String, String>();
		header.put(HeaderConstant.HEADER_USER_ID, "1");
		header.put(HeaderConstant.HEADER_TENANT_ID, "1");
		header.put(HeaderConstant.HEADER_REAL_NAME, "admin");

		RequestCommonParams requestParams = RequestCommonParams.newDefault();
		requestParams.setMethodParams(methodParams);
		requestParams.setHeaderParams(header);

		InputStream is = new FileInputStream(
				new File("C:\\Users\\Administrator\\Desktop\\v1.3.0\\微信图片_20171124203033.jpg"));

		HttpUtils.UploadFileParams file = new HttpUtils.UploadFileParams();
		file.setFileName("aaa");
		file.setParameterName("file");
		file.setInputStream(is);

		String url = "http://localhost:8204";
		String result = HttpUtils.postFiles(url + "/card/scaning", file, requestParams);
		System.out.println(result);
	}

}
