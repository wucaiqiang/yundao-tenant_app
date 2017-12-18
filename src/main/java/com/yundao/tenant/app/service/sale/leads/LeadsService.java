
package com.yundao.tenant.app.service.sale.leads;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsAllotToFpReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsPageReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsQueryReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsSearchReqDto;
import com.yundao.tenant.app.dto.sale.leads.LeadsUpdateStatusReqDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.sale.leads.LeadsQueryConditionView;

/**
 * Function: Reason: Date: 2017年9月11日 下午3:18:46
 * 
 * @author wucq
 * @version
 */
public interface LeadsService {
	public Result<ItemListDTO> getPage(LeadsPageReqDto dto) throws BaseException;
	public Result<ItemListDTO> search(LeadsSearchReqDto dto) throws BaseException;

	public Result<ItemListDTO> getPageForCustomer(LeadsQueryReqDto dto) throws BaseException;
	public Result<ItemListDTO> getPageForOpenSeaCustomer(LeadsQueryReqDto dto) throws BaseException;

	public Result<Long> doProcess(LeadsUpdateStatusReqDto dto) throws BaseException;

	public Result<LeadsQueryConditionView> getQueryCondition() throws BaseException;
	public Result<Boolean> allotToFp( LeadsAllotToFpReqDto dto) throws BaseException ;
	public Result<Boolean> doAllot(LeadsAllotReqDto dto) throws BaseException ;
	
	public Result<SearchView> searchByKeyword(String keyword) throws BaseException;
}
