
package com.yundao.tenant.app.service.msg.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.msg.MsgFeedResDto;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.service.msg.MsgFeedService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.msg.Structure3003View;

/**
 * Function: Reason: Date: 2017年8月24日 上午11:00:32
 * 
 * @author wucq
 * @version
 */
@Service
public class MsgFeedServiceImpl extends AbstractService implements MsgFeedService {

	@Override
	public Result<ItemListDTO> getPage(Long customerId, String filterType, BasePageDto pageDto) throws BaseException {

		Map<String, Object> params = ArgsUtils.toMap(pageDto);
		params.put("customerId", customerId);
		if (StringUtils.isNotBlank(filterType)) {
			if ("TRACE".equals(filterType)) {
				params.put("firstType", 2);
			} else if ("BEHAVIOUR".equals(filterType)) {
				params.put("firstType", 1);
			}
		}
		Result<PaginationSupport<MsgFeedResDto>> pageResult = HttpUtils.get(TenantUrl.GET_CUSTOMER_FEED, params,
				new BaseTypeReference<Result<PaginationSupport<MsgFeedResDto>>>() {
				});
		if (pageResult != null && pageResult.getResult() != null) {
			PaginationSupport<MsgFeedResDto> pager = pageResult.getResult();
			ItemListDTO itemListDTO = new ItemListDTO(pager);
			List<ViewItem> viewItems = new ArrayList<>();
			List<MsgFeedResDto> dtos = pager.getDatas();
			if (dtos != null && !dtos.isEmpty()) {
				for (MsgFeedResDto dto : dtos) {
					Structure3003View view3003 = new Structure3003View();
					view3003.setCreateTime(dto.getCreateDate());
					view3003.setContent(dto.getFeedContent());
					viewItems.add(new ViewItem(3003, view3003));
				}
			}
			itemListDTO.setViewItems(viewItems);
			return Result.newSuccessResult(itemListDTO);
		}
		return Result.newSuccessResult(new ItemListDTO());
	}

}
