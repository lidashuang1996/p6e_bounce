package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseResultDto;

import java.io.Serializable;
import java.util.List;

public class P6eListResultDto<T> extends P6eBaseResultDto implements Serializable {
    private List<T> list;
    private Long page;
    private Long size;
    private Long total;

    public P6eListResultDto() {
    }

    public P6eListResultDto(List<T> list, Long page, Long size, Long total) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
