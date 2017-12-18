

package com.yundao.tenant.app.service.user.card;

import org.springframework.web.multipart.MultipartFile;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.view.user.card.CardScanInfoView;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年11月24日 下午4:12:21 
 * @author   wucq
 * @version   
 */
public interface CardScanService {
	public Result<CardScanInfoView> getCardInfo(MultipartFile file) throws BaseException ;
}

