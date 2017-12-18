

package com.yundao.tenant.app.service.upload;

import org.springframework.web.multipart.MultipartFile;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月23日 下午3:46:09 
 * @author   wucq
 * @version   
 */
public interface UploadService {
  public String doUpload(MultipartFile file,Integer type)throws BaseException;
}

