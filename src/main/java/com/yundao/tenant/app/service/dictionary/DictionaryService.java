
package com.yundao.tenant.app.service.dictionary;

import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.dictionary.DictionaryDto;
import com.yundao.tenant.app.view.dictionary.DictionaryItemsView;
import com.yundao.tenant.app.view.dictionary.FollowUpView;

/**
 * Function: Reason: Date: 2017年8月21日 上午10:43:17
 * 
 * @author wucq
 * @version
 */
public interface DictionaryService {
	public Result<List<DictionaryDto>> gets( String codes) throws BaseException ;
	/**
	 * 跟进状态
	 * getFollowUpStatusAndType:
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<FollowUpView> getFollowUpStatusAndType() throws BaseException ;
	public Result<DictionaryItemsView> getCustomerLevel() throws BaseException ;
	/**
	 * 客户投资偏好
	 * getCustomerInvest:
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<DictionaryItemsView> getCustomerInvest() throws BaseException ;
	/**
	 * 客户风险特征
	 * getCustomerRiskRating:
	 * @author: wucq
	 * @return
	 * @throws BaseException
	 * @description:
	 */
	public Result<DictionaryItemsView> getCustomerRiskRating() throws BaseException ;
	
	public Result<String> getRegionText(String code)throws BaseException;
}
