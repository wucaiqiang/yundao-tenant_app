package com.yundao.tenant.app.view.workflow;

import com.yundao.tenant.app.dto.common.ActionDTO;
import com.yundao.tenant.app.view.product.AttachmentView;

/**
 * Created by gjl on 2017/9/22.
 */
public class WkBusinessInfoView {
    private String name;
    private String value;
    private ActionDTO action;
    private AttachmentView fileData;

    public WkBusinessInfoView(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public WkBusinessInfoView(String name, String value, ActionDTO action,AttachmentView fileData) {
        this.name = name;
        this.value = value;
        this.action = action;
        this.fileData = fileData;
    }

    public WkBusinessInfoView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActionDTO getAction() {
        return action;
    }

    public void setAction(ActionDTO action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttachmentView getFileData() {
        return fileData;
    }

    public void setFileData(AttachmentView fileData) {
        this.fileData = fileData;
    }
}
