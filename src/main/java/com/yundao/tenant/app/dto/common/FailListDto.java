package com.yundao.tenant.app.dto.common;


import com.yundao.core.utils.ConfigUtils;

/**失败结果
 * @author jan
 * @create 2017-07-13 AM11:18
 **/
public class FailListDto {


    /**
     * 编码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 对象的id
     */
    private Long id;

    /**
     * 对象的名称
     */
    private String name;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.message = ConfigUtils.getValue(Integer.valueOf(this.code).toString());
    }

    public String getMessage() {
        return message;
    }

    /**
     * 已在setCode自动设置了message
     *
     * @param message
     */
    private void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
