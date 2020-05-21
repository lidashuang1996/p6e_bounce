package com.p6e.bounce.model;


public class P6eResultModel {

    private Integer code;
    private String message;
    private Object data;

    public static P6eResultModel build(int code, String content) {
        return new P6eResultModel(code, content, null);
    }

    public static P6eResultModel build(String content) {
        return new P6eResultModel(content);
    }

    public static <T> P6eResultModel build(String content, T data) {
        return new P6eResultModel(content, data);
    }

    private P6eResultModel() { }

    private P6eResultModel(String content, Object data) {
        this(content);
        this.data = data;
    }

    private P6eResultModel(String content) {
        try {
            String[] ps = content.split("-");
            int code = Integer.parseInt(ps[0]);
            String message = ps[1];
            this.code = code;
            this.message = message;
        } catch (Exception e) {
            this.code = 500;
            this.message = "Parsing return string exceptions ...";
            this.data = null;
        }
    }

    private P6eResultModel(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

