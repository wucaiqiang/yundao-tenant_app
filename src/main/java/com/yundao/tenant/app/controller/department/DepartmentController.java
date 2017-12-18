
package com.yundao.tenant.app.controller.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.department.DepartmentUserListResDto;
import com.yundao.tenant.app.service.department.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年12月1日 下午8:32:03
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/department/")
@ResponseBody
@Api("部门相关")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@ApiOperation(value = "部门列表查询")
	@RequestMapping(value = "/get_department_and_users", method = RequestMethod.POST)
	public Result<DepartmentUserListResDto> getDepartmentAndUsers() throws BaseException {
		return Result.newSuccessResult(departmentService.getDepartmentAndUsers());
	}
}
