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
import com.yundao.tenant.app.dto.report.UserDeclarationRankReqDto;
import com.yundao.tenant.app.dto.report.declaration.UserDeclarationRankResDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.tenant.app.model.department.BaseDepartment;
import com.yundao.tenant.app.service.department.DepartmentService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.report.declaration.UserDeclarationRankService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.NumberUtil;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.report.declaration.Structure7007View;

@Service
public class UserDeclarationRankServiceImpl extends AbstractService implements UserDeclarationRankService {
	@Autowired
	PermissionService permissionService;
	@Autowired
	DepartmentService departmentService;

	/**
	 * 权限校验和参数设置 setDataPermission:
	 * 
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	private Result<Integer> setDataPermission(UserDeclarationRankReqDto reqDto,ItemListDTO itemListDTO ) throws BaseException {
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
		}else if (DataObjectPermissionEnum.DEPARTMENT.getValue().equals(permissionResult.getResult())) {
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
		}else if (DataObjectPermissionEnum.PUBLIC.getValue().equals(permissionResult.getResult())){
			if(reqDto.getDepartmentId() == null){
				reqDto.setDepartmentId(0l);
			}
		}
		return Result.newSuccessResult(1);
	}

	/**
	 * 用户报单排行榜 getUserRankPage:
	 * 
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<ItemListDTO> getUserRankPage(UserDeclarationRankReqDto reqDto) throws BaseException {
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
		Result<PaginationSupport<UserDeclarationRankResDto>> result = HttpUtils.get(
				TenantUrl.REPORT_GET_USER_DECLARATION_RANK_PAGE, map,
				new BaseTypeReference<Result<PaginationSupport<UserDeclarationRankResDto>>>() {
				});

		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}
		PaginationSupport<UserDeclarationRankResDto> page = result.getResult();
		if(!BooleanUtils.isEmpty(page.getDatas())){
			for (UserDeclarationRankResDto resDto : page.getDatas()) {
				Structure7007View structure7007View = new Structure7007View();
				structure7007View.setName(resDto.getUsername());
				structure7007View.setMaxRecord(resDto.getMaxSumDealAmount());
				structure7007View.setOrderCount(resDto.getDeclarationCount());
				structure7007View.setRecordText(NumberUtil.getAmountStr(resDto.getSumDealAmount()) + "万");
				structure7007View.setRecord(resDto.getSumDealAmount());
				String jumpUrl = Schema.REPORT_DETAIL 
						 + String.format(Schema.REPORT_DETAIL_PARAMS,"", resDto.getUserId(),resDto.getUsername()+"的业绩");
				viewItems.add(new ViewItem(7007, jumpUrl, structure7007View));
			}
		}
		

		itemListDTO.setPaginaton(page);
		return Result.newSuccessResult(itemListDTO);
	}

}
