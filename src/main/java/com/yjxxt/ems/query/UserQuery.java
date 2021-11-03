package com.yjxxt.ems.query;

import com.yjxxt.ems.base.BaseQuery;

public class UserQuery extends BaseQuery {

    private String userName;
    private Integer comId;
    private Integer vc;

    public UserQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getVc() {
        return vc;
    }

    public void setVc(Integer vc) {
        this.vc = vc;
    }

}
