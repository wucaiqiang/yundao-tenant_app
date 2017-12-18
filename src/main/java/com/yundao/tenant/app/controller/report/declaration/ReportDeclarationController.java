

package com.yundao.tenant.app.controller.report.declaration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.report.UserDeclarationRankReqDto;
import com.yundao.tenant.app.dto.report.declaration.DepartUserDeclarationReportReqDto;
import com.yundao.tenant.app.dto.report.declaration.DepartmentReportReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.CustomerDeclarationConditionDto;
import com.yundao.tenant.app.dto.report.declaration.customer.CustomerType;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationListReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationRankReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationUserReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationUserDto;
import com.yundao.tenant.app.enums.report.ReportCustomGroupEnum;
import com.yundao.tenant.app.enums.report.ReportFilterTypeEnum;
import com.yundao.tenant.app.service.report.declaration.CustomerDeclarationRankService;
import com.yundao.tenant.app.service.report.declaration.DepartUserDeclarationReportService;
import com.yundao.tenant.app.service.report.declaration.DepartmentDeclarationReportService;
import com.yundao.tenant.app.service.report.declaration.UserDeclarationRankService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月1日 上午10:15:54 
 * @author   欧阳利
 * @version   
 */

@RestController
@RequestMapping(value = "/report/declaration/")
@ResponseBody
@Api("报表报单业绩")
public class ReportDeclarationController {

	@Autowired
	DepartmentDeclarationReportService departmentDeclarationReportService;
	@Autowired
	DepartUserDeclarationReportService departUserDeclarationReportService;
	@Autowired
	UserDeclarationRankService userDeclarationRankService;
	@Autowired
	CustomerDeclarationRankService customerDeclarationRankService;
	
	@RequestMapping(value = "get_department", method = RequestMethod.POST)
	@ApiOperation(value = "获取部门报单业绩")
	public Result<ItemListNoPageDTO> getDepartmentReportDtos(@ModelAttribute DepartmentReportReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue()+"");
		}
		Result<ItemListNoPageDTO>  result = departmentDeclarationReportService.getDepartmentListDto(reqDto);
    	return result;
	}
	
	
	@RequestMapping(value = "get_department_user_detail", method = RequestMethod.POST)
	@ApiOperation(value = "部门（员工）业绩详情")
	public Result<ItemListDTO> getDepartmentUserDetailDto(@ModelAttribute DepartUserDeclarationReportReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		if(reqDto.getFilterType() == ReportFilterTypeEnum.CUSTOM.getValue()){
			if(BooleanUtils.isEmpty(reqDto.getStatType())){
				reqDto.setStatType(ReportCustomGroupEnum.MONTH.getValue());
			}
		}
		
		return departUserDeclarationReportService.getDepartmentUserDetailDto(reqDto);
	}
	
	@RequestMapping(value = "get_user_rank_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取用户报单排名列表 分页")
	public Result<ItemListDTO> getUserRankPage(@ModelAttribute UserDeclarationRankReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		return userDeclarationRankService.getUserRankPage(reqDto);
	}
	
	@RequestMapping(value = "get_customer_filter", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户报单的过滤条件")
	public Result<CustomerDeclarationConditionDto> getCustomerDeclarationFilterDto(@ModelAttribute DeclarationUserReqDto reqDto)throws BaseException{
		List<CustomerType> customerTypeList = new ArrayList<CustomerType>();
		customerTypeList.add(new CustomerType(0,"全部"));
		customerTypeList.add(new CustomerType(1,"首次成单客户"));
		customerTypeList.add(new CustomerType(2,"多次成单客户"));
		
		CustomerDeclarationConditionDto dto = new CustomerDeclarationConditionDto();
		dto.setCustomerTypeList(customerTypeList);
		
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}
		Result<List<DeclarationUserDto> > result = HttpUtils.get(
				TenantUrl.DECLARATION_GET_USER, map,
				new BaseTypeReference<Result<List<DeclarationUserDto>>>() {
				});
		dto.setFinancialPlannerList(result.getResult());
		return Result.newSuccessResult(dto);
	}
	
	
	@RequestMapping(value = "get_customer_rank_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户报单排名信息")
	public Result<ItemListDTO> getCustomerRankPage(@ModelAttribute DeclarationRankReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		return customerDeclarationRankService.getCustomerRankPage(reqDto);
	}
	
	
	@RequestMapping(value = "get_customer_list_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取客户的报单列表")
	public Result<ItemListDTO> getCustomerListPage(@ModelAttribute DeclarationListReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		return customerDeclarationRankService.getCustomerListPage(reqDto);
	}
	
}

