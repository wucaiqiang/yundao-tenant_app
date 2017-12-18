
package com.yundao.tenant.app.controller.user.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.user.visit.UserVisitPageReqDto;
import com.yundao.tenant.app.service.user.visit.UserVisitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年8月23日 下午8:28:23
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/user/visit")
@ResponseBody
@Api("客户回访")
public class UserVisitController {
	@Autowired
	private UserVisitService userVisitService;
	 @RequestMapping(value = "/get_page", method = RequestMethod.POST)
	    @ApiOperation("分页列表")
	    public Result<ItemListDTO> getCustomerDetailVisitPage(@ModelAttribute UserVisitPageReqDto dto) throws BaseException {
	        return userVisitService.getPage(dto);
	    }
}
