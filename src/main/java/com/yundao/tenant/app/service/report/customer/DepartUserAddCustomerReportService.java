

package com.yundao.tenant.app.service.report.customer;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.report.customer.DepartmentAndUserDetailReqDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月4日 上午9:04:58 
 * @author   欧阳利
 * @version   
 */
public interface DepartUserAddCustomerReportService {

	/**
	 * 部门/用户报表业绩详情
	 * getDepartmentUserDetailDto:
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<ItemListDTO> getDepartmentUserDetailDto(@ModelAttribute DepartmentAndUserDetailReqDto reqDto)throws BaseException;
}

