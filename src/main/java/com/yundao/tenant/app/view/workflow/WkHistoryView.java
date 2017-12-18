package com.yundao.tenant.app.view.workflow;

/**
 * Created by gjl on 2017/9/22.
 */
public class WkHistoryView {
    private String time;
    private String content;
    private String result;
    private String operatorName;
    private String reason;

    public WkHistoryView() {
    }

    public WkHistoryView(String time, String content, String result, String operatorName, String reason) {
        this.time = time;
        this.content = content;
        this.result = result;
        this.operatorName = operatorName;
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
