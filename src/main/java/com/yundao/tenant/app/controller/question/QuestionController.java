
package com.yundao.tenant.app.controller.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.risk.CustomerRiskPageReqDto;
import com.yundao.tenant.app.service.question.QuestionService;
import com.yundao.tenant.app.view.question.RiskEvalationView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Function: Reason: Date: 2017年9月12日 上午11:09:23
 * 
 * @author wucq
 * @version
 */
@Controller
@RequestMapping("/question")
@Api("题目相关")
@ResponseBody
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/get_rist_evaluation", method = RequestMethod.POST)
	@ApiOperation(value = "获取分析测评结果(客户id)")
	public Result<RiskEvalationView> getRiskEvaluationByCustomerId(@RequestParam Long customerId) throws Exception {
		return questionService.getRiskEvaluationByCustomerId(customerId);
	}
	

	@RequestMapping(value = "/get/my/customer/risk_evaluation_page", method = RequestMethod.POST)
	@ApiOperation(value = "查询当前用户的客户分析测评")
	public Result<ItemListDTO>  getMyCustomerRiskPage(@ModelAttribute CustomerRiskPageReqDto reqDto) throws Exception {
		return questionService.getMyCustomerRiskPage(reqDto);
	}
	
}
