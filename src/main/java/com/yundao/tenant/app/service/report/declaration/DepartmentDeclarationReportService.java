

package com.yundao.tenant.app.service.report.declaration;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.report.declaration.DepartmentReportReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月2日 上午9:07:19 
 * @author   欧阳利
 * @version   
 */
public interface DepartmentDeclarationReportService {
	
	 /**
	  * 获取部门业绩报表
	  * getDepartmentListDto:
	  * @author: 欧阳利
	  * @return
	  * @throws BaseException
	  * @description:
	  */
	 public Result<ItemListNoPageDTO> getDepartmentListDto(DepartmentReportReqDto reqDto)throws BaseException;
}

