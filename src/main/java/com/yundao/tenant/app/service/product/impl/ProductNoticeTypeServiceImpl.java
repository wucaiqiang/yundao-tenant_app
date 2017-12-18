
package com.yundao.tenant.app.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.model.product.BaseProductNoticeType;
import com.yundao.tenant.app.service.product.ProductNoticeTypeService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.NameIdView;

/**
 * Function: Reason: Date: 2017年10月18日 下午4:42:35
 * 
 * @author wucq
 * @version
 */
@Service
public class ProductNoticeTypeServiceImpl extends AbstractService implements ProductNoticeTypeService {

	@Override
	public Result<Map<String, Object>> getAll() throws BaseException {

		Result<List<BaseProductNoticeType>> queryResult = HttpUtils.get(TenantUrl.PRODUCT_NOTICE_TYPE_GET_ALL, null,
				new BaseTypeReference<Result<List<BaseProductNoticeType>>>() {
				});
		Map<String, Object> result = new HashMap<>();
		List<NameIdView> views = new ArrayList<>();
		if (queryResult.getResult() != null) {
			List<BaseProductNoticeType> noticeTypes = queryResult.getResult();
			if (noticeTypes != null && !noticeTypes.isEmpty()) {
				for (BaseProductNoticeType noticeType : noticeTypes) {
					views.add(new NameIdView(noticeType.getName(), String.valueOf(noticeType.getId())));
				}
			}
		}
		result.put("noticeTypeList", views);
		return Result.newSuccessResult(result);

	}

}
