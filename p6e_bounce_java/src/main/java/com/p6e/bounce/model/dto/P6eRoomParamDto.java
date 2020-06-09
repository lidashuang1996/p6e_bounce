package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseParamDto;

import java.io.Serializable;

public class P6eRoomParamDto extends P6eBaseParamDto implements Serializable {

    private String rid;
    private String name;
    private String describe;
    private String date;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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
