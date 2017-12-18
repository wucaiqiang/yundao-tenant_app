

package com.yundao.tenant.app.service.question;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.customer.risk.CustomerRiskPageReqDto;
import com.yundao.tenant.app.view.question.RiskEvalationView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年9月12日 上午11:23:14 
 * @author   wucq
 * @version   
 */
public interface QuestionService {
	public Result<RiskEvalationView> getRiskEvaluationByCustomerId(Long customerId) throws Exception ;
	
	
	
	/**
	 * 获取我的客户分析测评情况page
	 * getCustomerRiskPage:
	 * @author: 欧阳利
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<ItemListDTO>  getMyCustomerRiskPage(CustomerRiskPageReqDto reqDto) throws Exception ;
}

