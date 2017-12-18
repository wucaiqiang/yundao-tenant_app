

package com.yundao.tenant.app.service.report;

import java.util.Date;
import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.report.AfpReportDto;
import com.yundao.tenant.app.view.report.Structure1View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月19日 下午7:48:30 
 * @author   wucq
 * @version   
 */
public interface ReportService {
	public Result<Double> getReservationTotal(Date beginDate,Date endDate) throws BaseException ;
	public Result<Double> getDeclarationTotal(Date beginDate,Date endDate) throws BaseException ;
	public Result<List<AfpReportDto>> getReservationRank(Date beginDate,Date endDate,Integer limit) throws BaseException ;
	public Result<List<AfpReportDto>> getDeclarationRank(Date beginDate,Date endDate,Integer limit) throws BaseException ;
}

