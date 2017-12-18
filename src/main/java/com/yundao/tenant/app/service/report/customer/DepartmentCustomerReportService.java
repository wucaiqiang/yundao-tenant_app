

package com.yundao.tenant.app.service.report.customer;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.report.customer.DepartAddCustReportReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月2日 上午9:07:19 
 * @author   欧阳利
 * @version   
 */
public interface DepartmentCustomerReportService {
	
	 /**
	  * 获取部门业绩报表
	  * getDepartmentListDto:
	  * @author: 欧阳利
	  * @return
	  * @throws BaseException
	  * @description:
	  */
	 public Result<ItemListNoPageDTO> getDepartmentListDto(DepartAddCustReportReqDto reqDto)throws BaseException;
}

