package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseParamDto;

import java.io.Serializable;

public class P6eUserParamDto extends P6eBaseParamDto implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
