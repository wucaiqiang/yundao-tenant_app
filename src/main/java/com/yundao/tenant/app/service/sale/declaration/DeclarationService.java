
package com.yundao.tenant.app.service.sale.declaration;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.dto.BasePageDto;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddAndResubmitReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddDirectReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddInReservationReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationAddResDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationApplyRefundReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationBankReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationPageReqDto;
import com.yundao.tenant.app.dto.sale.declaration.DeclarationSelectListQueryDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.sale.declaration.ComplianceView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationCustomerView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationDetailHeaderView;
import com.yundao.tenant.app.view.sale.declaration.DeclarationListColumnView;
import com.yundao.tenant.app.view.sale.declaration.SubscribeView;

/**
 * Function: Reason: Date: 2017年9月2日 下午1:53:28
 * 
 * @author wucq
 * @version
 */
public interface DeclarationService {
	public Result<DeclarationAddResDto> add(DeclarationAddAndResubmitReqDto reqDto) throws BaseException;

	public Result<DeclarationAddResDto> addInReservation(DeclarationAddInReservationReqDto reqDto) throws BaseException;

	public Result<DeclarationAddResDto> addDirect(DeclarationAddDirectReqDto reqDto) throws BaseException;

	public Result<DeclarationAddResDto> addPatch(DeclarationAddDirectReqDto reqDto) throws BaseException;

	public Result<Long> submit(Long id) throws BaseException;

	public Result<Long> resubmit(DeclarationAddAndResubmitReqDto reqDto) throws BaseException;

	public Result<Boolean> update(Map<String, String> params) throws BaseException;

	public Result<Map<String, Object>> updateBank(DeclarationBankReqDto bankReqDto) throws BaseException;

	public Result<Long> doCancel(Long orderId, String reason) throws BaseException;

	Result<ItemListDTO> getSearchPage(BasePageDto dto, String keyword) throws BaseException;

	public Result<ItemListDTO> getPage(DeclarationPageReqDto declarationPageReqDto) throws BaseException;

	public Result<DeclarationDetailHeaderView> getDetailHeader(Long declarationId) throws BaseException;

	public Result<DeclarationCustomerView> getCustomerDetail(Long declarationId) throws BaseException;

	public Result<PaginationSupport<DeclarationDto>> getsByCustomerId(DeclarationSelectListQueryDto reqDto)
			throws BaseException;

	public Result<Map<String, Object>> getBank(Long declarationId) throws BaseException;

	public Result<SubscribeView> getSubscribe(Long declarationId) throws BaseException;

	public Result<ComplianceView> getCompliance(Long declarationId) throws BaseException;

	public Result<Long> applyRefund(DeclarationApplyRefundReqDto dto) throws BaseException;
	public Result<Long> refundCancel(DeclarationApplyRefundReqDto dto) throws BaseException;
	public Result<Long> resubmitRefund(Long declarationId) throws BaseException;

	public Result<DeclarationListColumnView> getColumns() throws BaseException;

	public Result<SearchView> searchByKeyword(String keyword) throws BaseException;
}
