
package com.yundao.tenant.app.service.product;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.product.ProductBaseQueryReqDto;
import com.yundao.tenant.app.dto.product.ProductManagerReqDto;
import com.yundao.tenant.app.dto.product.UpdateProductStatusReqDto;
import com.yundao.tenant.app.view.SearchView;
import com.yundao.tenant.app.view.product.ProductDetailView;

/**
 * Function: Reason: Date: 2017年8月1日 下午4:02:46
 * 
 * @author wucq
 * @version
 */
public interface ProductService {
	public Result<ItemListDTO> search(String keyword, Integer page, Integer pageSize) throws BaseException;

	public Result<SearchView> searchByKeyword(String keyword) throws BaseException;
	public Result<SearchView> searchProductManagerByKeyword(String keyword) throws BaseException;

	public Result<ItemListDTO> getList(@ModelAttribute ProductBaseQueryReqDto reqDto) throws BaseException;

	public List<ViewItem> getRecommendedList(Integer limit) throws BaseException;

	public Result<ItemListDTO> getSelectedList(ProductBaseQueryReqDto reqDto) throws BaseException;

	public Result<ItemListDTO> getManagerList(ProductManagerReqDto reqDto) throws BaseException;

	public Result<ProductDetailView> getDetailById(Long id) throws BaseException;
	public Result<ProductDetailView> getDetailByIdV2(Long id) throws BaseException;
	public Result<ProductDetailView> getDetailByIdForShare(Long id) throws BaseException;

	public Result<Integer> updateProductStatus(UpdateProductStatusReqDto dto) throws BaseException;
}
