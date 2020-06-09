package com.p6e.bounce.controller.support;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class P6eTrackModel implements Serializable {

    public static P6eTrackModel build(HttpServletRequest request) {
        return new P6eTrackModel(request);
    }

    private P6eTrackModel(HttpServletRequest request) {
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append('}');
        return sb.toString();
    }
}
