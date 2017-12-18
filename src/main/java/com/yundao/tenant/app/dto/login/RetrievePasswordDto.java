package com.yundao.tenant.app.dto.login;


import io.swagger.annotations.ApiModelProperty;


public class RetrievePasswordDto {
    /**
	 * 手机号码
	 */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
	 * 验证码
	 */
    @ApiModelProperty("验证码")
    private String captcha;

    /**
	 * 密码
	 */
    @ApiModelProperty("密码")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}