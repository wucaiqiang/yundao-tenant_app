
package com.yundao.tenant.app.service.product;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.product.ProductNoticeAddReqDto;
import com.yundao.tenant.app.dto.product.ProductNoticeQueryReqDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.product.ProductNoticeView;

/**
 * Function: Reason: Date: 2017年8月8日 上午10:22:21
 * 
 * @author wucq
 * @version
 */
public interface ProductNoticeService {
	public Result<Integer> add(ProductNoticeAddReqDto dto) throws BaseException ;
	public Result<ProductNoticeView> get(Long id) throws Exception;

	public Result<ItemListDTO> getPage(ProductNoticeQueryReqDto pageReqDto) throws BaseException;

	public Result<Integer> doApplyOrResubmit(Long id) throws BaseException;
	public Result<Integer> doCancelFlow(Long id,String reason) throws BaseException ;
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException ;
}
