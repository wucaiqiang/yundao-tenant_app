package com.yundao.tenant.app.service.report.declaration;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.ItemListDTO;
import com.yundao.tenant.app.dto.report.UserDeclarationRankReqDto;

public interface UserDeclarationRankService {
    
	
	public Result<ItemListDTO> getUserRankPage(UserDeclarationRankReqDto reqDto)throws BaseException;
}
