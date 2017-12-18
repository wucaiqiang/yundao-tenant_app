

package com.yundao.tenant.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.service.index.IndexService;
import com.yundao.tenant.app.view.index.IndexWorkbenchView;
import com.yundao.tenant.app.view.report.Structure1View;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月8日 下午4:47:42 
 * @author   wucq
 * @version   
 */
@Controller
@Api("首页")
@ResponseBody
public class IndexController {
	@Autowired
	private IndexService indexService;
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	@ApiOperation(value = "工作")
	public Result<ItemListDTO> index() throws Exception {
		return indexService.index();
	}
	@RequestMapping(value = "/report/get_by_time_type", method = RequestMethod.POST)
	@ApiOperation(value = "根据类型获取报表")
	public Result<Structure1View> getByTimeType(@RequestParam(defaultValue="CUR_MONTH") String timeType) throws BaseException {
		return indexService.getByTimeType(timeType);
	}
}

