
package com.yundao.tenant.app.service.customer;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.opensea.CustomerOpenSeaPageReqDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.customer.CustomerDetailHeaderView;
import com.yundao.tenant.app.view.customer.CustomerDetailView;

/**
 * Function: Reason: Date: 2017年9月20日 下午4:59:41
 * 
 * @author 欧阳利
 * @version
 */
public interface CustomerOpenSeaService {

	/**
	 * 公海客户：App搜索关键字模糊匹配
	 * 
	 * @param keyword
	 *            关键字
	 * @return
	 * @throws BaseException
	 */
	Result<SearchView> searchByKeyword(String keyword) throws BaseException;

	/**
	 * 查询客户 getItemListDTO:
	 * 
	 * @author: 欧阳利
	 * @param reqDto
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<ItemListDTO> getItemListDTO(CustomerOpenSeaPageReqDto reqDto) throws BaseException;

	/**
	 * 客户头部信息 getDetailHeader:
	 * 
	 * @author: wucq
	 * @param customerId
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	Result<CustomerDetailHeaderView> getDetailHeader(Long customerId) throws BaseException;
	Result<CustomerDetailView> getDetail(Long customerId) throws BaseException;
}
