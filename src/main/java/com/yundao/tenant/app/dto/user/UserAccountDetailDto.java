package com.yundao.tenant.app.dto.user;

import java.io.Serializable;
import java.util.List;

import com.yundao.core.base.model.BaseModel;
import com.yundao.tenant.app.model.tenant.TenantModel;

public class UserAccountDetailDto extends BaseModel implements Serializable {
    /**
	 * 手机号码
	 */
    private String mobile;

    /**
	 * 邮箱
	 */
    private String email;

    /**
	 * 用户名
	 */
    private String username;

    /**
	 * 密码
	 */
    private String password;
    /**
     * 客户所属于的租户
     */
    private List<TenantModel> tenantModels;

    private static final long serialVersionUID = 1L;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public List<TenantModel> getTenantModels() {
	
		return tenantModels;
	}

	public void setTenantModels(List<TenantModel> tenantModels) {
	
		this.tenantModels = tenantModels;
	}
    
}