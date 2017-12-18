package com.yundao.tenant.app.service.permission.impl;

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
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.DataPermissionDto;
import com.yundao.tenant.app.dto.common.FailListDto;
import com.yundao.tenant.app.dto.common.PassListDto;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.permission.PermissionDetailResDto;
import com.yundao.tenant.app.dto.permission.PermissionListResDto;
import com.yundao.tenant.app.dto.permission.PermissionMultiCodeDto;
import com.yundao.tenant.app.dto.permission.PermissionObjectDto;
import com.yundao.tenant.app.dto.product.BaseObjectDto;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.LambdaFilter;
import com.yundao.tenant.app.util.SymbolUtils;
import com.yundao.tenant.app.util.access.AccessCommon;

/**
 * 通用权限校验服务实现
 *
 * @author jan
 * @create 2017-08-06 PM1:08
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

	private static Log log = LogFactory.getLog(PermissionServiceImpl.class);

	@Override
	public Result<Integer> getUpdate(String dataObjectCode) throws BaseException {

		log.begin(dataObjectCode);
		Map<String, Object> paramMap = new HashMap<>(1);
		paramMap.put("dataObjectCode", dataObjectCode);
		Result<Integer> result = HttpUtils.get(TenantUrl.GET_DATA_OBJECT_PERMISSION_UPDATE, paramMap,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;

	}

	@Override
	public Result<PermissionResultDto> checkUpdateListForOwnerIds(List<BaseObjectDto> baseObjets, String dataObjectCode)
			throws BaseException {
		PermissionListResDto permissionDto = checkUpdateListSingleObj(dataObjectCode, this.fetchOwnerId(baseObjets));
		List<PermissionDetailResDto> permissionResultDtos = permissionDto.getPermissions();
		PermissionResultDto result = new PermissionResultDto();
		for (BaseObjectDto t : baseObjets) {
			List<Long> ownerIds = t.getOwnerIds();
			if (BooleanUtils.isEmpty(ownerIds)) {
				this.noPermission(baseObjets, t, result);
				continue;
			}

			PermissionDetailResDto detailResDto = getPermissionDetailResDto(permissionResultDtos, ownerIds);
			if (detailResDto.getIsPass()) {
				this.passPermission(t, result);
			} else {
				this.noPermission(baseObjets, t, result);
			}

		}
		return result.isSuccess() ? Result.newSuccessResult(result) : Result.newResult(0, result);

	}

	@Override
	public PermissionListResDto checkUpdateListSingleObj(String dataObjectCode, List<Long> ownerIdList)
			throws BaseException {
		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("dataObjectCode", dataObjectCode);
		paramMap.put("ownerIds", SymbolUtils.longToStr(ownerIdList));
		Result<List<PermissionListResDto>> result = HttpUtils.get(TenantUrl.CHECK_DATA_OBJECT_PERMISSION_UPDATE_LIST,
				paramMap, new BaseTypeReference<Result<List<PermissionListResDto>>>() {
				});
		if (!result.getSuccess()) {
			throw new BaseException(result.getCode(), result.getMessage());
		}
		return result.getResult().get(0);
	}

	@Override
	public Result<Integer> getRead(String dataObjectCode) throws BaseException {
		log.begin(dataObjectCode);
		Map<String, Object> paramMap = new HashMap<>(1);
		paramMap.put("dataObjectCode", dataObjectCode);
		Result<Integer> result = HttpUtils.get(TenantUrl.GET_DATA_OBJECT_PERMISSION_READ, paramMap,
				new BaseTypeReference<Result<Integer>>() {
				});
		return result;
	}

	@Override
	public <T> Result<PermissionResultDto> checkDeleteList(List<T> ls, String dataObjectCode) throws BaseException {
		log.begin(ls, dataObjectCode);
		PermissionListResDto permissionDto = httpCheckDeleteList(dataObjectCode, this.fetchOwnerId(ls)).get(0);
		List<PermissionDetailResDto> permissionResultDtos = permissionDto.getPermissions();
		PermissionResultDto result = new PermissionResultDto();
		for (T t : ls) {
			Object ownerId = AccessCommon.getOwner(t);
			if (ownerId == null)
				this.noPermission(ls, t, result);
			PermissionDetailResDto detailResDto = LambdaFilter.firstOrDefault(permissionResultDtos,
					m -> m.getOwnerId().equals(ownerId));
			if (detailResDto.getIsPass())
				this.passPermission(t, result);
			else
				this.noPermission(ls, t, result);
		}
		return result.isSuccess() ? Result.newSuccessResult(result) : Result.newResult(0, result);
	}

	/**
	 * 检查是否有权限删除
	 * 
	 * @param ls
	 * @param dataObjectCode
	 * @return
	 * @throws BaseException
	 */
	public Result<PermissionResultDto> checkDeleteListForOwnerIds(List<PermissionObjectDto> baseObjets,
			String dataObjectCode) throws BaseException {
		log.begin(baseObjets, dataObjectCode);
		PermissionListResDto permissionDto = httpCheckDeleteList(dataObjectCode, this.fetchOwnerId(baseObjets)).get(0);
		List<PermissionDetailResDto> permissionResultDtos = permissionDto.getPermissions();
		PermissionResultDto result = new PermissionResultDto();
		for (PermissionObjectDto t : baseObjets) {
			List<Long> ownerIds = t.getOwnerIds();
			if (BooleanUtils.isEmpty(ownerIds)) {
				this.noPermission(baseObjets, t, result);
				continue;
			}

			PermissionDetailResDto detailResDto = getPermissionDetailResDto(permissionResultDtos, ownerIds);
			if (detailResDto.getIsPass()) {
				this.passPermission(t, result);
			} else {
				this.noPermission(baseObjets, t, result);
			}

		}
		return result.isSuccess() ? Result.newSuccessResult(result) : Result.newResult(0, result);
	}

	/**
	 * 返回拥有这个权限详情
	 * 
	 * @param permissionResultDtos
	 * @param ownerIds
	 * @return
	 */
	private PermissionDetailResDto getPermissionDetailResDto(List<PermissionDetailResDto> permissionResultDtos,
			List<Long> ownerIds) {
		if (BooleanUtils.isEmpty(ownerIds) || BooleanUtils.isEmpty(permissionResultDtos)) {
			return null;
		}
		for (Long ownerId : ownerIds) {
			for (PermissionDetailResDto dto : permissionResultDtos) {
				if (ownerId.equals(dto.getOwnerId())) {
					return dto;
				}
			}
		}

		return null;
	}

	@Override
	public Result<Boolean> checkUpdate(String dataObjectCode, Long ownerId) throws BaseException {
		return this.check(dataObjectCode, ownerId, TenantUrl.GHECK_DATA_OBJECT_PERMISSION_UPDATE);
	}

	@Override
	public Result<Boolean> checkRead(String dataObjectCode, Long ownerId) throws BaseException {
		return this.check(dataObjectCode, ownerId, TenantUrl.GHECK_DATA_OBJECT_PERMISSION_READ);
	}

	@Override
	public Result<List<DataPermissionDto>> checkRead(List<PermissionMultiCodeDto> dtos) throws BaseException {
		Map<String, Object> params = new HashMap<>(1);
		params.put("jsonReqDto", JsonUtils.objectToJson(dtos));
		return HttpUtils.get(TenantUrl.CHECK_DATA_OBJECT_PERMISSION_MULTI_CODE, params,
				new BaseTypeReference<Result<List<DataPermissionDto>>>() {
				});
	}

	private Result<Boolean> check(String dataObjectCode, Long ownerId, String url) throws BaseException {
		log.begin(dataObjectCode, ownerId, url);
		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("dataObjectCode", dataObjectCode);
		paramMap.put("ownerId", ownerId);
		Result<Boolean> result = HttpUtils.get(url, paramMap, new BaseTypeReference<Result<Boolean>>() {
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	private <T> List<Long> fetchOwnerId(List<T> ls) throws BaseException {
		List<Long> ownerIdList = new ArrayList<>(ls.size());
		for (T t : ls) {
			Object ownerObj = AccessCommon.getOwner(t);
			if (ownerObj != null) {
				if (ownerObj instanceof Long) {
					if (!ownerIdList.contains(ownerObj)) {
						ownerIdList.add((Long) ownerObj);
					}
				} else if (ownerObj instanceof List) {
					List<Long> ownerIds = (List<Long>) ownerObj;
					for (Long ownerId : ownerIds) {
						if (!ownerIdList.contains(ownerId)) {
							ownerIdList.add(ownerId);
						}
					}
				}

			}

		}
		return ownerIdList;
	}

	/**
	 * 当无删除权限权限，生成需要返回的提示结果
	 *
	 * @param ls
	 *            批量删除时，记录列表
	 * @param t
	 *            当前记录
	 * @param result
	 *            提示结果
	 */
	private <T> void noPermission(List<T> ls, T t, PermissionResultDto result) throws BaseException {
		// 删除的是单条记录，直接返回告诉调用方
		if (ls.size() == 1)
			throw new BaseException(CodeConstant.CODE_1220015);

		Object id = AccessCommon.getId(t);
		Object name = AccessCommon.getName(t);
		FailListDto failDto = new FailListDto();
		failDto.setCode(CodeConstant.CODE_1220019);
		failDto.setId(id != null ? (Long) id : null);
		failDto.setName(name != null ? name.toString() : "");
		result.getFailList().add(failDto);
	}

	/**
	 * 校验通过的记录
	 *
	 * @param t
	 *            当前记录
	 * @param result
	 *            提示结果
	 */
	private <T> void passPermission(T t, PermissionResultDto result) throws BaseException {
		Object id = AccessCommon.getId(t);
		Object name = AccessCommon.getName(t);
		PassListDto passDto = new PassListDto();
		passDto.setId(id != null ? (Long) id : null);
		passDto.setName(name != null ? name.toString() : "");
		result.getPassList().add(passDto);
	}

	private List<PermissionListResDto> httpCheckDeleteList(String dataObjectCode, List<Long> ownerIdList)
			throws BaseException {
		Map<String, Object> paramMap = new HashMap<>(2);
		paramMap.put("dataObjectCode", dataObjectCode);
		paramMap.put("ownerIds", SymbolUtils.longToStr(ownerIdList));
		Result<List<PermissionListResDto>> result = HttpUtils.get(TenantUrl.CHECK_DATA_OBJECT_PERMISSION_DELETE_LIST,
				paramMap, new BaseTypeReference<Result<List<PermissionListResDto>>>() {
				});
		return result.getResult();
	}

}
