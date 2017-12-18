

package com.yundao.tenant.app.service.fieldgroup;

import java.util.List;

import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.fieldgroup.FieldGroupDto;

/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月21日 下午2:19:25 
 * @author   wucq
 * @version   
 */
public interface FieldGroupService {
  public List<FieldGroupDto> getAll() throws BaseException;
}

