
package com.yundao.tenant.app.service.user.detail.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.UserBaseData;
import com.yundao.tenant.app.dto.common.ShareInfo;
import com.yundao.tenant.app.dto.user.UserCardResDto;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.service.user.detail.UserDetailService;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.ThreadContextUtils;
import com.yundao.tenant.app.view.user.detail.UserCardView;

/**
 * Function: Reason: Date: 2017年9月21日 下午2:46:32
 * 
 * @author wucq
 * @version
 */
@Service
public class UserDetailServiceImpl extends AbstractService implements UserDetailService {
	@Autowired
	private ShareService shareService;

	@Override
	public Result<UserCardView> get() throws BaseException {
		UserCardView view = new UserCardView();

		Result<UserCardResDto> result = HttpUtils.get(TenantUrl.USER_DETAIL_GET_CARD, null,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});
		if (result.getResult() != null) {

			UserCardResDto dto = result.getResult();
			String cardIntroduce = "财富管理专家，专属优质服务";
			if (StringUtils.isNotBlank(dto.getCardIntroduce())) {
				cardIntroduce = dto.getCardIntroduce();
			}
			view.setCompany(dto.getTenantName());
			view.setDes(cardIntroduce);
			view.setHeaderBit(dto.getCardHeaderBit());
			view.setName(dto.getRealName());
			view.setAvatar(dto.getAvatar());
			view.setLeftBtnText(dto.getMobile());
			view.setRightBtnText("选TA做顾问");
			view.setMobile(dto.getMobile());
			// view.setDepartmentName(dto.getDepartmentName());
			/*
			 * List<UserRoleDto> roleDtos=dto.getRoles(); if(roleDtos !=null &&
			 * !roleDtos.isEmpty()){ List<String> roleTexts=new ArrayList<>();
			 * for(UserRoleDto role:roleDtos){
			 * roleTexts.add(role.getRoleName()); } view.setRoleList(roleTexts);
			 * }
			 */
			ShareInfo shareInfo = new ShareInfo();
			shareInfo.setTitle("财富管理专家，专属优质服务");
			shareInfo.setContent("来自" + dto.getRealName() + "的名片，专业顾问为您服务");
			shareInfo.setImgUrl(dto.getAvatar());
			String url = String.format(H5Constant.AFP_CARD, shareService.getCCShareDomainUrl(), dto.getCardUUID());
			shareInfo.setUrl(url);
			view.setShareInfo(shareInfo);
			view.setEmail(dto.getEmail());
			view.setAddress(dto.getAddress());

		}
		return Result.newSuccessResult(view);
	}

	@Override
	public Result<Long> updateCardIntroduce(String cardIntroduce) throws BaseException {
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("cardIntroduce", cardIntroduce);
		Result<Long> result = HttpUtils.post(TenantUrl.UPDATE_CARD_INTRODUCE, methodParams,
				new BaseTypeReference<Result<Long>>() {
				});
		return result;

	}

	@Override
	public Result<Long> updateCardIntroduceAndHeaderBit(String cardIntroduce, String headerBit) throws BaseException {

		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("cardIntroduce", cardIntroduce);
		methodParams.put("headerBit", headerBit);
		Result<Long> result = HttpUtils.post(TenantUrl.UPDATE_CARD_INTRODUCE_AND_HEADER_BIT, methodParams,
				new BaseTypeReference<Result<Long>>() {
				});
		return result;

	}

	public Result<UserCardResDto> getUserCardResDto() throws BaseException {
		Result<UserCardResDto> result = HttpUtils.get(TenantUrl.USER_DETAIL_GET_CARD, null,
				new BaseTypeReference<Result<UserCardResDto>>() {
				});
		if (!result.getSuccess()) {
			throw new BaseException(result.getCode(), result.getMessage());
		}
		return result;
	}

	/**
	 * 获取当前用户的uuid getCurrentCardUUID:
	 * 
	 * @author: 欧阳利
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public String getCurrentCardUUID() throws BaseException {
		Result<UserCardResDto> result = getUserCardResDto();
		if (result.getResult() != null) {
			return result.getResult().getCardUUID();
		}
		return null;
	}

}
