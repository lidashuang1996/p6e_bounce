package com.p6e.bounce.utils;

import java.util.UUID;

public final class CommonUtil {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }
}
