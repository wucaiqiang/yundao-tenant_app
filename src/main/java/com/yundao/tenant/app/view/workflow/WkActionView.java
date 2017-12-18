package com.yundao.tenant.app.view.workflow;

import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;

/**
 * Created by gjl on 2017/9/22.
 */
public class WkActionView {
    private Boolean hasRejectOperate;
    private Boolean hasPassOperate;
    private Boolean hasVetoOperate;
    private String operateResult;

    public Boolean getHasVetoOperate() {
	
		return hasVetoOperate;
	}

	public void setHasVetoOperate(Boolean hasVetoOperate) {
	
		this.hasVetoOperate = hasVetoOperate;
	}

	public WkActionView() {
    }

    public WkActionView(Boolean hasRejectOperate, Boolean hasPassOperate, String operateResult) {
        this.hasRejectOperate = hasRejectOperate;
        this.hasPassOperate = hasPassOperate;
        this.operateResult = operateResult;
    }

    public WkActionView(BaseWkServiceDto wkServiceBusiness) {
        this.hasRejectOperate = wkServiceBusiness.getHasRejectOperate();
        this.hasPassOperate = wkServiceBusiness.getHasPassOperate();
        this.operateResult = wkServiceBusiness.getOperateResult();
        this.hasVetoOperate = wkServiceBusiness.getHasVetoOperate();
    }

    public Boolean getHasRejectOperate() {
        return hasRejectOperate;
    }

    public void setHasRejectOperate(Boolean hasRejectOperate) {
        this.hasRejectOperate = hasRejectOperate;
    }

    public Boolean getHasPassOperate() {
        return hasPassOperate;
    }

    public void setHasPassOperate(Boolean hasPassOperate) {
        this.hasPassOperate = hasPassOperate;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

}
