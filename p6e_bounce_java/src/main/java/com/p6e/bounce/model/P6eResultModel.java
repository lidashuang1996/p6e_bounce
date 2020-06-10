package com.p6e.bounce.model;

import com.p6e.bounce.controller.support.P6eBaseController;

import java.io.Serializable;

/**
 * 请求结果的封装
 * @author LiDaShuang
 * @version 1.0
 */
public class P6eResultModel implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public static P6eResultModel build(String content) {
        return new P6eResultModel(content);
    }

    public static P6eResultModel build(String content, Object data) {
        return new P6eResultModel(content, data);
    }

    public static P6eResultModel build(int code, String message) {
        return new P6eResultModel(code, message, null);
    }

    public static P6eResultModel build(int code, String message, Object data) {
        return new P6eResultModel(code, message, data);
    }

    private P6eResultModel() { }

    private P6eResultModel(String content) {
        this(content, null);
    }

    private P6eResultModel(String content, Object data) {
        try {
            String[] ps = content.split("-");
            int code = Integer.parseInt(ps[0]);
            String message = ps[1];
            this.setCode(code);
            this.setData(data);
            this.setMessage(message);
        } catch (Exception e) {
            this.setCode(500);
            this.setData(null);
            this.setMessage("Parsing return string exceptions ...");
        }
    }

    private P6eResultModel(Integer code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        // 将 code 设置为返回的状态码
        P6eBaseController.getResponse().setStatus(code);
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

