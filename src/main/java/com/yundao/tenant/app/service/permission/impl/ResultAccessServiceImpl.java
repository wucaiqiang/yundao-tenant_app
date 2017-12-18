package com.yundao.tenant.app.service.permission.impl;


import com.yundao.core.code.Result;
import com.yundao.core.code.config.CommonCode;
import com.yundao.core.exception.BaseException;
import com.yundao.core.json.jackson.type.BaseTypeReference;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.tenant.app.annotation.FieldCode;
import com.yundao.tenant.app.constant.CodeConstant;
import com.yundao.tenant.app.constant.url.TenantUrl;
import com.yundao.tenant.app.dto.field.DataObjectFieldDto;
import com.yundao.tenant.app.dto.permission.PermissionDetailResDto;
import com.yundao.tenant.app.dto.permission.PermissionListReqDto;
import com.yundao.tenant.app.dto.permission.PermissionListResDto;
import com.yundao.tenant.app.enums.access.DataObjectPermissionEnum;
import com.yundao.tenant.app.enums.dataobject.DataObjectEnum;
import com.yundao.core.service.AbstractService;
import com.yundao.tenant.app.service.permission.PermissionService;
import com.yundao.tenant.app.service.permission.ResultAccessService;
import com.yundao.tenant.app.util.*;
import com.yundao.tenant.app.util.access.AccessCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询操作数据权限服务实现类
 *
 * @author jan
 * @create 2017-07-29 PM8:35
 **/
@Service
public class ResultAccessServiceImpl extends AbstractService implements ResultAccessService {

    private static Log log = LogFactory.getLog(ResultAccessServiceImpl.class);

    @Autowired
    private PermissionService permissionService;

    @Override
    public <T> Result<List<T>> processList(List<T> ls) throws BaseException {
        return this.processList(ls, null);
    }

    @Override
    public <T> Result<T> access(T t) throws BaseException {
        return this.access(t, null);
    }

    @Override
    public <T> Result<List<T>> processList(List<T> ls, Long ownerId) throws BaseException {
        Long startTime = System.currentTimeMillis();
        //获取所有注解的field code
        List<String> fieldCodes = this.fetchFieldCode(ls.get(0));

        //获取field 所属的 数据对象
        List<DataObjectFieldDto> dataObjectFields = this.getDataObjectFields(fieldCodes);

        //调用后置服务，校验权限
        List<PermissionListResDto> resDtos = this.httpCheckReadList(this.buildReqDtos(ls, LambdaMap.toSingleList(dataObjectFields, m -> m.getDataObjectCode()), ownerId));

        Map<String, Integer> dopMap = this.getDataObjectPermissionMap(dataObjectFields);
        for (T t : ls) {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                this.processAnnotation(t, field, dataObjectFields, resDtos, dopMap, ownerId);
            }
        }
        Long endTime = System.currentTimeMillis();
        log.info("processList 总耗时 :%s", endTime - startTime);
        return Result.newSuccessResult(ls);
    }

    @Override
    public <T> Result<T> access(T t, Long ownerId) throws BaseException {
        //获取所有注解的field code
        List<String> fieldCodes = this.fetchFieldCode(t);
        //获取field 所属的 数据对象
        List<DataObjectFieldDto> dataObjectFields = this.getDataObjectFields(fieldCodes);
        List<T> ls = new ArrayList<>(1);
        ls.add(t);
        //调用后置服务，校验权限
        List<PermissionListResDto> resDtos = this.httpCheckReadList(this.buildReqDtos(ls, LambdaMap.toSingleList(dataObjectFields, m -> m.getDataObjectCode()), ownerId));

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            this.processAnnotation(t, field, dataObjectFields, resDtos, this.getDataObjectPermissionMap(dataObjectFields), ownerId);
        }

        return Result.newSuccessResult(t);
    }

    /**
     * 处理用注解标注的字段
     */
    private <T> void processAnnotation(T t, Field field, List<DataObjectFieldDto> dataObjectFields, List<PermissionListResDto> resDtos, Map<String, Integer> dopMap, Long ownerId) throws BaseException {
        try {

            FieldCode annotation = field.getAnnotation(FieldCode.class);
            if (annotation == null)
                return;

            //根据fieldCode，获取他的数据对象code
            String dataObjectCode = this.getDataObjectCode(dataObjectFields, annotation.value());
            if (DataObjectEnum.CONTACT.getCode().equals(dataObjectCode)) {
                this.processAnnotationForContact(t, field, annotation.value(), resDtos, dataObjectCode, dopMap, ownerId);
            } else {

                Object owner = ownerId;
                Integer doPermission = dopMap.get(dataObjectCode);
                //如果拥有查看所有的权限，直接返回
                if (DataObjectPermissionEnum.PUBLIC.getValue().intValue() == doPermission)
                    return;
                if (owner == null) {
                    owner = AccessCommon.getOwner(t);
                    if (owner == null) {
                        this.dataEncrypt(t, field);
                        return;
                    }
                }
                PermissionDetailResDto permissionDto = this.getOwnerPermission(dataObjectCode, owner, resDtos);
                if (permissionDto == null || !permissionDto.getIsPass())
                    this.dataEncrypt(t, field);
            }

        } catch (Exception ex) {
            log.error("processAnnotation 异常:%s", ex.toString());
            throw new BaseException(CommonCode.COMMON_1007);
        }
    }

    /**
     * 处理用注解标注的字段
     */
    private <T> void processAnnotationForContact(T t, Field field, String fieldCode, List<PermissionListResDto> resDtos, String dataObjectCode, Map<String, Integer> customerContactmap, Long ownerId) throws BaseException {
        try {
            Integer contactRP = customerContactmap.get(DataObjectEnum.CONTACT.getCode());
            Integer customerRP = customerContactmap.get(DataObjectEnum.CUSTOMER.getCode());

            //拥有读取全部“联系方式” 权限
            if (DataObjectPermissionEnum.PUBLIC.getValue().equals(contactRP))
                return;

            Object owner = ownerId;
            if (owner == null) {
                owner = AccessCommon.getOwner(t);
            }
            if (owner == null) {
                //如果此条数据没有ownerId
                Boolean customerPermission = DataObjectPermissionEnum.PUBLIC.getValue().equals(customerRP);
                Boolean contactPermission = false;
                this.dataEncryptContact(t, field, fieldCode, customerPermission, contactPermission);
                return;
            }

            PermissionDetailResDto permissionDto = this.getOwnerPermission(dataObjectCode, owner, resDtos);
            if (permissionDto == null) {
                this.dataEncrypt(t, field);
                return;
            }
            //无需加密，直接返回
            if (permissionDto != null && permissionDto.getIsPass())
                return;

            //“客户联系方式”数据对象，需要特殊处理
            PermissionDetailResDto customerPermissionDto = this.getOwnerPermission(DataObjectEnum.CUSTOMER.getCode(), owner, resDtos);
            this.dataEncryptContact(t, field, fieldCode, customerPermissionDto.getIsPass(), permissionDto.getIsPass());

        } catch (Exception ex) {
            log.error("processAnnotationForContact 异常:%s", ex.toString());
            throw new BaseException(ex);
        }
    }


    private <T> List<Long> fetchOwnerId(List<T> ls) throws BaseException {
        List<Long> ownerIdList = new ArrayList<>(ls.size());
        for (T t : ls) {
            Object ownerObj = AccessCommon.getOwner(t);
            if (ownerObj != null && !ownerIdList.contains(ownerObj))
                ownerIdList.add((Long) ownerObj);
        }
        return ownerIdList;
    }

    /**
     * 获取所有的FieldCode注解
     */
    private <T> List<String> fetchFieldCode(T t) throws BaseException {
        List<String> codes = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            FieldCode annotation = field.getAnnotation(FieldCode.class);
            if (annotation == null)
                continue;
            codes.add(annotation.value());
        }
        if (BooleanUtils.isEmpty(codes))
            throw new BaseException(CodeConstant.CODE_900009);
        return codes;
    }

    /**
     * 组合调用后置系统查询，需要的数据
     */
    private <T> List<PermissionListReqDto> buildReqDtos(List<T> ls, List<String> dataObjectCodes, Long ownerId) throws
            BaseException {
        List<Long> ownerIds = new ArrayList<>();
        if (ownerId != null)
            ownerIds.add(ownerId);
        else
            ownerIds = this.fetchOwnerId(ls);

        List<PermissionListReqDto> reqDtos = new ArrayList<>(dataObjectCodes.size());
        for (String code : dataObjectCodes) {
            PermissionListReqDto reqDto = LambdaFilter.firstOrDefault(reqDtos, m -> m.getDataObjectCode().equals(code));
            if (reqDto != null)
                continue;
            PermissionListReqDto dto = new PermissionListReqDto();
            dto.setDataObjectCode(code);
            dto.setOwnerIds(ownerIds);
            reqDtos.add(dto);
        }
        return reqDtos;
    }

    private String getDataObjectCode(List<DataObjectFieldDto> fieldDtos, String fieldCode) throws BaseException {
        return LambdaFilter.firstOrDefault(fieldDtos, m -> m.getCode().equals(fieldCode)).getDataObjectCode();
    }

    /**
     * 数据加密
     */
    private <T> void dataEncrypt(T t, Field field) throws BaseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodSet = AccessCommon.setMethod(t, field);
        methodSet.invoke(t, "***");
    }

    /**
     * "客户联系方式"类字段数据加密
     */
    private <T> void dataEncryptContact(T t, Field field, String fieldCode, Boolean customerPermission, Boolean contactPermission) throws BaseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodSet = AccessCommon.setMethod(t, field);
        Object fieldValue = AccessCommon.getFieldValue(t, field);
        String value = fieldValue != null ? fieldValue.toString() : "";
        if (fieldCode.equals("contact_mobile")) {
            methodSet.invoke(t, FormatContactUtils.formatMobile(customerPermission, contactPermission, value));
        } else if (fieldCode.equals("contact_name")) {
            methodSet.invoke(t, FormatContactUtils.formatName(customerPermission, contactPermission, value));
        } else {
            methodSet.invoke(t, "***");
        }
    }

    /**
     * 从后置系统返回的权限数据，查询指定owner的权限
     *
     * @param dataObjectCode 数据对象code
     * @param owner          拥有者id
     */
    private PermissionDetailResDto getOwnerPermission(String dataObjectCode, Object owner, List<PermissionListResDto> resDtos) throws BaseException {
        PermissionListResDto singleResDto = LambdaFilter.firstOrDefault(resDtos, m -> m.getDataObjectCode().equals(dataObjectCode));
        List<PermissionDetailResDto> permissions = singleResDto.getPermissions();
        PermissionDetailResDto permissionDto = LambdaFilter.firstOrDefault(permissions, m -> m.getOwnerId
                ().equals(owner));
        return permissionDto;
    }


    private List<DataObjectFieldDto> getDataObjectFields(List<String> codes) throws BaseException {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("codes", SymbolUtils.toStr(codes));
        Result<List<DataObjectFieldDto>> result = HttpUtils.get(TenantUrl.GET_DATA_OBJECT_FIELDS_BY_CODES, paramMap, new BaseTypeReference<Result<List<DataObjectFieldDto>>>() {
        });
        return result.getResult();
    }

    private Map<String, Integer> getDataObjectPermissionMap(List<DataObjectFieldDto> dataObjectFields) throws BaseException {
        Map<String, Integer> map = new HashMap<>();
        List<String> dataObjects = LambdaFilter.distinct(LambdaMap.toSingleList(dataObjectFields, m -> m.getDataObjectCode()));
        for (String code : dataObjects) {
            map.put(code, permissionService.getRead(code).getResult());
        }
        String contact = DataObjectEnum.CONTACT.getCode();
        String customer = DataObjectEnum.CUSTOMER.getCode();
        if (map.get(contact) == null)
            map.put(contact, permissionService.getRead(contact).getResult());
        if (map.get(customer) == null)
            map.put(customer, permissionService.getRead(customer).getResult());
        return map;
    }

    private List<PermissionListResDto> httpCheckReadList(List<PermissionListReqDto> reqDots) throws BaseException {
        Map<String, Object> paramMap = new HashMap<>(1);
        //检查是否存在“客户联系方式”模块，如果存在，则需要加上“客户基本信息”模块，因为判断联系方式需要用到
        PermissionListReqDto contactDataObject = LambdaFilter.firstOrDefault(reqDots, m -> m.getDataObjectCode().equals(DataObjectEnum.CONTACT.getCode()));
        if (contactDataObject != null) {
            PermissionListReqDto customerDataObject = LambdaFilter.firstOrDefault(reqDots, m -> m.getDataObjectCode().equals(DataObjectEnum.CUSTOMER.getCode()));
            if (customerDataObject == null) {
                customerDataObject = new PermissionListReqDto();
                customerDataObject.setDataObjectCode(DataObjectEnum.CUSTOMER.getCode());
                customerDataObject.setOwnerIds(contactDataObject.getOwnerIds());
                reqDots.add(customerDataObject);
            }
        }
        paramMap.put("jsonReqDto", JsonUtils.objectToJson(reqDots));
        Result<List<PermissionListResDto>> result = HttpUtils.get(TenantUrl.CHECK_DATA_OBJECT_PERMISSION_RED_LIST, paramMap, new BaseTypeReference<Result<List<PermissionListResDto>>>() {
        });
        return result.getResult();
    }
}
