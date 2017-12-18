
package com.yundao.tenant.app.service.department.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.department.DepartmentUserListResDto;
import com.yundao.tenant.app.model.department.BaseDepartment;
import com.yundao.tenant.app.service.department.DepartmentService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: Reason: Date: 2017年11月3日 下午2:02:22
 * 
 * @author 欧阳利
 * @version
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Override
	public BaseDepartment getCurrentUserDepartment() throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		Result<BaseDepartment> result = HttpUtils.get(TenantUrl.GET_CURRENT_DEPARTMENT_BY_ID, methodParams,
				new BaseTypeReference<Result<BaseDepartment>>() {
				});
		if (!result.getSuccess()) {
			throw new BaseException(result.getCode(), result.getMessage());
		}
		return result.getResult();
	}

	@Override
	public BaseDepartment getBaseDepartment(Long id) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("id", id);
		Result<BaseDepartment> result = HttpUtils.get(TenantUrl.GET_DEPARTMENT_BY_ID, methodParams,
				new BaseTypeReference<Result<BaseDepartment>>() {
				});
		if (!result.getSuccess()) {
			throw new BaseException(result.getCode(), result.getMessage());
		}
		return result.getResult();
	}

	@Override
	public DepartmentUserListResDto getDepartmentAndUsers() throws BaseException {

		Result<DepartmentUserListResDto> result = HttpUtils.get(TenantUrl.GET_DEPARTMENT_AND_USER, null,
				new BaseTypeReference<Result<DepartmentUserListResDto>>() {
				});
		if (result.getResult() != null) {
			return result.getResult();
		}
		return null;

	}

}
