

package com.yundao.tenant.app.service.workflow.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.service.workflow.TaskService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月9日 下午4:16:50 
 * @author   wucq
 * @version   
 */
@Service
public class TaskServiceImpl extends AbstractService implements TaskService {

	@Override
	public Result<Integer> getTodoTaskCount() throws BaseException {
		Result<Integer> result = HttpUtils.get(TenantUrl.TASK_GET_TODO_COUNT, null,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;
	}

}

