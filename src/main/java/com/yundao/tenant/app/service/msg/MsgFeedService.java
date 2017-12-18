
package com.yundao.tenant.app.service.msg;


import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;

/**
 * Function: Reason: Date: 2017年8月24日 上午10:59:17
 * 
 * @author wucq
 * @version
 */
public interface MsgFeedService {
	public Result<ItemListDTO> getPage(Long customerId, String filterType,BasePageDto pageDto) throws BaseException;
}
