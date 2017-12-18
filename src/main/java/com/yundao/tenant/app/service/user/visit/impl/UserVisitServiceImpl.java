
package com.yundao.tenant.app.service.user.visit.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.PermissionUrlDto;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.user.visit.UserVisitPageReqDto;
import com.yundao.tenant.app.dto.user.visit.UserVisitPageResDto;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.user.visit.UserVisitService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.DateFormatUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.user.visit.Structure3012View;

/**
 * Function: Reason: Date: 2017年8月23日 下午8:29:48
 * 
 * @author wucq
 * @version
 */
@Service
public class UserVisitServiceImpl extends AbstractService implements UserVisitService {
	@Autowired
	private PermissionService permissionService;

	@Override
	public Result<ItemListDTO> getPage(UserVisitPageReqDto dto) throws BaseException {
		// 查看当前操作人的权限
		Integer permission = permissionService.getRead(DataObjectEnum.VISIT.getCode()).getResult();

		PermissionUrlDto urlDto = new PermissionUrlDto();
		urlDto.setPersonalUrl(TenantUrl.GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_PERSONAL);
		urlDto.setDepartmentUrl(TenantUrl.GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_DEPARTMENT);
		urlDto.setPublicUrl(TenantUrl.GET_USER_VISIT_PAGE_FOR_CUSTOMER_DETAIL_PUBLIC);
		String url = urlDto.getPermissionUrl(permission);
		// 如无权限，直接返回
		if (StringUtils.isNotBlank(url)){
			Result<PaginationSupport<UserVisitPageResDto>> peResult = HttpUtils.get(url, ArgsUtils.toMap(dto),
					new BaseTypeReference<Result<PaginationSupport<UserVisitPageResDto>>>() {
					});
			
			if(peResult !=null && peResult.getResult() !=null){
				PaginationSupport<UserVisitPageResDto> pageDatas=peResult.getResult();
				ItemListDTO itemListDTO=new ItemListDTO(pageDatas); 
				List<ViewItem> viewItems=new ArrayList<>();
				if(pageDatas.getDatas() !=null && !pageDatas.getDatas().isEmpty()){
					List<UserVisitPageResDto> dtos=pageDatas.getDatas();
					for(UserVisitPageResDto resDto:dtos){
						Structure3012View view3012=new Structure3012View();
						view3012.setContent(resDto.getContent());
						
						Date visitDate=DateFormatUtils.parse(resDto.getVisitDate(), "yyyy-MM-dd HH:mm:ss");
						if(visitDate !=null){
							view3012.setDateText(DateFormatUtils.format(visitDate, "yyyy-MM-dd HH:mm"));
						}
						view3012.setName(resDto.getUserName());
						view3012.setReason(resDto.getMatter());
						view3012.setSuccess(resDto.getStatus()==null?false:(resDto.getStatus()==1?true:false));
						view3012.setWay(resDto.getTypeText());
						
						viewItems.add(new ViewItem(3012,view3012));
					}
				}
				itemListDTO.setViewItems(viewItems);
				
				return Result.newSuccessResult(itemListDTO);
			}
		}

		return Result.newSuccessResult(new ItemListDTO());

	}

}
