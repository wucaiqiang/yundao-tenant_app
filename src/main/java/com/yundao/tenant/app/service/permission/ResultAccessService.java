package com.yundao.tenant.app.service.permission;


import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;

import java.util.List;

/**
 * 查询操作数据权限服务接口
 *
 * @author jan
 * @create 2017-07-29 PM8:27
 **/
public interface ResultAccessService {


    /**
     * 列表数据权限
     */
    <T> Result<List<T>> processList(List<T> ls) throws BaseException;

    /**
     * 权限校验
     *
     * @param t 数据实体
     */
    <T> Result<T> access(T t) throws BaseException;


    /**
     * 列表数据权限
     *
     * @param ls      数据类别
     * @param ownerId 数据的拥有人id，如果赋值，则表示所有数据的ownerid为此id，注解@Owner无效
     */
    <T> Result<List<T>> processList(List<T> ls, Long ownerId) throws BaseException;

    /**
     * 权限校验
     *
     * @param t       数据实体
     * @param ownerId 数据的拥有人id，如果赋值，则表示所有数据的ownerid为此id，注解@Owner无效
     */
    <T> Result<T> access(T t, Long ownerId) throws BaseException;



}
