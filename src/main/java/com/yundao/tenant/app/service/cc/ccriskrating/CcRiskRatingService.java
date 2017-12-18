

package com.yundao.tenant.app.service.cc.ccriskrating;

import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.model.cc.ccriskrating.CcRiskRatingModel;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年10月12日 下午2:09:32 
 * @author   wucq
 * @version   
 */
public interface CcRiskRatingService {
	public Result<List<CcRiskRatingModel>> getList() throws BaseException ;

}

