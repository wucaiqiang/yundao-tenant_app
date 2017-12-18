
package com.yundao.tenant.app.service.fieldgroup.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.fieldgroup.FieldGroupDto;
import com.yundao.tenant.app.enums.bo.BusinessObjectEnum;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.service.fieldgroup.FieldGroupService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年8月21日 下午2:20:01
 * 
 * @author wucq
 * @version
 */
@Service
public class FieldGroupServiceImpl extends AbstractService implements FieldGroupService {

	@Override
	public List<FieldGroupDto> getAll() throws BaseException {
		Map<String, Object> params = new HashMap<>();
		params.put("boCode", BusinessObjectEnum.PRODUCT.getCode());
		params.put("currentPage", 1);
		params.put("pageSize", Integer.MAX_VALUE);
		params.put("isEnabled", CommonConstant.ONE);
		Result<PaginationSupport<FieldGroupDto>> models = HttpUtils.get(TenantUrl.FIELD_GROUP_GETS, params,
				new BaseTypeReference<Result<PaginationSupport<FieldGroupDto>>>() {
				});
		return models.getResult() == null ? null : models.getResult().getDatas();
	}

}
