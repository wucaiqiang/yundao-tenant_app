
package com.yundao.tenant.app.service.user.detail;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.user.UserCardResDto;
import com.yundao.tenant.app.view.user.detail.UserCardView;

/**
 * Function: Reason: Date: 2017年9月21日 下午2:45:57
 * 
 * @author wucq
 * @version
 */
public interface UserDetailService {
	public Result<UserCardView> get() throws BaseException;

	public Result<Long> updateCardIntroduce(String cardIntroduce) throws BaseException;

	public Result<Long> updateCardIntroduceAndHeaderBit(String cardIntroduce, String headerBit) throws BaseException;
	
	public Result<UserCardResDto> getUserCardResDto()throws BaseException;
	
	public String getCurrentCardUUID()throws BaseException;
}
