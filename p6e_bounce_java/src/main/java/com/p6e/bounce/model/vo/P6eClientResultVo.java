package com.p6e.bounce.model.vo;

import com.p6e.bounce.model.base.P6eBaseParamVo;

import java.io.Serializable;
import java.util.List;

public class P6eClientResultVo extends P6eBaseParamVo implements Serializable {

    private String cid;
    private String ip;
    private String date;

    private List<Integer> list;

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

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
