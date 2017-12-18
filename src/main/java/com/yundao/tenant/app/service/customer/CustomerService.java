
package com.yundao.tenant.app.service.customer;

import java.util.List;
import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.CustomerAddReqDto;
import com.yundao.tenant.app.dto.customer.CustomerListQueryReqDto;
import com.yundao.tenant.app.dto.customer.CustomerSaleQueryReqDto;
import com.yundao.tenant.app.dto.customer.bank.CustomerBankReqDto;
import com.yundao.tenant.app.model.customer.BaseCustomer;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;
import com.yundao.tenant.app.view.customer.CustomerImportBookView;
import com.yundao.tenant.app.view.customer.CustomerListColumnView;
import com.yundao.tenant.app.view.customer.CustomerQueryConditionView;

/**
 * Function: Reason: Date: 2017年8月16日 下午3:35:19
 * 
 * @author wucq
 * @version
 */
public interface CustomerService {
	Result<ItemListDTO> getMyPage(CustomerListQueryReqDto dto) throws BaseException;

	Result<ItemListDTO> getMyPageV3(CustomerListQueryReqDto dto) throws BaseException;

	Result<ItemListDTO> getSalePage(CustomerSaleQueryReqDto dto) throws BaseException;
	Result<ItemListDTO> getSalePageForOpenSea(CustomerSaleQueryReqDto dto) throws BaseException;

	Result<ItemListDTO> getMyPageSearch(BasePageDto reqDto, String keyword) throws BaseException;

	Result<ItemListDTO> getSearchPage(BasePageDto dto, String keyword) throws BaseException;

	Result<Map<String, Object>> add(CustomerAddReqDto reqDto) throws BaseException;

	public Result<CustomerImportBookView> addFromBook(String customerList) throws Exception;

	Result<CustomerDetailHeaderView> getDetailHeader(Long customerId, Integer from) throws BaseException;
	Result<CustomerDetailHeaderView> getDetailHeaderV2(Long customerId) throws BaseException;

	Result<CustomerDetailView> getDetail(Long customerId) throws BaseException;

	Result<List<BaseCustomer>> getByMobileOrNumber(String mobileOrNumber) throws BaseException;

	Result<Boolean> update(Map<String, String> params) throws BaseException;

	public Result<Map<String, Object>> updateBank(CustomerBankReqDto customerBankReqDto) throws BaseException;

	public Result<Boolean> validateMobile(String mobile, Long id) throws BaseException;

	/**
	 * 
	 * getColumns:
	 * 
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	Result<CustomerListColumnView> getColumns() throws BaseException;

	Result<CustomerListColumnView> getColumnsV2() throws BaseException;

	Result<CustomerQueryConditionView> getQueryCondition() throws BaseException;

	Result<CustomerQueryConditionView> getQueryConditionV2() throws BaseException;

	public Result<SearchView> searchByKeyword(String keyword) throws BaseException;
}
