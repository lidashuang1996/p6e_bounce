package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseResultDto;

import java.io.Serializable;

public class P6eSocketResultDto extends P6eBaseResultDto implements Serializable {

    private String sid;
    private String status;
    private String name;
    private String describe;
    private String date;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
