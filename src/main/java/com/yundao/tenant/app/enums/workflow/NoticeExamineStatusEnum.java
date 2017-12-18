package com.yundao.tenant.app.enums.workflow;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品审核状态
 *
 * @author jan
 * @create 2017-07-22 PM3:36
 **/
public enum NoticeExamineStatusEnum {


    /**
     * 未提交
     */
    INITIAL(1, "未提交",false,false,false,false),

    /**
     * 审核中
     */
    APPROVALING(2, "审核中",true,true,true,false),

    /**
     * 已通过
     */
    PASS(3, "已通过",false,false,false,true),

    /**
     * 已驳回
     */
    REJECT(4, "已驳回",false,false,false,true),

    /**
     * 已撤销
     */
    CANCEL(5, "已撤销",false,false,false,true);

    private Integer value;
    private String name;

    private Boolean hasPassOperate;
    private Boolean hasRejectOperate;
    private Boolean hasCancelOperate;
    private Boolean showText;

    private static Map<Integer, NoticeExamineStatusEnum> enumMap = new HashMap<>();

    static {
        EnumSet<NoticeExamineStatusEnum> set = EnumSet.allOf(NoticeExamineStatusEnum.class);
        for (NoticeExamineStatusEnum each : set) {
            enumMap.put(each.getValue(), each);
        }
    }

    NoticeExamineStatusEnum(Integer value, String name, Boolean hasPassOperate, Boolean hasRejectOperate, Boolean hasCancelOperate, Boolean showText) {
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
    public static NoticeExamineStatusEnum getEnum(Integer value) {
        if (value == null)
            return null;
        return enumMap.get(value);
    }


    public static Boolean canAudit(Integer value) {
        return NoticeExamineStatusEnum.APPROVALING.getValue().equals(value);
    }

    /**
     * 根据value 获取name
     */
    public static String getEnumName(Integer value) {
        NoticeExamineStatusEnum enumItem = getEnum(value);
        if (enumItem == null)
            return null;
        return enumItem.getName();
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

    public static Map<Integer, NoticeExamineStatusEnum> getEnumMap() {
        return enumMap;
    }

    public static void setEnumMap(Map<Integer, NoticeExamineStatusEnum> enumMap) {
        NoticeExamineStatusEnum.enumMap = enumMap;
    }

    public Boolean getShowText() {
        return showText;
    }

    public void setShowText(Boolean showText) {
        showText = showText;
    }
}
