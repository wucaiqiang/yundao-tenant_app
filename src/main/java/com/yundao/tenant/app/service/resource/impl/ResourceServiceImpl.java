
package com.yundao.tenant.app.service.resource.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.model.resource.BaseResource;
import com.yundao.tenant.app.service.resource.ResourceService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年9月14日 下午4:55:26
 * 
 * @author wucq
 * @version
 */
@Service
public class ResourceServiceImpl extends AbstractService implements ResourceService {

	@Override
	public Result<List<String>> getsByUserId() throws BaseException {
		Set<String> codes = new HashSet<>();

		Result<List<BaseResource>> result = HttpUtils.get(TenantUrl.GET_MY, null,
				new BaseTypeReference<Result<List<BaseResource>>>() {
				});
		if (result.getResult() != null) {
			List<BaseResource> resources = result.getResult();
			if (resources != null && !resources.isEmpty()) {
				for (BaseResource resource : resources) {
					codes.add(resource.getCode());
				}
			}
		}
		
		return Result.newSuccessResult(new ArrayList<String>(codes));

	}

}
