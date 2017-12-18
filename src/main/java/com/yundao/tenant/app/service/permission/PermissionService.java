package com.yundao.tenant.app.service.permission;

import java.util.List;

import com.yundao.core.code.Result;
import com.yundao.core.exception.BaseException;
import com.yundao.tenant.app.dto.common.DataPermissionDto;
import com.yundao.tenant.app.dto.common.PermissionResultDto;
import com.yundao.tenant.app.dto.permission.PermissionListResDto;
import com.yundao.tenant.app.dto.permission.PermissionMultiCodeDto;
import com.yundao.tenant.app.dto.permission.PermissionObjectDto;
import com.yundao.tenant.app.dto.product.BaseObjectDto;

/**
 * 通用权限校验服务接口
 *
 * @author jan
 * @create 2017-08-06 PM1:06
 **/
public interface PermissionService {

    /**
     * 获取读取权限
     *
     * @param dataObjectCode 数据对象编码
     */
    Result<Integer> getRead(String dataObjectCode) throws BaseException;
    /**
     * 获取更新权限
     *
     * @param dataObjectCode 数据对象编码
     */
    Result<Integer> getUpdate(String dataObjectCode) throws BaseException;
    
    /**
     * 检查是否有权限修改
     */
    Result<PermissionResultDto> checkUpdateListForOwnerIds(List<BaseObjectDto> baseObjets, String dataObjectCode) throws BaseException;
    public PermissionListResDto checkUpdateListSingleObj(String dataObjectCode, List<Long> ownerIdList)
			throws BaseException ;
    /**
     * 校验删除多条数据权限
     *
     * @param ls             需要删除的多条数据
     * @param dataObjectCode 数据对象编码
     */
    <T> Result<PermissionResultDto> checkDeleteList(List<T> ls, String dataObjectCode) throws BaseException;
    
    /**
     * 检查是否有权限删除
     * @param ls
     * @param dataObjectCode
     * @return
     * @throws BaseException
     */
     public  Result<PermissionResultDto> checkDeleteListForOwnerIds(List<PermissionObjectDto> baseObjets, String dataObjectCode) throws BaseException;
    /**
     * 校验更新权限
     *
     * @param dataObjectCode 数据对象编码
     * @param ownerId        数据拥有者id
     */
    Result<Boolean> checkUpdate(String dataObjectCode, Long ownerId) throws BaseException;

    /**
     * 校验读取权限
     *
     * @param dataObjectCode 数据对象编码
     * @param ownerId        数据拥有者id
     */
    Result<Boolean> checkRead(String dataObjectCode, Long ownerId) throws BaseException;

    /**
     * 校验多个数据对象权限
     */
    Result<List<DataPermissionDto>> checkRead(List<PermissionMultiCodeDto> dtos) throws BaseException;
}
