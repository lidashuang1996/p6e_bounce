package com.p6e.bounce.model.vo;

import com.p6e.bounce.model.base.P6eBaseParamVo;

import java.io.Serializable;

public class P6eSignInParamVo extends P6eBaseParamVo implements Serializable {
    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
