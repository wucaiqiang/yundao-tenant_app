
package com.yundao.tenant.app.service.customer.followup.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.customer.followup.BaseCustomerFollowUpAttach;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpPageDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpPageResDto;
import com.yundao.tenant.app.dto.customer.followup.CustomerFollowUpReqDto;
import com.yundao.tenant.app.enums.customer.MapTypeEnum;
import com.yundao.tenant.app.service.customer.followup.CustomerFollowUpService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.customer.Structure3021View;

/**
 * Function: Reason: Date: 2017年8月24日 下午2:11:25
 * 
 * @author wucq
 * @version
 */
@Service
public class CustomerFollowUpServiceImpl extends AbstractService implements CustomerFollowUpService {

	@Override
	public Result<ItemListDTO> getPage(CustomerFollowUpPageDto reqDto) throws BaseException {
		ItemListDTO itemListDTO = new ItemListDTO();
		Result<PaginationSupport<CustomerFollowUpPageResDto>> result = HttpUtils.get(
				TenantUrl.CUSTOMER_FOLLOW_GETS_PUBLIC, ArgsUtils.toMap(reqDto),
				new BaseTypeReference<Result<PaginationSupport<CustomerFollowUpPageResDto>>>() {
				});
		if (result.getResult() != null) {
			PaginationSupport<CustomerFollowUpPageResDto> pager = result.getResult();
			itemListDTO.setPaginaton(pager);
			List<CustomerFollowUpPageResDto> dtos = pager.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				List<ViewItem> viewItems=new ArrayList<>();
				for (CustomerFollowUpPageResDto dto : dtos) {
					Structure3021View view3021 = new Structure3021View();
					view3021.setContent(dto.getContent());
					view3021.setCreateTime(dto.getCreateDate());
					view3021.setFollowType(dto.getTypeText());
					view3021.setState(dto.getStatusText());
					view3021.setLocation(dto.getAddress());
					view3021.setOperateMan(dto.getCreateUserName());
					List<BaseCustomerFollowUpAttach> attachs = dto.getAttachs();
					if (attachs != null && !attachs.isEmpty()) {
						List<String> pictureList = new ArrayList<>();
						for (BaseCustomerFollowUpAttach attach : attachs) {
							pictureList.add(attach.getUrl());
						}
						view3021.setPictureList(pictureList);
					}
					viewItems.add(new ViewItem(3021, view3021));
				}
				itemListDTO.setViewItems(viewItems);
			}

		}
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<Long> add(CustomerFollowUpReqDto reqDto) throws BaseException {
		CustomerFollowUpDto dto = new CustomerFollowUpDto();
		dto.setContent(reqDto.getContent());
		dto.setCustomerId(reqDto.getCustomerId());
		if (reqDto.getNextFollowDate() != null) {
			dto.setNextFollowDate(new Date(reqDto.getNextFollowDate()));
		}
		if (StringUtils.isNotBlank(reqDto.getFollowStatus())) {
			dto.setStatus(NumberUtils.toInt(reqDto.getFollowStatus()));
		}
		if (StringUtils.isNotBlank(reqDto.getFollowType())) {
			dto.setType(NumberUtils.toInt(reqDto.getFollowType()));
		}
		if (StringUtils.isNotBlank(reqDto.getAddress())) {
			dto.setAddress(reqDto.getAddress());
		}
		if (reqDto.getLongitude() != null) {
			dto.setItudeX(reqDto.getLongitude());
		}
		if (reqDto.getLatitude() != null) {
			dto.setItudeY(reqDto.getLatitude());
		}
		if (StringUtils.isNotBlank(reqDto.getMapType()) && "GAODE".equals(reqDto.getMapType())) {
			dto.setMapType(MapTypeEnum.GAODE.getValue());
		}
		if (StringUtils.isNotBlank(reqDto.getPictureList())) {
			dto.setAttachs(reqDto.getPictureList());
		}

		Result<Long> result = HttpUtils.post(TenantUrl.FOLLOW_UP_ADD, ArgsUtils.toMap(dto),
				new BaseTypeReference<Result<Long>>() {
				});

		return result;

	}

}
