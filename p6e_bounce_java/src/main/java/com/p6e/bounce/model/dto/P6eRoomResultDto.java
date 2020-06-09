package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseResultDto;

import java.io.Serializable;

public class P6eRoomResultDto extends P6eBaseResultDto implements Serializable {

    private String rid;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

}
