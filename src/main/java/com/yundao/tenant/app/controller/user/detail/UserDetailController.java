
package com.yundao.tenant.app.controller.user.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.service.user.detail.UserDetailService;
import com.yundao.tenant.app.view.user.detail.UserCardView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月21日 下午2:10:11
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/user_detail")
@ResponseBody
@Api("用户详情")
public class UserDetailController {
	@Autowired
	private UserDetailService userDetailService;

	@RequestMapping(value = "/get_card", method = RequestMethod.POST)
	@ApiOperation(value = "获取用户名片")
	public Result<UserCardView> getCard() throws BaseException {
		return userDetailService.get();
	}

	@RequestMapping(value = "/update_card_introduce", method = RequestMethod.POST)
	@ApiOperation(value = "修改用户名片", notes = "修改用户名片")
	public Result<Long> updateCardIntroduce(@RequestParam String des) throws Exception {
		return userDetailService.updateCardIntroduce(des);
	}

	@RequestMapping(value = "/update_introduce_and_header_bit", method = RequestMethod.POST)
	@ApiOperation(value = "修改用户名片简介与头衔", notes = "修改用户名片简介与头衔")
	public Result<Long> updateCardIntroduceAndHeaderBit(@RequestParam(defaultValue = "") String des,
			@RequestParam(defaultValue = "") String headerBit) throws Exception {
		return userDetailService.updateCardIntroduceAndHeaderBit(des, headerBit);
	}

}
