package com.yundao.tenant.app.enums.workflow;

import com.yundao.tenant.app.constant.schema.Schema;

import java.util.EnumSet;

/**
 * 工作流 流程定义key
 * 
 * @author 欧阳利
 * 2017年8月31日
 */
public enum ProcessDefineKeyEnum {

    /**
     * 产品审核
     */
    PRODUCT_AUDIT("product_audit","产品上线审批","productWkService", Schema.PRODUCT_DETAIL + Schema.PRODUCT_DETAIL_PARAMS),

    /**
     * 报单审核
     */
    DECLARATION("declaration","报单审批","declWkService",Schema.DECLARATION_DETAIL + Schema.DECLARATION_DETAIL_PARAMS),
    /**
     * 产品公告
     */
    PRODUCT_NOTICE("product_notice", "公告发布审批","noticeWkService",Schema.PRODUCT_NOTICE_DETAIL + Schema.PRODUCT_NOTICE_DETAIL_PARAMS),
    
    /**
     * 退款审批
     */
    REFUND("refund", "退款审批","refundWkService",Schema.DECLARATION_DETAIL + Schema.DECLARATION_DETAIL_PARAMS),
    
    /**
     * 结佣审批
     */
    KNOT_COMMISSION("knot_commission", "结佣审批","knotCommissionWkService",null),
    
    
    /**
     * 预约审核
     */
    RESERVATIN("product_reservation","预约额度审批","reserWkService",Schema.RESERVATION_DETAIL + Schema.RESERVATION_DETAIL_PARAMS);

    private String key;
    private String name;
    private String serviceName;
    private String actionUrl;


    public static ProcessDefineKeyEnum getKey(String key){
    	EnumSet<ProcessDefineKeyEnum> set = EnumSet.allOf(ProcessDefineKeyEnum.class);
    	for (ProcessDefineKeyEnum each : set) {
    		if(each.getKey().equals(key)){
    			return each;
    		}
        }
    	return null;
    }

    private ProcessDefineKeyEnum(String key, String name,String serviceName,String actionUrl) {
        this.key = key;
        this.name = name;
        this.serviceName = serviceName;
        this.actionUrl = actionUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
}
