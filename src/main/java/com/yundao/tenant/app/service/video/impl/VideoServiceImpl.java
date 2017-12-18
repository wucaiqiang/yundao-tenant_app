
package com.yundao.tenant.app.service.video.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.service.video.VideoService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年11月3日 下午4:34:31
 * 
 * @author wucq
 * @version
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Override
	public Result<Map<String, Object>> getUploadSignature() throws Exception {
		Map<String, Object> retMap = new HashMap<>();
		Result<String> result = HttpUtils.get(TenantUrl.VIDEO_GET_UPLOAD_SIGNATURE, null,
				new BaseTypeReference<Result<String>>() {
				});
		if (!result.getSuccess() || StringUtils.isBlank(result.getResult())) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		retMap.put("signature", result.getResult());
		return Result.newSuccessResult(retMap);

	}

}
