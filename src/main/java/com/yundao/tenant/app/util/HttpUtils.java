package com.yundao.tenant.app.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.constant.HeaderConstant;
import com.yundao.core.constant.MethodConstant;
import com.yundao.core.enums.AppTypeEnum;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.threadlocal.filter.RequestCommonParams;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.EDUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.dto.UserBaseData;

/**
 * HTTP调用工具类
 *
 * @author jan
 * @create 2017-06-21 PM5:16
 **/
public class HttpUtils {

    private static Log log = LogFactory.getLog(HttpUtils.class);

    /**
     * 执行post方法
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> post(String url, Map<String, Object> methodParams,
                                     BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, null, methodParams, typeReference, MethodConstant.POST);
    }

    /**
     * 执行post方法
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> post(String url, Map<String, Object> headerParams, Map<String, Object> methodParams,
                                     BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, headerParams, methodParams, typeReference, MethodConstant.POST);
    }

    /**
     * 执行get方法 ;
     *
     * @param url           url地址
     * @param methodParams  键值形式参数
     * @param typeReference 返回类型信息
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> get(String url, Map<String, Object> methodParams,
                                    BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, null, methodParams, typeReference, MethodConstant.GET);
    }

    /**
     * 执行get方法 ;
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> get(String url, Map<String, Object> headerParams, Map<String, Object> methodParams,
                                    BaseTypeReference<Result<T>> typeReference) throws BaseException {
        return doExecute(url, headerParams, methodParams, typeReference, MethodConstant.GET);
    }

    /**
     * 获取头部参数
     *
     * @return 头部参数
     */
    public static Map<String, String> getHeaderParams() {
        UserBaseData userBaseData = ThreadContextUtils.getUserBaseData();// 保存线程数据
        Map<String, String> result = new HashMap<String, String>();
        if (userBaseData != null && userBaseData.getHeaderTenantId() != null
                && userBaseData.getHeaderTenantId() != null) {
            result.put(HeaderConstant.HEADER_USER_ID, userBaseData.getHeaderUserId().toString());
            result.put(HeaderConstant.HEADER_TENANT_ID, userBaseData.getHeaderTenantId().toString());
            result.put(HeaderConstant.HEADER_REAL_NAME, EDUtils.encode(userBaseData.getHeaderRealName()));
        }
        result.put(HeaderConstant.HEADER_APP_TYPE, AppTypeEnum.B_APP.getType());

        return result;
    }

    /**
     * http请求统一调用
     *
     * @param url           url地址
     * @param headerParams  头部参数
     * @param methodParams  方法参数
     * @param typeReference 返回类型
     * @param method        请求方式
     * @param <T>           返回类型
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    private static <T> Result<T> doExecute(String url, Map<String, Object> headerParams,
                                           Map<String, Object> methodParams, BaseTypeReference<Result<T>> typeReference, String method)
            throws BaseException {

        String response = executeHttp(url, headerParams, methodParams, method);
        try {
            return JsonUtils.jsonToObject(response, typeReference);
        } catch (Exception e) {
            log.error("调用链接时异常", e);
            throw new BaseException(CommonCode.COMMON_1007, e);
        }
    }

    /**
     * 真正请求网络的方法
     *
     * @param url          url地址
     * @param headerParams 请求头参数
     * @param methodParams 方法参数
     * @param method       请求类型
     * @return 返回字符串结果
     */
    private static String executeHttp(String url, Map<String, Object> headerParams, Map<String, Object> methodParams, String method) {

        String response = "";
        try {
            url = url.trim();
            RequestCommonParams commonParams = RequestCommonParams.newDefault();
            commonParams.setMethodParams(methodParams);
            commonParams.setHeaderParams(getHeaderParams());

            if (null != headerParams && !headerParams.isEmpty()) {
                for (Iterator<String> iterator = headerParams.keySet().iterator(); iterator.hasNext(); ) {
                    String name = iterator.next();
                    String value = String.valueOf(headerParams.get(name));
                    if (!BooleanUtils.isBlank(name) && !BooleanUtils.isBlank(value)) {
                        commonParams.addHeaderParams(name, value);
                    }
                }
            }
            log.info("头部参数headerParams=" + commonParams.getHeaderParams() + ",方法参数methodParams=" + methodParams);
            if (MethodConstant.POST.equals(method))
                response = com.yundao.core.utils.HttpUtils.post(url, commonParams);
            else
                response = com.yundao.core.utils.HttpUtils.get(url, commonParams);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    //##################################FastJson解析##################################//


    /**
     * 执行post方法,内部使用FastJson进行解析。
     *
     * @param url          url地址
     * @param methodParams 键值形式参数
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    public static <T> Result<T> postFastJson(String url, Map<String, Object> methodParams,
                                             TypeReference<Result<T>> typeReference) throws BaseException {
        return doExecuteFastJson(url, null, methodParams, typeReference, MethodConstant.POST);
    }

    public static <T> Result<T> getFastJson(String url, Map<String, Object> methodParams,
                                             TypeReference<Result<T>> typeReference) throws BaseException {
        return doExecuteFastJson(url, null, methodParams, typeReference, MethodConstant.GET);
    }


    /**
     * http请求统一调用
     *
     * @param url           url地址
     * @param headerParams  头部参数
     * @param methodParams  方法参数
     * @param typeReference 返回类型
     * @param method        请求方式
     * @param <T>           返回类型
     * @return 返回结果
     * @throws BaseException 异常信息
     */
    private static <T> Result<T> doExecuteFastJson(String url, Map<String, Object> headerParams,
                                                   Map<String, Object> methodParams, TypeReference<Result<T>> typeReference, String method)
            throws BaseException {

        String response = executeHttp(url, headerParams, methodParams, method);
        try {

            return JSON.parseObject(response, typeReference);
        } catch (Exception e) {
            log.error("调用链接时异常", e);
            throw new BaseException(CommonCode.COMMON_1007, e);
        }
    }


}
