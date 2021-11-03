package com.yjxxt.ems.query;

import com.yjxxt.ems.base.BaseQuery;

public class VaccinationQuery extends BaseQuery {

    private String trueName;
    private String first;
    private String second;

    public VaccinationQuery() {
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
