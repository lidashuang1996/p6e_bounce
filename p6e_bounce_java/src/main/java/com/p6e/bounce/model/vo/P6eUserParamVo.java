package com.p6e.bounce.model.vo;

import com.p6e.bounce.model.base.P6eBaseParamVo;

import java.io.Serializable;

public class P6eUserParamVo extends P6eBaseParamVo implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
