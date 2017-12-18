

package com.yundao.tenant.app.service.user.customer;
import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.view.customer.UserCustomerFocusView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月17日 下午8:46:44 
 * @author   wucq
 * @version   
 */
public interface UserCustomerFocusService {
	 public Result<UserCustomerFocusView> focus(String customerId,Boolean requestConcern) throws BaseException ;
}

