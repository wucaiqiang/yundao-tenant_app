
package com.yundao.tenant.app.service.customer.tag.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.tag.TagDto;
import com.yundao.tenant.app.dto.tag.TagReqDto;
import com.yundao.tenant.app.model.customer.tag.TagModel;
import com.yundao.tenant.app.service.customer.tag.TagService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年8月18日 下午4:33:43
 * 
 * @author wucq
 * @version
 */
@Service
public class TagServiceImpl extends AbstractService implements TagService {

	@Override
	public Result<ItemListDTO> getPage(TagReqDto reqDto) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("name", reqDto.getKeyword());
		Result<PaginationSupport<TagModel>> result = HttpUtils.get(TenantUrl.TAG_GET_PAGE, methodMap,
				new BaseTypeReference<Result<PaginationSupport<TagModel>>>() {
				});
		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();
		if (result.getResult() != null) {
			PaginationSupport<TagModel> pager = result.getResult();
			itemListDTO.setPaginaton(pager);
			if (pager.getDatas() != null && !pager.getDatas().isEmpty()) {
				List<TagModel> tagModels = pager.getDatas();
				for (TagModel model : tagModels) {
					viewItems.add(new ViewItem(3015, new NameIdView(model.getName(), String.valueOf(model.getId()))));
				}
			}
		}
		itemListDTO.setViewItems(viewItems);
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<Boolean> setTag(Long customerId, String tags) throws BaseException {

		Map<String, Object> methodMap = new HashMap<String, Object>();
		methodMap.put("customerId", customerId);
		methodMap.put("tags", tags);
		Result<Boolean> result = HttpUtils.post(TenantUrl.SET_CUSTOMER_TAG, methodMap,
				new BaseTypeReference<Result<Boolean>>() {
				});
		return result;
	}

	@Override
	public Result<Map<String, Object>> getAll() throws BaseException {

		Result<List<TagDto>> tagsResult = HttpUtils.get(TenantUrl.TAG_GET_ALL, null,
				new BaseTypeReference<Result<List<TagDto>>>() {
				});
		if (tagsResult != null || tagsResult.getResult() != null) {
			List<TagDto> tagDtos = tagsResult.getResult();
			if (tagDtos != null && !tagDtos.isEmpty()) {
				List<NameIdView> items = new ArrayList<>();
				for (TagDto dto : tagDtos) {
					items.add(new NameIdView(dto.getName(), String.valueOf(dto.getId())));
				}
				Map<String, Object> retMap = new HashMap<>();
				retMap.put("items", items);
				return Result.newSuccessResult(retMap);
			}
		}

		return Result.newSuccessResult(null);

	}

	@Override
	public Result<Map<String, Object>> getTop() throws BaseException {
		Result<List<TagDto>> tagsResult = HttpUtils.get(TenantUrl.TAG_GET_TOP, null,
				new BaseTypeReference<Result<List<TagDto>>>() {
				});
		if (tagsResult != null || tagsResult.getResult() != null) {
			List<TagDto> tagDtos = tagsResult.getResult();
			if (tagDtos != null && !tagDtos.isEmpty()) {
				List<NameIdView> items = new ArrayList<>();
				for (TagDto dto : tagDtos) {
					items.add(new NameIdView(dto.getName(), String.valueOf(dto.getId())));
				}
				Map<String, Object> retMap = new HashMap<>();
				retMap.put("items", items);
				return Result.newSuccessResult(retMap);
			}
		}

		return Result.newSuccessResult(null);

	}

}
