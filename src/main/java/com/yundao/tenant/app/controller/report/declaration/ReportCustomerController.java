

package com.yundao.tenant.app.controller.report.declaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.report.customer.DepartAddCustReportReqDto;
import com.yundao.tenant.app.dto.report.customer.DepartmentAndUserDetailReqDto;
import com.yundao.tenant.app.dto.report.customer.UserAddCustomerRankReqDto;
import com.yundao.tenant.app.enums.report.ReportCustomGroupEnum;
import com.yundao.tenant.app.enums.report.ReportFilterTypeEnum;
import com.yundao.tenant.app.service.report.customer.DepartUserAddCustomerReportService;
import com.yundao.tenant.app.service.report.customer.DepartmentCustomerReportService;
import com.yundao.tenant.app.service.report.customer.UserAddCustomerRankService;

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
@RequestMapping(value = "/report/customer/")
@ResponseBody
@Api("报表添加客户业绩")
public class ReportCustomerController {
	
	@Autowired
	DepartmentCustomerReportService departmentCustomerReportService;
	@Autowired
	DepartUserAddCustomerReportService departUserAddCustomerReportService;
	@Autowired
	UserAddCustomerRankService userAddCustomerRankService;
	/**
	 * 查询部门新增客户排名和报表
	 */
	@RequestMapping(value = "get_department", method = RequestMethod.POST)
	@ApiOperation(value = "获取部门报单业绩")
    public Result<ItemListNoPageDTO> getDepartmentAddCustomerReport(@ModelAttribute DepartAddCustReportReqDto reqDto)throws BaseException{
        // 默认是本月度
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
    	return departmentCustomerReportService.getDepartmentListDto(reqDto);
    }
    

	
	/**
	 * 查询员新增客户的排名
	 */
	@RequestMapping(value = "get_user_rank_page", method = RequestMethod.POST)
	@ApiOperation(value = "获取用户新增客户(全部用户, 部门用户, 我的)")
    public Result<ItemListDTO> getUserAddCustomerRank(@ModelAttribute UserAddCustomerRankReqDto reqDto)throws BaseException{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		return userAddCustomerRankService.getUserRankPage(reqDto);
    }
	
	
	/**
	 * 查询员工或者部门新增客户
	 * 报表走势和客户列表
	 */
	@RequestMapping(value = "get_department_or_user_detail", method = RequestMethod.POST)
	@ApiOperation(value = "获取部门新增客户业绩详情")
    public Result<ItemListDTO> getDepartmentAndUserAddCustomerDetail(@ModelAttribute DepartmentAndUserDetailReqDto reqDto)throws Exception{
		if(reqDto.getFilterType() == null){
			reqDto.setFilterType(ReportFilterTypeEnum.MONTH.getValue());
		}
		if(reqDto.getFilterType() == ReportFilterTypeEnum.CUSTOM.getValue()){
			if(BooleanUtils.isEmpty(reqDto.getStatType())){
				reqDto.setStatType(ReportCustomGroupEnum.MONTH.getValue());
			}
		}
		return departUserAddCustomerReportService.getDepartmentUserDetailDto(reqDto);
    }
}

