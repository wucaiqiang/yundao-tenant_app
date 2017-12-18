
package com.yundao.tenant.app.controller.upload;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.enums.upload.FileTypeEnum;
import com.yundao.tenant.app.service.upload.UploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月23日 下午3:39:44
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/upload")
@ResponseBody
@Api("上传文件")
public class UploadController {
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	@ApiOperation("图片")
	public Result<Map<String, Object>> image(MultipartFile file) throws BaseException {
		String url=uploadService.doUpload(file, FileTypeEnum.IMAGE.getType());
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("url", url);
		return Result.newSuccessResult(reMap);
	}
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	@ApiOperation("文件")
	public Result<Map<String, Object>> file(MultipartFile file) throws BaseException {
		String url=uploadService.doUpload(file, FileTypeEnum.FILE.getType());
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("url", url);
		return Result.newSuccessResult(reMap);
	}
}
