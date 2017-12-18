

package com.yundao.tenant.app.service.report.declaration.impl;

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
import com.yundao.tenant.app.dto.report.declaration.DepartUserDeclarationReportReqDto;
import com.yundao.tenant.app.dto.report.declaration.ReportDto;
import com.yundao.tenant.app.dto.report.declaration.detail.DepartmentUserDeclarationDto;
import com.yundao.tenant.app.dto.report.declaration.detail.DepartmentUserDeclarationReportDetailDto;
import com.yundao.tenant.app.dto.report.declaration.detail.DepartmentUserReportDto;
import com.yundao.tenant.app.dto.report.report.Value;
import com.yundao.tenant.app.dto.report.report.YConfigeData;
import com.yundao.tenant.app.service.report.declaration.DepartUserDeclarationReportService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.report.declaration.Structure7001View;
import com.yundao.tenant.app.view.sale.declaration.Structure4003View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月4日 上午9:06:03 
 * @author   欧阳利
 * @version   
 */
@Service
public class DepartUserDeclarationReportServiceImpl implements DepartUserDeclarationReportService {

	@Override
	public Result<ItemListDTO> getDepartmentUserDetailDto(DepartUserDeclarationReportReqDto reqDto)
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
	private Result<ItemListDTO> getDepartmentDetailDto(DepartUserDeclarationReportReqDto reqDto)throws BaseException{
        // 获取数据
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}

		Result<DepartmentUserDeclarationReportDetailDto> result = HttpUtils.get(TenantUrl.REPORT_GET_DEPAETMENT_OR_USER_DETAIL, 
				map,new BaseTypeReference<Result<DepartmentUserDeclarationReportDetailDto>>() {});
		
		
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		DepartmentUserDeclarationReportDetailDto dto = result.getResult();
		// 部门或者员工业走势
		if(reqDto.getCurrentPage() == 1){
			setReport(reqDto,dto.getReportDtos(),dto,viewItems);
		}
		
		viewItems.add(new ViewItem(2001, new DividingLineView()));
		
		//分页查询员工或者部门业绩列表
		setList(reqDto,dto.getDeclarationDtos(),viewItems );
		ItemListDTO itemListDTO = new ItemListDTO(viewItems);
		itemListDTO.setPaginaton(dto.getDeclarationDtos());
		
		return Result.newSuccessResult(itemListDTO);
	}
	
	
	private void setReport(DepartUserDeclarationReportReqDto reqDto,List<DepartmentUserReportDto> reportDtos,DepartmentUserDeclarationReportDetailDto dto,List<ViewItem> viewItems){
		 Structure7001View structure7001View = new Structure7001View();
//		 if(reqDto.getDepartmentId() != null){
//			 structure7001View.setTitle(dto.getDepartmentName()+"的业绩");
//		 }
//		 if(reqDto.getUserId() != null){
//			 structure7001View.setTitle(dto.getUserName()+"的业绩");
//		 }
		 // 如果所有金额都为0 显示没有数据
		 boolean isAllZero = true;
		 if(!BooleanUtils.isEmpty(reportDtos)){
			 for(DepartmentUserReportDto departmentUserReportDto : reportDtos){
				 if(departmentUserReportDto.getSumDealAmount() > 0){
					 isAllZero = false;
				 }
			 }
		 }
		
		 structure7001View.setUnit("万");
		 structure7001View.setIsIndex(false);
		 if(isAllZero){
			 structure7001View.setValues(new ArrayList<Value>());
		 }else{
			 structure7001View.setValues(ReportDto.getValuesDepartmentUser(reqDto.getFilterType(),reqDto.getStatType(),reportDtos, "", "万"));
		 }
		 
		 structure7001View.setxTitles(ReportDto.getXTitleDepartmentUser(reqDto.getFilterType(),reqDto.getStatType(),reportDtos, ""));
		 YConfigeData yConfigeData =  ReportDto.getYConfigeDataDepartmentUser(reportDtos);
		 yConfigeData.setyMin(0.0);
		 structure7001View.setyConfigeData(yConfigeData);
		 viewItems.add(new ViewItem(7001, null, structure7001View));
	}

	
	private void setList(DepartUserDeclarationReportReqDto reqDto,
			PaginationSupport<DepartmentUserDeclarationDto> declarationDtos,List<ViewItem> viewItems){
		 List<DepartmentUserDeclarationDto> list =  declarationDtos.getDatas();
		 if(!BooleanUtils.isEmpty(list)){
			 for(DepartmentUserDeclarationDto dto : list){
				 Structure4003View structure4003View = new Structure4003View();
				 structure4003View.setOrderId(dto.getId().toString());
				 structure4003View.setCustomerName(dto.getCustomerName());
				 structure4003View.setProductName(dto.getProductName());
				 structure4003View.setLeftName("认购金额");
				 structure4003View.setLeftValue(NumberUtil.getAmountStr(dto.getDealAmount())+"万");
				 structure4003View.setStateText(dto.getDeclarationStatusText());
				 structure4003View.setRightName("打款日期");
				 structure4003View.setOrderCertificateText("打款凭证");
				 structure4003View.setRightValue(dto.getPayDate());
				 structure4003View.setOrderDateText(dto.getApplyDate());
				 structure4003View.setOrderCertificateState(true);
				 structure4003View.setStateText(dto.getDeclarationStatusText());
//				 String jumpUrl = Schema.DECLARATION_DETAIL
//							+ String.format(Schema.DECLARATION_DETAIL_PARAMS, dto.getId());
				 String jumpUrl = null;
				 viewItems.add(new ViewItem(4003, jumpUrl, structure4003View));
				 viewItems.add(new ViewItem(2001, null, null)); 
			 }
		 }
		 
	}

}

