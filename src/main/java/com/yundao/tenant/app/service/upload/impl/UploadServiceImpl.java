

package com.yundao.tenant.app.service.upload.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.service.AbstractService;
import com.yundao.core.threadlocal.filter.RequestCommonParams;
import com.yundao.core.utils.HttpUtils;
import com.yundao.core.utils.HttpUtils.UploadFileParams;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.BaseUrl;
import com.yundao.tenant.app.dto.upload.CloudFileDto;
import com.yundao.tenant.app.enums.upload.FileTypeEnum;
import com.yundao.tenant.app.service.upload.UploadService;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月23日 下午3:46:53 
 * @author   wucq
 * @version   
 */
@Service
public class UploadServiceImpl extends AbstractService implements UploadService {
	private Log logger=LogFactory.getLog(UploadServiceImpl.class);
	
	@Override
	public String doUpload(MultipartFile file,Integer type) throws BaseException {
		
		InputStream inputStream=null;
		if (!file.isEmpty()) {
			int pre = (int) System.currentTimeMillis();
			
			Set<String> supportFormat=FileTypeEnum.getEnumFormat(type);
			String fileName=file.getOriginalFilename();
			if(supportFormat !=null && !supportFormat.isEmpty()){
				int index_=fileName.lastIndexOf(".");
				if(!supportFormat.contains(fileName.substring(index_+1))){
					throw new BaseException(CodeConstant.CODE_1300017);
				}
			}
			try {
				inputStream= file.getInputStream();
				
				
				// 调用文件基础服务接口上传文件
				String url = BaseUrl.FILE_UPLOAD_POST;
				List<UploadFileParams> files = new ArrayList<UploadFileParams>();
				UploadFileParams uploadFileParams = new UploadFileParams();
				uploadFileParams.setFileName(file.getOriginalFilename());
				uploadFileParams.setParameterName("file");
				uploadFileParams.setInputStream(inputStream);
				files.add(uploadFileParams);

				Map<String, String> methodParams = new HashMap<String, String>();
				methodParams.put("cloudType", "0");
				methodParams.put("folder", "");
				
				RequestCommonParams requestParams = RequestCommonParams.newDefault(methodParams);
				requestParams.setHeaderParams(com.yundao.tenant.app.util.HttpUtils.getHeaderParams());
				logger.info("文件开始上传 url：" + url + " files：" + files + " requestParams:" + requestParams);
				
				Result<CloudFileDto>  result = JsonUtils.jsonToObject(HttpUtils.postFiles(url, files, requestParams), new BaseTypeReference<Result<CloudFileDto>>(){});
				
				logger.info("上传成功！返回结果：" + result.toString());
				
				int finaltime = (int) System.currentTimeMillis();
				
				logger.info("上传耗时间：" + (finaltime - pre) + "毫秒.");
				
				if(result !=null && result.getResult() !=null){
					return result.getResult().getUrl();
				}
			}
			catch (Exception e) {
				logger.error("文件上传出错", e);
				throw new BaseException(CommonCode.COMMON_0);
			}finally {
				// 关闭流
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("上传文件，关闭文件流出现异常",e);
				}
			}
		}
		else {
			logger.info("文件是空的");
			throw new BaseException(CommonCode.COMMON_0);
		}
		return "";
	}
}

