
package com.yundao.tenant.app.service.dictionary.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.DictionaryCodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.common.LabelValueDto;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.dto.fieldgroup.FieldGroupDto;
import com.yundao.tenant.app.model.cc.ccriskrating.CcRiskRatingModel;
import com.yundao.tenant.app.service.cc.ccriskrating.CcRiskRatingService;
import com.yundao.tenant.app.service.dictionary.DictionaryService;
import com.yundao.tenant.app.service.fieldgroup.FieldGroupService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.NameIdView;
import com.yundao.tenant.app.view.dictionary.DictionaryItemsView;
import com.yundao.tenant.app.view.dictionary.FollowUpView;

/**
 * Function: Reason: Date: 2017年8月21日 上午10:43:52
 * 
 * @author wucq
 * @version
 */
@Service
public class DictionaryServiceImpl extends AbstractService implements DictionaryService {
	@Autowired
	private FieldGroupService fieldGroupService;
	@Autowired
	private CcRiskRatingService ccRiskRatingService;

	@Override
	public Result<DictionaryItemsView> getCustomerRiskRating() throws BaseException {

		DictionaryItemsView customerInvestView = new DictionaryItemsView();
		// 风险特征
		Result<List<CcRiskRatingModel>> ratingModelResult = ccRiskRatingService.getList();
		if (ratingModelResult.getSuccess() && ratingModelResult.getResult() != null) {
			List<CcRiskRatingModel> models = ratingModelResult.getResult();
			if (models != null && !models.isEmpty()) {
				List<NameIdView> idViews = new ArrayList<>();
				for (CcRiskRatingModel model : models) {
					idViews.add(new NameIdView(model.getLabel(), String.valueOf(model.getValue())));
				}
				customerInvestView.setItems(idViews);
			}
		}

		return Result.newSuccessResult(customerInvestView);

	}

	@Override
	public Result<DictionaryItemsView> getCustomerInvest() throws BaseException {
		DictionaryItemsView customerInvestView = new DictionaryItemsView();
		// 投资偏好
		List<FieldGroupDto> fieldGroupDtos = fieldGroupService.getAll();
		if (fieldGroupDtos != null && !fieldGroupDtos.isEmpty()) {
			List<NameIdView> idViews = new ArrayList<>();
			for (FieldGroupDto fieldGroupDto : fieldGroupDtos) {
				idViews.add(new NameIdView(fieldGroupDto.getName(), String.valueOf(fieldGroupDto.getId())));
			}
			customerInvestView.setItems(idViews);
		}
		return Result.newSuccessResult(customerInvestView);

	}

	public Result<List<DictionaryDto>> gets(String codes) throws BaseException {
		return HttpUtils.get(TenantUrl.GET_Dictionaries, ArgsUtils.toIdMap(codes, "codes"),
				new BaseTypeReference<Result<List<DictionaryDto>>>() {
				});
	}

	@Override
	public Result<String> getRegionText(String code) throws BaseException {

		return HttpUtils.get(TenantUrl.GET_REGION_TEXT, ArgsUtils.toIdMap(code, "code"),
				new BaseTypeReference<Result<String>>() {
				});

	}

	@Override
	public Result<DictionaryItemsView> getCustomerLevel() throws BaseException {
		DictionaryItemsView itemsView = new DictionaryItemsView();

		String[] codes = new String[1];
		codes[0] = DictionaryCodeConstant.CUSTOMER_LEVEL;// 跟进方式
		Result<List<DictionaryDto>> dictionaryResult = this.gets(StringUtils.join(codes, ","));
		if (dictionaryResult != null && dictionaryResult.getResult() != null) {
			List<DictionaryDto> dictionaryDtos = dictionaryResult.getResult();
			if (dictionaryDtos != null && !dictionaryDtos.isEmpty()) {
				for (DictionaryDto dto : dictionaryDtos) {
					List<LabelValueDto> labelValueDtos = dto.getSelections();
					if (labelValueDtos != null && !labelValueDtos.isEmpty()) {
						List<NameIdView> idViews = new ArrayList<>();
						for (LabelValueDto labelValueDto : labelValueDtos) {
							idViews.add(new NameIdView(labelValueDto.getLabel(), labelValueDto.getValue()));
						}
						if (dto.getValue().equals(DictionaryCodeConstant.CUSTOMER_LEVEL)) {// 客户性别下拉选项
							itemsView.setItems(idViews);
						}
					}
				}
			}
		}
		return Result.newSuccessResult(itemsView);

	}

	@Override
	public Result<FollowUpView> getFollowUpStatusAndType() throws BaseException {
		String[] codes = new String[2];
		codes[0] = DictionaryCodeConstant.FOLLOW_TYPE;// 跟进方式
		codes[1] = DictionaryCodeConstant.FOLLOW_STATUS;// 跟进状态
		FollowUpView followUpView = new FollowUpView();

		Result<List<DictionaryDto>> dictionaryResult = this.gets(StringUtils.join(codes, ","));

		if (dictionaryResult != null && dictionaryResult.getResult() != null) {
			List<DictionaryDto> dictionaryDtos = dictionaryResult.getResult();
			if (dictionaryDtos != null && !dictionaryDtos.isEmpty()) {
				for (DictionaryDto dto : dictionaryDtos) {
					List<LabelValueDto> labelValueDtos = dto.getSelections();
					if (labelValueDtos != null && !labelValueDtos.isEmpty()) {
						List<NameIdView> idViews = new ArrayList<>();
						for (LabelValueDto labelValueDto : labelValueDtos) {
							idViews.add(new NameIdView(labelValueDto.getLabel(), labelValueDto.getValue()));
						}
						if (dto.getValue().equals(DictionaryCodeConstant.FOLLOW_TYPE)) {// 客户性别下拉选项
							followUpView.setModes(idViews);
						} else if (dto.getValue().equals(DictionaryCodeConstant.FOLLOW_STATUS)) {// 客户级别下拉选项
							followUpView.setStateList(idViews);
						}
					}
				}
			}
		}
		return Result.newSuccessResult(followUpView);

	}

}
