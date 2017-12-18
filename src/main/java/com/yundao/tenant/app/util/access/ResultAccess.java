package com.yundao.tenant.app.util.access;


import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.core.listener.SystemListener;
import com.yundao.core.pagination.PaginationSupport;
import com.yundao.tenant.app.service.permission.ResultAccessService;

import java.util.List;

/**
 * 查询操作，数据权限
 *
 * @author jan
 * @create 2017-07-29 PM10:13
 **/
public class ResultAccess {

    /**
     * 单条数据 读取操作
     */
    public static <T> Result<T> newSuccessResult(Result<T> t) throws BaseException {
        getService().access(t.getResult());
        return t;
    }

    /**
     * 单条数据 读取操作
     *
     * @param t
     * @param ownerId 数据的拥有人id，如果赋值，则表示所有数据的ownerid为此id，注解@Owner无效
     */
    public static <T> Result<T> newSuccessResult(Result<T> t, Long ownerId) throws BaseException {
        getService().access(t.getResult(), ownerId);
        return t;
    }

    /**
     * 列表 读取操作
     */
    public static <T> Result<List<T>> newSuccessResultList(Result<List<T>> ts) throws BaseException {
        return getService().processList(ts.getResult());

    }

    /**
     * 列表 读取操作
     *
     * @param ts      列表数据
     * @param ownerId 数据的拥有人id，如果赋值，则表示所有数据的ownerid为此id，注解@Owner无效
     */
    public static <T> Result<List<T>> newSuccessResultList(Result<List<T>> ts, Long ownerId) throws BaseException {
        return getService().processList(ts.getResult(), ownerId);

    }


    /**
     * 分页数据权限
     *
     * @param pts 列表数据
     */
    public static <T> Result<PaginationSupport<T>> newSuccessResultPage(Result<PaginationSupport<T>> pts) throws
            BaseException {
        getService().processList(pts.getResult().getDatas());
        return pts;
    }

    /**
     * 分页数据权限
     *
     * @param pts     列表数据
     * @param ownerId 数据的拥有人id，如果赋值，则表示所有数据的ownerid为此id，注解@Owner无效
     */
    public static <T> Result<PaginationSupport<T>> newSuccessResultPage(Result<PaginationSupport<T>> pts, Long ownerId)
            throws
            BaseException {
        getService().processList(pts.getResult().getDatas(), ownerId);
        return pts;
    }

    /**
     * 获取服务实例
     */
    private static ResultAccessService getService() {
        return SystemListener.getBean("resultAccessServiceImpl", ResultAccessService.class);
    }

}
