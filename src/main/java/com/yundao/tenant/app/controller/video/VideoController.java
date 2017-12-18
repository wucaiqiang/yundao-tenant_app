
package com.yundao.tenant.app.controller.video;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.service.video.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年12月8日 下午3:38:57
 * 
 * @author wucq
 * @version
 */
@RestController
@RequestMapping("/video/")
@ResponseBody
@Api("视频管理")
public class VideoController {
	@Autowired
	private VideoService videoService;

	@RequestMapping(value = "get_upload_signature", method = RequestMethod.POST)
	@ApiOperation(value = "请求上传文件签名")
	public Result<Map<String, Object>> getUploadSignature() throws Exception {
		return videoService.getUploadSignature();
	}
}
