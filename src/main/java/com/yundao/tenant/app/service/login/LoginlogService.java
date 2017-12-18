package com.yundao.tenant.app.service.login;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.login.LoginLogAddReqDto;
import com.yundao.tenant.app.dto.login.UserLoginResDto;
import com.yundao.tenant.app.dto.login.UserloginReqDto;
import com.yundao.tenant.app.model.tenant.TenantModel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gjl on 2017/12/1.
 */
public interface LoginlogService {
    void logError(UserloginReqDto loginReqDto, HttpServletRequest request, String message,List<TenantModel> tenantModels) throws BaseException;

    void logSuccess(HttpServletRequest request) throws BaseException;

}
