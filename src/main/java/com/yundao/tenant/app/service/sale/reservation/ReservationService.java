package com.yundao.tenant.app.service.sale.reservation;

import java.util.Map;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.sale.reservation.MyReservationListResDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationAddReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationCancelReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationListQueryReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSearchListReqDto;
import com.yundao.tenant.app.dto.sale.reservation.ReservationSelectListQueryDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.sale.reservation.DeclarationReservationSelectedView;
import com.yundao.tenant.app.view.sale.reservation.ReservationDetailView;
import com.yundao.tenant.app.view.sale.reservation.ReservationListColumnsView;

/**
 * Created by lan on 2017/8/30.
 */
public interface ReservationService {

	Result<Long> add(ReservationAddReqDto dto) throws BaseException;

	Result<Long> cancel(ReservationCancelReqDto dto) throws BaseException;

	Result<ReservationListColumnsView> getColumns() throws BaseException;

	Result<Long> againCommit(Long appointId) throws BaseException;

	Result<ReservationDetailView> detail(Long appointId) throws BaseException;

	Result<ItemListDTO> getMyPage(ReservationListQueryReqDto reqDto) throws BaseException;

	Result<DeclarationReservationSelectedView> getNewest(Long productId) throws BaseException;
	Result<ItemListDTO> getsByProductId(Long productId) throws BaseException;

	Result<ItemListDTO> getMySearchPage(ReservationSearchListReqDto reqDto) throws BaseException;

	Result<PaginationSupport<MyReservationListResDto>> getsByCustomerId(ReservationSelectListQueryDto reqDto)
			throws BaseException;

	public Result<ItemListDTO> getSelectCanOrderPage(ReservationSelectListQueryDto reqDto) throws BaseException;

	Result<Boolean> update(Map<String, String> params) throws BaseException;

	/**
	 * 预约搜索：App搜索关键字模糊匹配
	 * 
	 * @param keyword
	 *            关键字
	 * @return
	 * @throws BaseException
	 */
	Result<SearchView> searchByKeyword(String keyword) throws BaseException;
}
