package com.p6e.bounce.model.base;

import java.io.Serializable;

public class P6eBaseDb implements Serializable {
    private Integer page;
    private Integer size;
    private String search;

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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
