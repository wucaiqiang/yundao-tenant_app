
package com.yundao.tenant.app.service.video;

import java.util.List;
import java.util.Map;

import com.yundao.core.code.Result;

/**
 * Function: Reason: Date: 2017年11月3日 下午4:34:11
 * 
 * @author wucq
 * @version
 */
public interface VideoService {
	public Result<Map<String, Object>> getUploadSignature() throws Exception;
}
