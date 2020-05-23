package com.p6e.bounce.model.vo;

import com.p6e.bounce.model.base.P6eBaseResultVo;

import java.io.Serializable;

public class P6eProverbResultVo extends P6eBaseResultVo implements Serializable {
    private Integer id;
    private String author;
    private String content;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
