
package com.yundao.tenant.app.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.model.resource.BaseResource;
import com.yundao.tenant.app.service.resource.ResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月14日 下午4:53:00
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/customer")
@Api("用户资源权限")
@ResponseBody
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/get_my", method = RequestMethod.POST)
	@ApiOperation(value = "获取当前用户的菜单资源")
	public Result<List<String>> getsByUserId() throws BaseException {
		return resourceService.getsByUserId();
	}
}
