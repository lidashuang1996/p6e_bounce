package com.p6e.bounce.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 对 GEON 的封装
 * @author LiDaShuang
 * @version 1.0
 */
public final class GsonUtil {

    /** 工具类创建一个 GEON 对象 */
    private static final Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

}
