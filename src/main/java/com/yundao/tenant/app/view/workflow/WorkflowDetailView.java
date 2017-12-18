package com.yundao.tenant.app.view.workflow;

import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.dto.workflow.BaseWkServiceDto;

import java.util.List;

/**
 * Created by gjl on 2017/9/22.
 */
public class WorkflowDetailView extends WkActionView{
    private String title;
    private String submitter;
    private String submitTime;
    private ActionDTO detailAction;
    private List<WkBusinessInfoView> businessInfoList;
    private List<WkHistoryView> recordList;

    public WorkflowDetailView() {
    }

    public WorkflowDetailView(String title, String submitter, String submitTime, ActionDTO detailAction, List<WkBusinessInfoView> businessInfoList, List<WkHistoryView> recordList,BaseWkServiceDto wkServiceBusiness) {
        super(wkServiceBusiness);
        this.title = title;
        this.submitter = submitter;
        this.submitTime = submitTime;
        this.detailAction = detailAction;
        this.businessInfoList = businessInfoList;
        this.recordList = recordList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public List<WkBusinessInfoView> getBusinessInfoList() {
        return businessInfoList;
    }

    public void setBusinessInfoList(List<WkBusinessInfoView> businessInfoList) {
        this.businessInfoList = businessInfoList;
    }

    public ActionDTO getDetailAction() {
        return detailAction;
    }

    public void setDetailAction(ActionDTO detailAction) {
        this.detailAction = detailAction;
    }

    public List<WkHistoryView> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<WkHistoryView> recordList) {
        this.recordList = recordList;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }
}
