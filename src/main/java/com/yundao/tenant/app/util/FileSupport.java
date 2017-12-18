

package com.yundao.tenant.app.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月8日 下午4:40:30 
 * @author   wucq
 * @version   
 */
public class FileSupport {
  //手机端所支持的文件类型
  public static Map<String,String> supportFile=new HashMap<>();
  static{
	  supportFile.put("pdf", "PDF");
	  supportFile.put("jpg", "JPG");
	  supportFile.put("jpeg", "JPEG");
	  supportFile.put("png", "PNG");
//	  supportFile.put("doc", "DOC");
//	  supportFile.put("docx", "DOCX");
//	  supportFile.put("xlsx", "XLSX");
//	  supportFile.put("xls", "XLS");
//	  supportFile.put("txt", "TXT");
  }
  public static String getFileFormat(String suffix){
	  if(StringUtils.isBlank(suffix)){
		  return "";
	  }
	  int index_=suffix.lastIndexOf(".");
	  if(index_ !=-1){
		  suffix=suffix.substring(index_+1);
	  }
	  
	  suffix=suffix.toLowerCase();//转小写
	  
	  if(!supportFile.containsKey(suffix)){
		  return "";
	  }
	  return supportFile.get(suffix);
	  
  }
}

