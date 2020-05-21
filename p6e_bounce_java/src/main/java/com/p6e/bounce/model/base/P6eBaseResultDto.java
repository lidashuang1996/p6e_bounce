package com.p6e.bounce.model.base;

import java.io.Serializable;

public class P6eBaseResultDto implements Serializable {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
