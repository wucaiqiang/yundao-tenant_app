
package com.yundao.tenant.app.service.department;

import java.util.List;

import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.department.DepartmentUserListResDto;
import com.yundao.tenant.app.model.department.BaseDepartment;

/**
 * Function: Reason: Date: 2017年11月3日 下午2:00:03
 * 
 * @author 欧阳利
 * @version
 */
public interface DepartmentService {
	/**
	 * 查询当前用户所在的部门 getCurrentUserDepartment:
	 * 
	 * @author: 欧阳利
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public BaseDepartment getCurrentUserDepartment() throws BaseException;

	/**
	 * 通过部门id查询部门信息 getBaseDepartment:
	 * 
	 * @author: 欧阳利
	 * @param id
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public BaseDepartment getBaseDepartment(Long id) throws BaseException;

	/**
	 * 获取组织架构 getDepartmentAndUsers:
	 * 
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	DepartmentUserListResDto getDepartmentAndUsers() throws BaseException;
}
