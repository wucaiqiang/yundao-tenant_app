

package com.yundao.tenant.app.service.report.declaration.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationListReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationRankReqDto;
import com.yundao.tenant.app.dto.report.declaration.customer.DeclarationRankResDto;
import com.yundao.tenant.app.dto.report.declaration.detail.DepartmentUserDeclarationDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationUserDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.model.department.BaseDepartment;
import com.yundao.tenant.app.service.department.DepartmentService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.report.declaration.CustomerDeclarationRankService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.report.declaration.Structure7009View;
import com.yundao.tenant.app.view.sale.declaration.Structure4003View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月6日 下午3:39:35 
 * @author   欧阳利
 * @version   
 */
@Service
public class CustomerDeclarationRankServiceImpl extends AbstractService implements CustomerDeclarationRankService {
	@Autowired
	PermissionService permissionService;
	@Autowired
	DepartmentService departmentService;
	
	
	private Result<Integer> setDataPermission(DeclarationRankReqDto reqDto,ItemListDTO itemListDTO ) throws BaseException {
		// 查询数据权限配置
		Result<Integer> permissionResult = permissionService.getRead(DataObjectEnum.REPORT.getCode());
		if (!permissionResult.getSuccess()) {
			return Result.newFailureResult(permissionResult.getCode(), permissionResult.getMessage(), null);
		}
		itemListDTO.setPermissionText("您的查看权限："+DataObjectPermissionEnum.getEnumName(permissionResult.getResult()));
		if (DataObjectPermissionEnum.NONE.getValue().equals(permissionResult.getResult())) {
			return Result.newFailureResult(CodeConstant.CODE_1300038);
		}
  
		BaseDepartment department = null;
		if (reqDto.getDepartmentId() != null) {
			department = departmentService.getBaseDepartment(reqDto.getDepartmentId());
			if (department == null) {
				return Result.newFailureResult(CodeConstant.CODE_1200004);
			}
		}
		 // 数据权限是查看自己，就不能查询其他人的业绩
		if (DataObjectPermissionEnum.PERSONAL.getValue().equals(permissionResult.getResult())) {
			if(reqDto.getUserId() != null && !UserUtils.getUserId().equals(reqDto.getUserId())){
				return Result.newFailureResult(CodeConstant.CODE_1300038);
			}
			reqDto.setUserId(UserUtils.getUserId());
		}
		else if (DataObjectPermissionEnum.DEPARTMENT.getValue().equals(permissionResult.getResult())) {
			// 查询用户当前所在部门信息 判断用户是否可以查看部门信息
			BaseDepartment currentDepartment = departmentService.getCurrentUserDepartment();
			if(currentDepartment == null && reqDto.getDepartmentId() == null){
				reqDto.setDepartmentId(0l);
			}else{
				if (department != null) {
					if (!department.getCode().startsWith(currentDepartment.getCode())) {
						return Result.newFailureResult(CodeConstant.CODE_1300038);
					}
				}
				else {
					reqDto.setDepartmentId(currentDepartment.getId());
				}
			}
		}
		return Result.newSuccessResult(1);
	}
	
	
	@Override
	public Result<ItemListDTO> getCustomerRankPage(DeclarationRankReqDto reqDto) throws BaseException {
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		ItemListDTO itemListDTO = new ItemListDTO(viewItems);
		
		// 查询数据权限配置
		Result<Integer> dataResult = setDataPermission(reqDto,itemListDTO);
		if (!dataResult.getSuccess()) {
			return Result.newFailureResult(dataResult.getCode(), dataResult.getMessage(), null);
		}
		
		// 获取数据
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}
		Result<PaginationSupport<DeclarationRankResDto>> result = HttpUtils.get(
				TenantUrl.REPORT_GET_CUSTOMER_DECLARATION_RANK_PAGE, map,
				new BaseTypeReference<Result<PaginationSupport<DeclarationRankResDto>>>() {
				});

		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		PaginationSupport<DeclarationRankResDto> page = result.getResult();
		
		if(!BooleanUtils.isEmpty( page.getDatas())){
			
			List<String> userNameList = new ArrayList<String>();
			for (DeclarationRankResDto resDto : page.getDatas()) {
				Structure7009View structure7009View = new Structure7009View();
				structure7009View.setName(resDto.getCustomerName());
				structure7009View.setMaxRecord(resDto.getMaxSumDealAmount());
				structure7009View.setOrderCount(resDto.getDeclarationCount());
				structure7009View.setRecordText(NumberUtil.getAmountStr(resDto.getSumDealAmount()) + "万");
				structure7009View.setRecord(resDto.getSumDealAmount());
				
				
				userNameList.clear();
				if(!BooleanUtils.isEmpty(resDto.getUserDtos())){
					for(DeclarationUserDto userDto : resDto.getUserDtos()){
						if (!userNameList.contains(userDto.getName())) {
							userNameList.add(userDto.getName());
						}
					}
					
					StringBuffer userName = new StringBuffer();
					for(int i=0;i<userNameList.size() && i<=1;i++){
						userName.append(userNameList.get(i));
						if(i == 0 && userNameList.size() > 1){
							userName.append(",");
						}
					}
					structure7009View.setUserName(userName.toString());
				}
				
				
				
				String jumpUrl = Schema.REPORT_CUSTOMER_DETAIL 
						 + String.format(Schema.REPORT_CUSTOMER_DETAIL_PARAMS,resDto.getCustomerId());
				viewItems.add(new ViewItem(7009, jumpUrl, structure7009View));
			}
		}
		

		itemListDTO.setPaginaton(page);
		return Result.newSuccessResult(itemListDTO);
		
	}
	
	
	
	public Result<ItemListDTO> getCustomerListPage(DeclarationListReqDto reqDto)throws BaseException{
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		ItemListDTO itemListDTO = new ItemListDTO(viewItems);
		// 获取数据
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}
		Result<PaginationSupport<DepartmentUserDeclarationDto>> result = HttpUtils.get(
				TenantUrl.REPORT_GET_CUSTOMER_DECLARATION_LIST_PAGE, map,
				new BaseTypeReference<Result<PaginationSupport<DepartmentUserDeclarationDto>>>() {
				});
		if(!result.getSuccess()){
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		 List<DepartmentUserDeclarationDto> list =  result.getResult().getDatas();
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
				 structure4003View.setRightValue(dto.getPayDate());
				 structure4003View.setOrderDateText(dto.getApplyDate());
				 structure4003View.setOrderCertificateState(true);
				 structure4003View.setStateText(dto.getDeclarationStatusText());
				 structure4003View.setOrderCertificateText("打款凭证");
//				 String jumpUrl = Schema.DECLARATION_DETAIL
//					+ String.format(Schema.DECLARATION_DETAIL_PARAMS, dto.getId());
		          String jumpUrl = null;
				 viewItems.add(new ViewItem(4003, jumpUrl, structure4003View));
				 viewItems.add(new ViewItem(2001, null, null)); 
				 
			 }
		 }
		itemListDTO.setPaginaton(result.getResult());
		return Result.newSuccessResult(itemListDTO);
	}

}

