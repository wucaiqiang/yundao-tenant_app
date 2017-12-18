package com.yundao.tenant.app.controller.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.report.FilterConditionDto;
import com.yundao.tenant.app.service.report.declaration.IndexDeclarationReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/report/")
@ResponseBody
@Api("报表")
public class ReportController {
	
	@Autowired
	IndexDeclarationReportService indexDeclarationReportService;
	@RequestMapping(value = "get_index", method = RequestMethod.POST)
	@ApiOperation(value = "获取数据报表首页")
    public Result<ItemListNoPageDTO> getIndexDto() throws BaseException{
		Result<ItemListNoPageDTO>  result = indexDeclarationReportService.getIndexDto();
    	return result;
    }
	
	
	@RequestMapping(value = "get_filter_list", method = RequestMethod.POST)
	@ApiOperation(value = "业绩报表筛选项")
	public Result<Map<String,List<FilterConditionDto>>> getUserReportList()throws BaseException{
		Map<String,List<FilterConditionDto>> map = new HashMap<String,List<FilterConditionDto>>();
		List<FilterConditionDto> list = new ArrayList<FilterConditionDto>();
		// list.add(new FilterConditionDto(1,"自定义"));
		list.add(new FilterConditionDto(2,"本月"));
		list.add(new FilterConditionDto(3,"上一月"));
		list.add(new FilterConditionDto(4,"本季度"));
		list.add(new FilterConditionDto(5,"上一季度"));
		list.add(new FilterConditionDto(6,"本年"));
		list.add(new FilterConditionDto(7,"上一年"));
		map.put("filterList", list);
		return Result.newSuccessResult(map);
	}
}
