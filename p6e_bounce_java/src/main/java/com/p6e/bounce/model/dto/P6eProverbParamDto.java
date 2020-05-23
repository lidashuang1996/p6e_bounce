package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseParamDto;

import java.io.Serializable;

public class P6eProverbParamDto extends P6eBaseParamDto implements Serializable {
    private Integer id;
    private String author;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
