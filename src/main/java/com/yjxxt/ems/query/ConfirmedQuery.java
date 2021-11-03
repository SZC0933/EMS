package com.yjxxt.ems.query;

import com.yjxxt.ems.base.BaseQuery;

public class ConfirmedQuery extends BaseQuery {
    private Integer userId;
    private String trueName;
    private String address;

    private Integer state;
    private String tcPhone;
    private String comId;

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    //社区名字
    private String comAddress;

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getTcPhone() {
        return tcPhone;
    }

    public void setTcPhone(String tcPhone) {
        this.tcPhone = tcPhone;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTrueName() {
        return trueName;
    }



    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public ConfirmedQuery() {
    }



}
