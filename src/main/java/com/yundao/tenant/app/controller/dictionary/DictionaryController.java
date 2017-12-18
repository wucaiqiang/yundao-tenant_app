

package com.yundao.tenant.app.controller.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.service.dictionary.DictionaryService;
import com.yundao.tenant.app.view.dictionary.DictionaryItemsView;
import com.yundao.tenant.app.view.dictionary.FollowUpView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月24日 下午1:41:01 
 * @author   wucq
 * @version   
 */
@Controller
@RequestMapping("/dictionary")
@ResponseBody
@Api("数据字典")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping(value = "/follow/status_type", method = RequestMethod.POST)
	@ApiOperation("获取跟进方式与跟进状态")
	public Result<FollowUpView> getFollowUpStatusAndType()throws Exception{
		return dictionaryService.getFollowUpStatusAndType();
	}
	@RequestMapping(value = "/customer/level", method = RequestMethod.POST)
	@ApiOperation("获取客户级别")
	public Result<DictionaryItemsView> getCustomerLevel()throws Exception{
		return dictionaryService.getCustomerLevel();
	}
	@RequestMapping(value = "/customer/invest", method = RequestMethod.POST)
	@ApiOperation("获取客户投资偏好")
	public Result<DictionaryItemsView> getCustomerInvest()throws Exception{
		return dictionaryService.getCustomerInvest();
	}
}

