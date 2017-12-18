

package com.yundao.tenant.app.service.cc.ccriskrating.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.model.cc.ccriskrating.CcRiskRatingModel;
import com.yundao.tenant.app.service.cc.ccriskrating.CcRiskRatingService;
import com.yundao.tenant.app.util.HttpUtils;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年10月12日 下午2:11:18 
 * @author   wucq
 * @version   
 */
@Service
public class CcRiskRatingServiceimpl extends AbstractService implements CcRiskRatingService {

	@Override
	public Result<List<CcRiskRatingModel>> getList() throws BaseException {
		
		return HttpUtils.get(TenantUrl.CC_RISK_RATING_GETS, null, new BaseTypeReference<Result<List<CcRiskRatingModel>>>() {
		});
		
	}

	
}

