package com.yjxxt.ems.query;

import com.yjxxt.ems.base.BaseQuery;

public class RoleQuery extends BaseQuery {
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public RoleQuery() {
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
