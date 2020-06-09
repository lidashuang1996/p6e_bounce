package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseParamDto;

import java.io.Serializable;

public class P6eClientParamDto extends P6eBaseParamDto implements Serializable {
    private String ip;

    public P6eClientParamDto() {
    }

    public P6eClientParamDto(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
