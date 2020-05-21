package com.p6e.bounce.model.base;

import java.io.Serializable;

public class P6eBaseParamVo implements Serializable {
    private String search;
    private Integer page;
    private Integer size;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
