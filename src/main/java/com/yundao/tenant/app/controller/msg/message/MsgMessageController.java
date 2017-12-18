
package com.yundao.tenant.app.controller.msg.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yundao.core.code.Result;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.msg.MsgMessageQueryReqDto;
import com.yundao.tenant.app.service.msg.MsgMessageService;
import com.yundao.tenant.app.view.msg.MsgUnReadCountView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月7日 下午5:33:06
 * 
 * @author wucq
 * @version
 */
@RestController
@RequestMapping(value = "/msg/message/")
@ResponseBody
@Api("消息表")
public class MsgMessageController {
	@Autowired
	private MsgMessageService msgMessageService;

	@RequestMapping(value = "get_unread_count", method = RequestMethod.POST)
	@ApiOperation(value = "获取未读数")
	public Result<MsgUnReadCountView> getUnreadCount(@RequestParam Long lastDate) throws Exception {
		Date date = new Date(lastDate);
		return msgMessageService.getUnreadCount(date);
	}
	@RequestMapping(value = "get_todo_count", method = RequestMethod.POST)
	@ApiOperation(value = "获取今日待办数")
	public Result<Integer> getTodoCount() throws Exception {
		return msgMessageService.getTodoCount();
	}

	@RequestMapping(value = "get_my_page", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询自己的消息")
	public Result<ItemListDTO> getMyPage(@ModelAttribute MsgMessageQueryReqDto dto) throws Exception {
		return msgMessageService.getPage(dto);
	}

	@RequestMapping(value = "readAll", method = RequestMethod.POST)
	@ApiOperation(value = "全部已读")
	public Result<Integer> readAll(@RequestParam String type) throws Exception {
		return msgMessageService.processReadAll(type);
	}

	@RequestMapping(value = "read", method = RequestMethod.POST)
	@ApiOperation(value = "全部已读")
	public Result<Integer> read(@RequestParam String messageIds) throws Exception {
		return msgMessageService.processRead(messageIds);
	}
}
