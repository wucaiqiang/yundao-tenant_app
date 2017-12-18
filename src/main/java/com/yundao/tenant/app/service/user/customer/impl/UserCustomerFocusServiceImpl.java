

package com.yundao.tenant.app.service.user.customer.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.service.user.customer.UserCustomerFocusService;
import com.yundao.tenant.app.util.ArgsUtils;
import com.yundao.tenant.app.util.HttpUtils;
import com.yundao.tenant.app.view.customer.UserCustomerFocusView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月17日 下午8:47:28 
 * @author   wucq
 * @version   
 */
@Service
public class UserCustomerFocusServiceImpl extends AbstractService implements UserCustomerFocusService {
	@Override
	public Result<UserCustomerFocusView> focus(String customerId,Boolean requestConcern) throws BaseException {
		String url="";
		Map<String, Object> methodParams=new HashMap<>();
		if(requestConcern){
			url=TenantUrl.FOCUS_CUSTOMER;
			methodParams=ArgsUtils.toIdMap(customerId, "customerIds");
		}else {
			url=TenantUrl.UNFOCUS_CUSTOMER;
			methodParams=ArgsUtils.toIdMap(customerId, "customerId");
		}
		Result<Boolean> result=HttpUtils.post(url,methodParams , new BaseTypeReference<Result<Boolean>>() {
        });
		
		UserCustomerFocusView view=new UserCustomerFocusView();
		view.setCustomerId(customerId);
		if(result !=null && result.getResult() !=null){
			view.setConcernStatus(result.getResult());
		}
 		 return Result.newSuccessResult(view);
 				 
	}

}

