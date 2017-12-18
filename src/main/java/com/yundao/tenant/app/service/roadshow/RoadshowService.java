
package com.yundao.tenant.app.service.roadshow;

import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.roadshow.RoadshowListReqDto;
import com.yundao.tenant.app.view.roadshow.RoadshowDetailView;

/**
 * Function: Reason: Date: 2017年11月9日 下午6:03:24
 * 
 * @author wucq
 * @version
 */
public interface RoadshowService {
	public Result<Map<String, Object>> getColumn() throws BaseException;

	public Result<ItemListDTO> getPage(RoadshowListReqDto dto) throws BaseException;

	public Result<RoadshowDetailView> get(String id) throws BaseException;
}
