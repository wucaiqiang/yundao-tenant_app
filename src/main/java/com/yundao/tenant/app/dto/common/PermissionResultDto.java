package com.yundao.tenant.app.dto.common;


import com.yundao.core.utils.BooleanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限操作结果集
 *
 * @author jan
 * @create 2017-07-09 PM5:26
 **/
public class PermissionResultDto {


    /**
     * 操作失败的结果集
     */
    private List<FailListDto> failList;

    /**
     * 允许操作的结果集
     */
    private List<PassListDto> passList;

    /**
     * 是否成功，即没有任何失败信息
     *
     * @return
     */
    public Boolean isSuccess() {
        return BooleanUtils.isEmpty(this.failList);
    }

    public List<FailListDto> getFailList() {
        if (this.failList == null)
            this.failList = new ArrayList<>();
        return failList;
    }

    public List<PassListDto> getPassList() {
        if (this.passList == null)
            this.passList = new ArrayList<>();
        return passList;
    }

}
