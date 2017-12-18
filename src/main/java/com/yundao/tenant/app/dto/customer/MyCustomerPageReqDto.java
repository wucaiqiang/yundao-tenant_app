package com.yundao.tenant.app.dto.customer;

import com.yundao.tenant.app.dto.AbstractBasePageDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询我的客户列表入参dto
 *
 * @author jan
 * @create 2017-08-09 PM2:03
 **/
public class MyCustomerPageReqDto extends AbstractBasePageDto {

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "客户编号")
    private String number;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "性别，多个逗号分隔")
    private Integer sexs;

    @ApiModelProperty(value = "省份")
    private String provinceCodes;

    @ApiModelProperty(value = "城市")
    private String cityCodes;

    @ApiModelProperty(value = "客户级别，多个逗号分隔")
    private String levels;

    @ApiModelProperty(value = "成交日期，开始时间")
    private String dealDateBegin;

    @ApiModelProperty(value = "成交日期，结束时间")
    private String dealDateEnd;

    @ApiModelProperty(value = "分配日期，开始时间")
    private String distributionDateBegin;

    @ApiModelProperty(value = "分配日期，结束时间")
    private String distributionDateEnd;

    @ApiModelProperty(value = "搜索范围；all：全部，focus：我关注的，unfollow：未跟进；默认all")
    private String scope;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSexs() {
        return sexs;
    }

    public void setSexs(Integer sexs) {
        this.sexs = sexs;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getDealDateBegin() {
        return dealDateBegin;
    }

    public void setDealDateBegin(String dealDateBegin) {
        this.dealDateBegin = dealDateBegin;
    }

    public String getDealDateEnd() {
        return dealDateEnd;
    }

    public void setDealDateEnd(String dealDateEnd) {
        this.dealDateEnd = dealDateEnd;
    }

    public String getDistributionDateBegin() {
        return distributionDateBegin;
    }

    public void setDistributionDateBegin(String distributionDateBegin) {
        this.distributionDateBegin = distributionDateBegin;
    }

    public String getDistributionDateEnd() {
        return distributionDateEnd;
    }

    public void setDistributionDateEnd(String distributionDateEnd) {
        this.distributionDateEnd = distributionDateEnd;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(String provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }
}
