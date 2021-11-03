package com.yjxxt.ems.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Vaccination {
    private Integer id;

    private Integer userId;

    private String trueName;

    private String first;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date firstDate;

    private String second;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date secondDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second == null ? null : second.trim();
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "id=" + id +
                ", userId=" + userId +
                ", trueName='" + trueName + '\'' +
                ", first='" + first + '\'' +
                ", firstDate=" + firstDate +
                ", second='" + second + '\'' +
                ", secondDate=" + secondDate +
                '}';
    }
}