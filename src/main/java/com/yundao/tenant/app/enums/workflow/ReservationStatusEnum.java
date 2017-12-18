package com.yundao.tenant.app.enums.workflow;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 预约状态
 *
 * @author jan
 * @create 2017-07-22 PM3:36
 **/
public enum ReservationStatusEnum {

    /**
     * 待审核
     */
    APPROVALING(1, "待审批",true,true,true,false),

    /**
     * 已通过
     */
    PASS(2, "已通过",false,false,false,true),

    /**
     * 已驳回
     */
    REJECT(3, "已驳回",false,false,false,true),

    /**
     * 已取消
     */
    CANCEL(4, "已取消",false,false,false,true);

    private Integer value;
    private String name;

    private Boolean hasPassOperate;
    private Boolean hasRejectOperate;
    private Boolean hasCancelOperate;
    private Boolean showText;

    private static Map<Integer, ReservationStatusEnum> enumMap = new HashMap<>();

    static {
        EnumSet<ReservationStatusEnum> set = EnumSet.allOf(ReservationStatusEnum.class);
        for (ReservationStatusEnum each : set) {
            enumMap.put(each.getValue(), each);
        }
    }

    ReservationStatusEnum(Integer value, String name,Boolean hasPassOperate,Boolean hasRejectOperate,Boolean hasCancelOperate,Boolean showText) {
        this.value = value;
        this.name = name;
        this.hasPassOperate = hasPassOperate;
        this.hasRejectOperate = hasRejectOperate;
        this.hasCancelOperate = hasCancelOperate;
        this.showText = showText;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取枚举
     */
    public static ReservationStatusEnum getEnum(Integer value) {
        if (value == null)
            return null;
        return enumMap.get(value);
    }

    /**
     * 获取在申请者页面显示的文本
     */
    public static String getName(Integer value) {
        ReservationStatusEnum enumItem = getEnum(value);
        if (enumItem == null)
            return null;
        return enumItem.getName();
    }

    /**
     * 是否能进行审核
     *
     * @param value
     * @return
     */
    public static Boolean canAudit(Integer value) {
        return ReservationStatusEnum.APPROVALING.getValue().equals(value);
    }

    /**
     * 判断是否能编辑
     *
     * @param value
     * @return
     */
    public static Boolean canUpdate(Integer value) {
        if (REJECT.getValue().equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否能重新提交
     *
     * @param value
     * @return
     */
    public static Boolean canAgainCommit(Integer value) {
        if (REJECT.getValue().equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否能取消
     *
     * @param value
     * @return
     */
    public static Boolean canCancel(Integer value) {
        if (REJECT.getValue().equals(value)) {
            return true;
        }
        return false;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasPassOperate() {
        return hasPassOperate;
    }

    public void setHasPassOperate(Boolean hasPassOperate) {
        this.hasPassOperate = hasPassOperate;
    }

    public Boolean getHasRejectOperate() {
        return hasRejectOperate;
    }

    public void setHasRejectOperate(Boolean hasRejectOperate) {
        this.hasRejectOperate = hasRejectOperate;
    }

    public Boolean getHasCancelOperate() {
        return hasCancelOperate;
    }

    public void setHasCancelOperate(Boolean hasCancelOperate) {
        this.hasCancelOperate = hasCancelOperate;
    }

    public static Map<Integer, ReservationStatusEnum> getEnumMap() {
        return enumMap;
    }

    public static void setEnumMap(Map<Integer, ReservationStatusEnum> enumMap) {
        ReservationStatusEnum.enumMap = enumMap;
    }

    public Boolean getShowText() {
        return showText;
    }

    public void setShowText(Boolean showText) {
        showText = showText;
    }
}
