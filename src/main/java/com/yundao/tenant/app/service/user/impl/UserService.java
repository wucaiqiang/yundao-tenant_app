
package com.yundao.tenant.app.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.MapUtils;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.user.UserBaseUpdateDto;
import com.yundao.tenant.app.dto.user.UserOptionResDto;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.user.Structure3016View;
import com.yundao.tenant.app.view.user.Structure3017View;

import ch.qos.logback.classic.Logger;

/**
 * Function: Reason: Date: 2017年11月13日 上午10:48:58
 * 
 * @author wucq
 * @version
 */
@Service
public class UserService implements com.yundao.tenant.app.service.user.UserService {
	private Log logger = LogFactory.getLog(UserService.class);

	@Override
	public Result<ItemListDTO> getManagerByRealName(String realName) throws BaseException {

		Map<String, Object> map = new HashMap<>();
		map.put("realName", realName);
		map.put("limit", 20);// 取20条
		Result<List<UserOptionResDto>> result = HttpUtils.get(TenantUrl.GETS_USER_BY_REALNAME, map,
				new BaseTypeReference<Result<List<UserOptionResDto>>>() {
				});

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		if (result.getResult() != null && !result.getResult().isEmpty()) {
			List<UserOptionResDto> resDtos = result.getResult();
			itemListDTO.setTotalCount(resDtos.size());
			for (UserOptionResDto dto : resDtos) {
				Structure3016View view3016 = new Structure3016View();
				view3016.setAssetManagerId(String.valueOf(dto.getId()));
				view3016.setMobile(dto.getMobile());
				view3016.setName(dto.getRealName());
				viewItems.add(new ViewItem(3016, view3016));
			}
			itemListDTO.setViewItems(viewItems);
		}
		return Result.newSuccessResult(itemListDTO);
	}

	@Override
	public Result<ItemListDTO> getCustomerServerByRealName(String realName) throws BaseException {

		Map<String, Object> map = new HashMap<>();
		map.put("realName", realName);
		map.put("limit", 20);// 取20条
		Result<List<UserOptionResDto>> result = HttpUtils.get(TenantUrl.GETS_USER_BY_REALNAME, map,
				new BaseTypeReference<Result<List<UserOptionResDto>>>() {
				});

		ItemListDTO itemListDTO = new ItemListDTO();
		List<ViewItem> viewItems = new ArrayList<>();

		if (result.getResult() != null && !result.getResult().isEmpty()) {
			List<UserOptionResDto> resDtos = result.getResult();
			itemListDTO.setTotalCount(resDtos.size());
			for (UserOptionResDto dto : resDtos) {
				Structure3017View view3017 = new Structure3017View();
				view3017.setEmployeeId(String.valueOf(dto.getId()));
				view3017.setName(dto.getRealName());
				view3017.setMobile(dto.getMobile());
				viewItems.add(new ViewItem(3017, view3017));
			}
			itemListDTO.setViewItems(viewItems);
		}
		return Result.newSuccessResult(itemListDTO);

	}

	@Override
	public Result<List<UserOptionResDto>> getUsersByRealName(String realName) throws BaseException {

		Map<String, Object> map = new HashMap<>();
		map.put("realName", realName);
		return HttpUtils.get(TenantUrl.GETS_USER_BY_REALNAME, map,
				new BaseTypeReference<Result<List<UserOptionResDto>>>() {
				});

	}

	@Override
	public Result<Long> updateBaseInfo(Map<String, String> params) throws BaseException {
		logger.info("参数：" + JsonUtils.objectToJson(params));
		return HttpUtils.post(TenantUrl.USER_UPDATE_BASE_INFO, MapUtils.cast(params),
				new BaseTypeReference<Result<Long>>() {
				});
	}

}
