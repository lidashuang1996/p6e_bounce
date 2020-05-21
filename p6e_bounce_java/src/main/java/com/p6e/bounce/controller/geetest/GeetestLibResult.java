package com.p6e.bounce.controller.geetest;

public class GeetestLibResult {
    /**
     * 成功失败的标识码，1表示成功，0表示失败
     */
    private int status;

    /**
     * 返回数据，json格式
     */
    private String data;

    /**
     * 备注信息，如异常信息等
     */
    private String msg;

    int getStatus() {
        return status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    String getData() {
        return data;
    }

    void setData(String data) {
        this.data = data;
    }

    String getMsg() {
        return msg;
    }

    void setMsg(String msg) {
        if (msg == null || msg.isEmpty()) {
            return;
        }
        if (this.getMsg() == null || this.getMsg().isEmpty()) {
            this.msg = msg;
        } else {
            this.msg += "\n" + msg;
        }

    }

    @Override
    public String toString() {
        return "GeetestLibResult{" + "status=" + status + ", data='" + data
            + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
