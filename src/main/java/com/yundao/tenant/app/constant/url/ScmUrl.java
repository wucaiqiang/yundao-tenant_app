package com.yundao.tenant.app.constant.url;

import com.yundao.core.utils.ConfigUtils;
import com.yundao.tenant.app.enums.url.UrlEnum;

/**
 * 公共服务url
 *
 * @author jan
 * @create 2017-06-30 PM8:27
 **/
public interface ScmUrl {

    /**
     * HOST地址
     */
    String BASE_URL = ConfigUtils.getValue(UrlEnum.SCM_URL.getKey());

    /**
     * 根据CODE编码获取租户信息
     */
    String GET_BY_TENANT_CODE = BASE_URL + "/tenant/get_by_code";
    String GETS_BY_ACCOUNT_ID = BASE_URL + "/tenant/gets_by_account_id";
    String GETS_BY_ACCOUNT = BASE_URL + "/tenant/gets_by_account";
    /**
     * 获取用户域名
     */
    String GETS_DOMAIN_BY_CODE_AND_TYPE = BASE_URL + "/tenant/get_by_code_and_type";
    /**
     * 获取租户密钥对
     */
    String KEY_PAIR_GET_BY_TENANT_ID = BASE_URL + "/keypair/get_by_tenant_id";
    String USER_LOGIN = BASE_URL + "/user/login";
    String USER_SEND_RETRIEVE_CAPTCHA = BASE_URL + "/user/send_retrieve_captcha";
    /**
     * 修改密码
     */
    String USER_UPDATE_PASSWORD = BASE_URL + "/user/update_password";
    String USER_CHECK_PASSWORD = BASE_URL + "/user/check_password";
    String USER_RETRIEVE_PASSWORD = BASE_URL + "/user/retrieve_password";
    String USER_CHECK_MOBILE = BASE_URL + "/user/check_mobile_exist";
    String USER_LOGINOUT = BASE_URL + "/user/logout";
    String GET_BY_ACCOUNT = BASE_URL + "/user/get_by_account";
    String TICKET_VALIDATE = BASE_URL + "/ticket/validate";
    String GET_USER_ACCOUNT_BY_TICKET = BASE_URL + "/ticket/get_user_account";
    String TICKET_REFRESH = BASE_URL + "/ticket/refresh";
    /**
     * 获取所有可用的租户域名
     */
    String GET_TENANT_DOMAIN = BASE_URL + "/domainname/get_tenant_domain";
}
