

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
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ItemListNoPageDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.report.declaration.DeclarationDepartmentListDto;
import com.yundao.tenant.app.dto.report.declaration.DepartmentReportDto;
import com.yundao.tenant.app.dto.report.declaration.DepartmentReportReqDto;
import com.yundao.tenant.app.dto.report.declaration.DepartmentUserSelectDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.model.department.BaseDepartment;
import com.yundao.tenant.app.service.department.DepartmentService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.report.declaration.DepartmentDeclarationReportService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.view.DividingLineView;
import com.yundao.tenant.app.view.report.declaration.Structure7004View;
import com.yundao.tenant.app.view.report.declaration.Structure7005View;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月2日 上午9:10:12 
 * @author   欧阳利
 * @version   
 */
@Service
public class DepartmentDeclarationReportServiceImpl implements DepartmentDeclarationReportService {

	@Autowired
	PermissionService permissionService;
	@Autowired
	DepartmentService departmentService;
	
	@Override
	public Result<ItemListNoPageDTO> getDepartmentListDto(DepartmentReportReqDto reqDto) throws BaseException {
		// 查询数据权限配置
		Result<Integer> permissionResult = permissionService.getRead(DataObjectEnum.REPORT.getCode());
		if(!permissionResult.getSuccess()){
			 return Result.newFailureResult(permissionResult.getCode(), permissionResult.getMessage(), null);
		 }
		
		if(DataObjectPermissionEnum.NONE.getValue().equals(permissionResult.getResult()) ||
				DataObjectPermissionEnum.PERSONAL.getValue().equals(permissionResult.getResult())){
			return Result.newFailureResult(CodeConstant.CODE_1300038);
		}
		BaseDepartment department = null;
		if(reqDto.getDepartmentId() != null){
			
			department = departmentService.getBaseDepartment(reqDto.getDepartmentId());
			if(department == null && reqDto.getDepartmentId() != 0l){
				return Result.newFailureResult(CodeConstant.CODE_1200004);
			}
		}
		
		// 不是全部权限就要检查数据权限
		if(DataObjectPermissionEnum.DEPARTMENT.getValue().equals(permissionResult.getResult()) ){
			// 查询用户当前所在部门信息    判断用户是否可以查看部门信息
			BaseDepartment currentDepartment = departmentService.getCurrentUserDepartment();
			if(currentDepartment == null && reqDto.getDepartmentId() == null){
				reqDto.setDepartmentId(0l);
			}else {
				if(department != null){
					if(!department.getCode().startsWith(currentDepartment.getCode())){
						return Result.newFailureResult(CodeConstant.CODE_1300038);
					}
				}else{
					reqDto.setDepartmentId(currentDepartment.getId());
					department = currentDepartment;
				}
			}
			
		}
		
		 // 获取数据
		Map<String, Object> map = ArgsUtils.toMap(reqDto);
		if(reqDto.getCustomEnd() != null){
			map.put("customEnd", DateUtils.format(new Date(reqDto.getCustomEnd()), DateUtils.YYYY_MM_DD));
		}
		if(reqDto.getCustomStart() != null){
			map.put("customStart", DateUtils.format(new Date(reqDto.getCustomStart()), DateUtils.YYYY_MM_DD));
		}
		 Result<List<DepartmentReportDto>> result = HttpUtils.get(TenantUrl.REPORT_GET_DEPAETMENT_DECLARATION, map,
				new BaseTypeReference<Result<List<DepartmentReportDto>>>() {
				});
		 if(!result.getSuccess()){
			 return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		 }
		
		 List<DepartmentReportDto> list = result.getResult();
		 List<ViewItem> viewItems = new ArrayList<ViewItem>();
		 // 设置饼图数据
		 String departmentName = department == null?"全公司":department.getName();
		 Long upperDepartmentId = department == null? null:department.getParentId();
		 setPieChartData(upperDepartmentId,departmentName,viewItems,list);
		 
		 
		 viewItems.add(new ViewItem(2001, new DividingLineView()));
		 // 设置列表数据
		 setListData(viewItems,list);
		 
		 ItemListNoPageDTO dto = new ItemListNoPageDTO(viewItems);
		 dto.setPermissionText("您的查看权限："+DataObjectPermissionEnum.getEnumName(permissionResult.getResult()));
		 dto.setChatDes("说明: 百分比四舍五入到小数点后1位，全部相加可能不正好等于100%");
		 return Result.newSuccessResult(dto);
	}
	
	/**
	 * 设置饼图数据
	 * setPieChartData:
	 * @author: 欧阳利
	 * @param upperDepartmentId
	 * @param viewItems
	 * @param list
	 * @description:
	 */
	private void setPieChartData(Long upperDepartmentId,String departmentName, List<ViewItem> viewItems,List<DepartmentReportDto> list){
		 Structure7004View structure7004View = new Structure7004View();
		 if(upperDepartmentId != null){
			 structure7004View.setUpperDepartmentId(upperDepartmentId);
		 }
		 viewItems.add(new ViewItem(7004, null, structure7004View));
		 
		 List<DepartmentUserSelectDto> departments = new ArrayList<DepartmentUserSelectDto>();
		 structure7004View.setDepartments(departments);
		 structure7004View.setDepartmentName(departmentName);
		 if(!BooleanUtils.isEmpty(list)){
			 for(DepartmentReportDto dto: list){
				 if(dto.getSumDealAmount() > 0){
					 DepartmentUserSelectDto departmentUserSelectDto = new DepartmentUserSelectDto();
					 departments.add(departmentUserSelectDto);
					 departmentUserSelectDto.setRecord(dto.getSumDealAmount());
					 departmentUserSelectDto.setRecordText(NumberUtil.getAmountStr(dto.getSumDealAmount())+"万");
					 if(!BooleanUtils.isEmpty(dto.getDepartmentName())){
						 departmentUserSelectDto.setTitle(dto.getDepartmentName());
					 }else{
						 departmentUserSelectDto.setTitle(dto.getUsername());
					 }
				 }
			 }
		 }
	}
	
	/**
	 * 设置列表数据
	 * setListData:
	 * @author: 欧阳利
	 * @param viewItems
	 * @param list
	 * @description:
	 */
    private void setListData(List<ViewItem> viewItems,List<DepartmentReportDto> list){
    	Structure7005View structure7005View = new Structure7005View();
    	viewItems.add(new ViewItem(7005, null, structure7005View));
    	
    	List<DeclarationDepartmentListDto> departments = new ArrayList<DeclarationDepartmentListDto>();
    	structure7005View.setDepartments(departments);
    	if(!BooleanUtils.isEmpty(list)){
    		Double sumDealAmount = 0.0d;
    		for(DepartmentReportDto dto: list){
    			sumDealAmount = sumDealAmount + dto.getSumDealAmount();
    		}
			 for(DepartmentReportDto dto: list){
				 DeclarationDepartmentListDto departmentUserSelectDto = new DeclarationDepartmentListDto();
				 departments.add(departmentUserSelectDto);
				 departmentUserSelectDto.setDepartmentId(dto.getDepartmentId());
				 departmentUserSelectDto.setUserId(dto.getUserId());
				 if(!BooleanUtils.isEmpty(dto.getDepartmentName())){
					 departmentUserSelectDto.setTitle(dto.getDepartmentName());
				 }else{
					 departmentUserSelectDto.setTitle(dto.getUsername());
				 }
				 departmentUserSelectDto.setRecordText(NumberUtil.getAmountStr(dto.getSumDealAmount())+"万");
				 if(dto.getSumDealAmount() > 0){
					Long d = Math.round((dto.getSumDealAmount()/sumDealAmount)*100000/100);
					Double dd = Double.valueOf(d.toString());
					departmentUserSelectDto.setRecordPerText(NumberUtil.getAmountStr(dd/10)+"%");
				 }else{
					 departmentUserSelectDto.setRecordPerText("0%");
				 }
				 
				 if(dto.getDepartmentId() != null ){
					 departmentUserSelectDto.setRecordDetailAction(new ActionDTO(Schema.REPORT_DETAIL 
							 + String.format(Schema.REPORT_DETAIL_PARAMS, dto.getDepartmentId(), "",dto.getDepartmentName()+"部门的业绩")));
				 }
				 if(dto.getUserId() != null ){
					 departmentUserSelectDto.setRecordDetailAction(new ActionDTO(Schema.REPORT_DETAIL 
							 + String.format(Schema.REPORT_DETAIL_PARAMS, "", dto.getUserId(),dto.getUsername()+"的业绩")));
				 }
			 }
		 }
	}

}

