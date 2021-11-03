package com.yjxxt.ems.query;

import com.yjxxt.ems.base.BaseQuery;

public class CommunityQuery extends BaseQuery {
    private Integer comId;
    private String address;
    private String leader;

    public CommunityQuery() {
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "CommunityQuery{" +
                "comId=" + comId +
                ", address='" + address + '\'' +
                ", leader='" + leader + '\'' +
                '}';
    }
}
