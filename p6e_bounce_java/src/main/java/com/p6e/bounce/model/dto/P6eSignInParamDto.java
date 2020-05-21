package com.p6e.bounce.model.dto;

import com.p6e.bounce.model.base.P6eBaseParamDto;

import java.io.Serializable;

public class P6eSignInParamDto extends P6eBaseParamDto implements Serializable {
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
