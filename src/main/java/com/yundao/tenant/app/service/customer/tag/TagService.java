
package com.yundao.tenant.app.service.customer.tag;

import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.tag.TagReqDto;

/**
 * Function: Reason: Date: 2017年8月18日 下午4:33:07
 * 
 * @author wucq
 * @version
 */
public interface TagService {
	public Result<Boolean> setTag(Long customerId, String tags) throws BaseException;

	public Result<Map<String, Object>> getTop() throws BaseException;

	public Result<Map<String, Object>> getAll() throws BaseException;

	public Result<ItemListDTO> getPage(TagReqDto reqDto) throws BaseException;
}
