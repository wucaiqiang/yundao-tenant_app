package com.yundao.tenant.app.model.cc.ccriskrating;

import com.yundao.core.base.model.BaseModel;
import java.io.Serializable;

public class CcRiskRatingModel extends BaseModel implements Serializable {
    /**
	 * 文本
	 */
    private String label;

    /**
	 * 值，1：保守型，2：适度保守型，3：平衡型，4：适度进取型，5：进取型
	 */
    private Integer value;

    /**
	 * 分数最小区间
	 */
    private Integer gradeMin;

    /**
	 * 分数最大区间
	 */
    private Integer gradeMax;

    /**
	 * 评语
	 */
    private String comment;

    private static final long serialVersionUID = 1L;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getGradeMin() {
        return gradeMin;
    }

    public void setGradeMin(Integer gradeMin) {
        this.gradeMin = gradeMin;
    }

    public Integer getGradeMax() {
        return gradeMax;
    }

    public void setGradeMax(Integer gradeMax) {
        this.gradeMax = gradeMax;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}