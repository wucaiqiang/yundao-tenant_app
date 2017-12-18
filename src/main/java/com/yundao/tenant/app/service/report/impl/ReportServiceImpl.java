
package com.yundao.tenant.app.service.report.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.BeginEndDateDto;
import com.yundao.tenant.app.dto.report.AfpReportDto;
import com.yundao.tenant.app.service.report.ReportService;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.report.ReportView;
import com.yundao.tenant.app.view.report.Structure1View;

/**
 * Function: Reason: Date: 2017年9月19日 下午7:49:04
 * 
 * @author wucq
 * @version
 */
@Service
public class ReportServiceImpl extends AbstractService implements ReportService {

	@Override
	public Result<Double> getReservationTotal(Date beginDate,Date endDate) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("beginDate", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		methodParams.put("endDate", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		Result<Double> result = HttpUtils.get(TenantUrl.REPORT_GET_RESERVATION_TOTAL, methodParams,
				new BaseTypeReference<Result<Double>>() {
				});
		return result;

	}

	@Override
	public Result<Double> getDeclarationTotal(Date beginDate,Date endDate) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("beginDate", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		methodParams.put("endDate", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		Result<Double> result = HttpUtils.get(TenantUrl.REPORT_GET_DECLARATION_TOTAL, methodParams,
				new BaseTypeReference<Result<Double>>() {
				});
		return result;

	}

	@Override
	public Result<List<AfpReportDto>> getReservationRank(Date beginDate,Date endDate,Integer limit) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("beginDate", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		methodParams.put("endDate", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		methodParams.put("limit", limit);
		Result<List<AfpReportDto>> result = HttpUtils.get(TenantUrl.REPORT_GET_RESERVATION_RANK, methodParams,
				new BaseTypeReference<Result<List<AfpReportDto>>>() {
				});
		return result;

	}

	@Override
	public Result<List<AfpReportDto>> getDeclarationRank(Date beginDate,Date endDate,Integer limit) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("beginDate", DateFormatUtils.format(beginDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		methodParams.put("endDate", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss:SSS"));
		Result<List<AfpReportDto>> result = HttpUtils.get(TenantUrl.REPORT_GET_DECLARATION_RANK, methodParams,
				new BaseTypeReference<Result<List<AfpReportDto>>>() {
				});
		return result;

	}

}
