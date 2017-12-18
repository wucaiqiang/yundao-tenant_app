

package com.yundao.tenant.app.service.report.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.report.customer.CustomerReportDto;
import com.yundao.tenant.app.dto.report.customer.DepartmentAndUserDetailReqDto;
import com.yundao.tenant.app.dto.report.customer.DepartmentAndUserDetailResDto;
import com.yundao.tenant.app.dto.report.customer.DepartmentUserAddCustomerReportDto;
import com.yundao.tenant.app.dto.report.declaration.ReportDto;
import com.yundao.tenant.app.dto.report.report.Value;
import com.yundao.tenant.app.dto.report.report.YConfigeData;
import com.yundao.tenant.app.service.report.customer.DepartUserAddCustomerReportService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.customer.Structure3020View;
import com.yundao.tenant.app.view.report.declaration.Structure7001View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年12月4日 下午4:34:45 
 * @author   欧阳利
 * @version   
 */
@Service
public class DepartUserAddCustomerReportServiceImpl implements DepartUserAddCustomerReportService {



	@Override
	public Result<ItemListDTO> getDepartmentUserDetailDto(DepartmentAndUserDetailReqDto reqDto)
			throws BaseException {
		if(reqDto.getUserId() == null && reqDto.getDepartmentId() != null){
			return getDepartmentDetailDto(reqDto);
		}
		
		if(reqDto.getUserId() != null && reqDto.getDepartmentId() == null){
			return getDepartmentDetailDto(reqDto);
		}
		return Result.newFailureResult(CodeConstant.CODE_1300039);
	}
	
	/**
	 * 查询部门业绩详情
	 * getDepartmentDetailDto:
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @description:
	 */
	private Result<ItemListDTO> getDepartmentDetailDto(DepartmentAndUserDetailReqDto reqDto)throws BaseException{
        // 获取数据
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}
		Result<DepartmentAndUserDetailResDto> result = HttpUtils.get(TenantUrl.REPORT_GET_DEPARTMENT_OR_USER_DETAIL_ADD_CUSTOMER, 
				map,new BaseTypeReference<Result<DepartmentAndUserDetailResDto>>() {});
		
		
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		if(!result.getSuccess()){
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		DepartmentAndUserDetailResDto dto = result.getResult();
		// 部门或者员工业走势
		if(reqDto.getCurrentPage() == 1){
			setReport(reqDto,dto.getReportDtos(),dto,viewItems);
		}
		
		viewItems.add(new ViewItem(2001, new DividingLineView()));
		
		//分页查询员工或者部门业绩列表
		setList(reqDto,dto.getCustomerDtos(),viewItems );
		ItemListDTO itemListDTO = new ItemListDTO(viewItems);
		itemListDTO.setPaginaton(dto.getCustomerDtos());
		
		return Result.newSuccessResult(itemListDTO);
	}
	
	
	private void setReport(DepartmentAndUserDetailReqDto reqDto,List<DepartmentUserAddCustomerReportDto> reportDtos,DepartmentAndUserDetailResDto dto,List<ViewItem> viewItems){
		 Structure7001View structure7001View = new Structure7001View();
		 // 如果所有金额都为0 显示没有数据
		 boolean isAllZero = true;
		 if(!BooleanUtils.isEmpty(reportDtos)){
			 for(DepartmentUserAddCustomerReportDto departmentUserReportDto : reportDtos){
				 if(departmentUserReportDto.getSumCount() > 0){
					 isAllZero = false;
				 }
			 }
		 }
		
		 structure7001View.setUnit("人");
		 structure7001View.setIsIndex(false);
		 if(isAllZero){
			 structure7001View.setValues(new ArrayList<Value>());
		 }else{
			 structure7001View.setValues(ReportDto.getValuesCustomerDepartmentUser(reqDto.getFilterType(),reqDto.getStatType(),reportDtos, "", "人"));
		 }
		 
		 structure7001View.setxTitles(ReportDto.getXTitleCustomerDepartmentUser(reqDto.getFilterType(),reqDto.getStatType(),reportDtos, ""));
		 YConfigeData yConfigeData =  ReportDto.getYConfigeDataCustomerDepartmentUser(reportDtos);
		 yConfigeData.setyMin(0.0);
		 structure7001View.setyConfigeData(yConfigeData);
		 viewItems.add(new ViewItem(7001, null, structure7001View));
	}

	
	private void setList(DepartmentAndUserDetailReqDto reqDto,
			PaginationSupport<CustomerReportDto> declarationDtos,List<ViewItem> viewItems){
		 List<CustomerReportDto> list =  declarationDtos.getDatas();
		 if(!BooleanUtils.isEmpty(list)){
			 for(CustomerReportDto dto : list){
				 Structure3020View structure3020View = new Structure3020View();
				 structure3020View.setCustomerName(dto.getName());
				 if(!BooleanUtils.isBlank(dto.getNumber())){
					 structure3020View.setCustomerNo("客户编号："+dto.getNumber());
				 }else{
					 structure3020View.setCustomerNo("客户编号：");
				 }
				
				 if(!BooleanUtils.isEmpty(dto.getLevelText())){
					 structure3020View.setLevel("级别："+dto.getLevelText());
				 }else{
					 structure3020View.setLevel("");
				 }
				 
				 structure3020View.setBargain(dto.getDeclarationCount() > 0 ?"已成交":"未成交");
				 structure3020View.setCustomerId(dto.getId());
				 
				 structure3020View.setCrateTime("创建日期："+DateUtils.format(dto.getCreateDate(), DateUtils.YYYY_MM_DD));
				 
				 String jumpUrl = Schema.CUSTOMER_DETAIL + String.format(Schema.CUSTOMER_DETAIL_PARAMS, dto.getId());
				 if(!BooleanUtils.isBlank(dto.getCreateUserName())){
					 structure3020View.setCreator("创建人："+dto.getCreateUserName());
				 }else{
					 structure3020View.setCreator("创建人：");
				 }
				
				 viewItems.add(new ViewItem(3020, jumpUrl, structure3020View));
				 viewItems.add(new ViewItem(2001, null, null)); 
			 }
		 }
		 
	}



}

