

package com.yundao.tenant.app.service.workflow;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月9日 下午4:16:24 
 * @author   wucq
 * @version   
 */
public interface TaskService {
	/**
	 * 获取我的待办任务事
	 * getTodoTaskCount:
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<Integer> getTodoTaskCount() throws BaseException;
}

