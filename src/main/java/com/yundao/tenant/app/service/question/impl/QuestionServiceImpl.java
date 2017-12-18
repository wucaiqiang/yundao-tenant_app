
package com.yundao.tenant.app.service.question.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.core.service.AbstractService;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.ConfigUtils;
import com.yundao.core.utils.DateUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.constant.schema.H5Constant;
import com.yundao.tenant.app.constant.schema.Schema;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.aa.ShareInfoDto;
import com.yundao.tenant.app.dto.aa.asset.AssetAllocationPageResDto;
import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.common.ViewItem;
import com.yundao.tenant.app.dto.customer.risk.CustomerRiskPageReqDto;
import com.yundao.tenant.app.dto.customer.risk.CustomerRiskPageResDto;
import com.yundao.tenant.app.dto.question.CustomerRiskQuestionResDto;
import com.yundao.tenant.app.dto.question.CustomerRiskResDto;
import com.yundao.tenant.app.service.question.QuestionService;
import com.yundao.tenant.app.service.share.ShareService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.util.SymbolUtils;
import com.yundao.tenant.app.util.UserUtils;
import com.yundao.tenant.app.view.TitleAndContentView;
import com.yundao.tenant.app.view.aa.Structure9View;
import com.yundao.tenant.app.view.question.AfpRiskEvalationView;
import com.yundao.tenant.app.view.question.RiskEvalationView;

/**
 * Function: Reason: Date: 2017年9月12日 上午11:23:48
 * 
 * @author wucq
 * @version
 */
@Service
public class QuestionServiceImpl extends AbstractService implements QuestionService {

	@Autowired
	ShareService shareService;

	@Override
	public Result<RiskEvalationView> getRiskEvaluationByCustomerId(Long customerId) throws Exception {
		RiskEvalationView view = new RiskEvalationView();

		// 查询客户自己做的风险测评
		Map<String, Object> methodParams = new HashMap<>();
		methodParams.put("customerId", customerId);
		Result<CustomerRiskResDto> result = HttpUtils.get(TenantUrl.QUESTION_GET_RIST_EVALUATION, methodParams,
				new BaseTypeReference<Result<CustomerRiskResDto>>() {
				});

		// 查询理财师为客户所做的风险测评
		Map<String, Object> evaluateMap = new HashMap<>();
		evaluateMap.put("userId", UserUtils.getUserId());
		evaluateMap.put("customerId", customerId);
		evaluateMap.put("currentPage", 1);
		evaluateMap.put("pageSize", Integer.MAX_VALUE);
		Result<PaginationSupport<AssetAllocationPageResDto>> evaluateResult = HttpUtils.get(TenantUrl.ASSET_GET_PAGE,
				evaluateMap, new BaseTypeReference<Result<PaginationSupport<AssetAllocationPageResDto>>>() {
				});
		if (result != null && result.getResult() != null) {
			CustomerRiskResDto dto = result.getResult();
			view.setTitle("客户风险测评结果：");
			view.setType(dto.getRiskText());
			String score = dto.getGrade() == null ? "0" : dto.getGrade().toString();
			view.setScore(score);
			view.setTestTime(dto.getEvaluationTime());
			List<CustomerRiskQuestionResDto> questionResDtos = dto.getQuestionDtos();
			if (questionResDtos != null && !questionResDtos.isEmpty()) {
				List<TitleAndContentView> titleAndContentViews = new ArrayList<>();
				view.setTestSubjects(titleAndContentViews);
				for (CustomerRiskQuestionResDto resDto : questionResDtos) {
					titleAndContentViews.add(new TitleAndContentView(resDto.getQuestionText(), resDto.getOptionText()));
				}
			}

		}
		if (evaluateResult.getResult() != null) {
			PaginationSupport<AssetAllocationPageResDto> paginationSupport = evaluateResult.getResult();
			if (paginationSupport.getDatas() != null && !paginationSupport.getDatas().isEmpty()) {
				view.setEvaluateExamResultTitle("我为客户配置的方案");
				List<AfpRiskEvalationView> evaluateExamResultList = new ArrayList<>();
				List<AssetAllocationPageResDto> allocationPageResDtos = paginationSupport.getDatas();
				for (AssetAllocationPageResDto resDto : allocationPageResDtos) {
					AfpRiskEvalationView riskEvalationView = new AfpRiskEvalationView();
					riskEvalationView.setType(resDto.getRiskText());
					riskEvalationView.setProductNum(String.valueOf(resDto.getProductCount()));
					riskEvalationView.setCreateDate(resDto.getCreateDate());

					// 设置分享地址
					ShareInfoDto shareInfoDto = new ShareInfoDto();
					String url = String.format(H5Constant.RISK_SCHEME, shareService.getCCShareDomainUrl(),
							resDto.getId(), false);
					shareInfoDto.setUrl(url);
					shareInfoDto.setUrl(URLEncoder.encode(shareInfoDto.getUrl(), "UTF-8"));
					shareInfoDto.setContent(UserUtils.getCurrentUser().getHeaderRealName() + "为您评估风险偏好，量身打造适合您的资产配置方案");
					shareInfoDto.setTitle(UserUtils.getCurrentUser().getHeaderRealName() + " 为您量身定制的资产配置方案");
					shareInfoDto.setImgUrl(ConfigUtils.getValue("user.default.avater.url"));

					String shareInfo = JsonUtils.objectToJson(shareInfoDto);
					String productIds = "";
					if (!BooleanUtils.isEmpty(resDto.getProductIds())) {
						productIds = SymbolUtils.longToStr(resDto.getProductIds());
					}
					String shareUrl = URLEncoder.encode(String.format(H5Constant.RISK_SCHEME,
							shareService.getCCShareDomainUrl(), resDto.getId(), true), "UTF-8");
					String jumpUrl = Schema.EVALUATE_MANAGER_RESULT + String.format(
							Schema.EVALUATE_MANAGER_RESULT_PARAMS, resDto.getId(), shareInfo, productIds, shareUrl);
					ActionDTO action = new ActionDTO(jumpUrl);
					riskEvalationView.setAction(action);

					evaluateExamResultList.add(riskEvalationView);
				}
				view.setEvaluateExamResultList(evaluateExamResultList);

			}
		}
		return Result.newSuccessResult(view);

	}

	/**
	 * 获取我的客户分析测评情况page getCustomerRiskPage:
	 * 
	 * @author: 欧阳利
	 * @return
	 * @throws BaseException
	 * @throws UnsupportedEncodingException
	 * @description:
	 */
	public Result<ItemListDTO> getMyCustomerRiskPage(CustomerRiskPageReqDto reqDto) throws Exception {
		reqDto.setUserId(UserUtils.getUserId());

		// 获取数据
		Result<PaginationSupport<CustomerRiskPageResDto>> result = HttpUtils.get(
				TenantUrl.QUESTION_GET_RIST_EVALUATION_PAGE, ArgsUtils.toMap(reqDto),
				new BaseTypeReference<Result<PaginationSupport<CustomerRiskPageResDto>>>() {
				});

		if (!result.getSuccess()) {
			return Result.newFailureResult(result.getCode(), result.getMessage(), null);
		}

		PaginationSupport<CustomerRiskPageResDto> page = result.getResult();
		List<ViewItem> viewItems = new ArrayList<ViewItem>();
		if (!BooleanUtils.isEmpty(page.getDatas())) {

			for (CustomerRiskPageResDto dto : page.getDatas()) {
				Structure9View structure9View = new Structure9View();
				structure9View.setId(dto.getCustomerId());
				structure9View.setName(dto.getCustomerName());
				structure9View.setEvaluationTypeText(dto.getRiskText());
				structure9View.setHasReservation(dto.getHasReservation());
				if (dto.getRiskValue() != null) {
					structure9View.setHasEvaluate(true);
					structure9View.setDate(DateUtils.format(dto.getRiskEvaluationDate(), DateUtils.YYYY_MM_DD));
					structure9View.setDateTitle("配置时间");
				} else {
					structure9View.setHasEvaluate(false);
				}

				String url = String.format(H5Constant.RISK_SCHEME_CUSTOMER, shareService.getCCShareDomainUrl(),
						dto.getCustomerId(), false);
				url = URLEncoder.encode(url, "UTF-8");
				String resultUrl = String.format(Schema.H5_PAGE + Schema.H5_PAGE_PARAMS, url,
						URLEncoder.encode("智能资产配置", "UTF-8"));
				viewItems.add(new ViewItem(9, resultUrl, structure9View));
			}

		}
		ItemListDTO dto = new ItemListDTO(viewItems);
		dto.setPaginaton(page);
		return Result.newSuccessResult(dto);
	}

}
