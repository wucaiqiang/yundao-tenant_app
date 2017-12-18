

package com.yundao.tenant.app.service.msg;

import java.util.Date;
import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.msg.MsgMessagePageResDto;
import com.yundao.tenant.app.dto.msg.MsgMessageQueryReqDto;
import com.yundao.tenant.app.view.msg.MsgUnReadCountView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月7日 下午5:37:02 
 * @author   wucq
 * @version   
 */
public interface MsgMessageService {
	public Result<MsgUnReadCountView> getUnreadCount(Date lastDate) throws BaseException ;
	public Result<Integer> getTodoCount() throws BaseException ;
	public Result<ItemListDTO> getPage(MsgMessageQueryReqDto dto) throws BaseException ;
	public Result<List<MsgMessagePageResDto>> getListByLimit(MsgMessageQueryReqDto dto) throws BaseException ;
	public Result<Integer> processReadAll(String type) throws BaseException ;
	public Result<Integer> processRead(String ids) throws BaseException ;
}

