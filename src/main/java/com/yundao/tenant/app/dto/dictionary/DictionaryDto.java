package com.yundao.tenant.app.dto.dictionary;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yundao.tenant.app.dto.common.LabelValueDto;

/**
 * 数据字典dto
 *
 * @author jan
 * @create 2017-07-16 AM11:32
 **/
public class DictionaryDto implements Serializable {

    static final long serialVersionUID = 1L;

    /**
     * 文本
     */
    private String label;

    /**
     * 值
     */
    private String value;

    /**
     * 选项
     */
    private List<LabelValueDto> selections;

    public List<LabelValueDto> getSelections() {
        if (this.selections == null)
            this.selections = new ArrayList<>();
        return selections;
    }

    public void setSelections(List<LabelValueDto> selections) {
        this.selections = selections;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
