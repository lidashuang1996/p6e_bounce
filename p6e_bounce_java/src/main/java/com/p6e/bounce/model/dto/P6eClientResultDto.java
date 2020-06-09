package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseResultDto;

import java.io.Serializable;

public class P6eClientResultDto extends P6eBaseResultDto implements Serializable {

    private String cid;
    private String ip;
    private String date;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
