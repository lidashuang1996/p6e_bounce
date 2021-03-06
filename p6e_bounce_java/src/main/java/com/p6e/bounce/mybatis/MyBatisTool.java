package com.p6e.bounce.mybatis;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * MyBatis 密码加密处理类
 * @author LiDaShuang
 * @version 1.0
 */
public final class MyBatisTool {

    public static String encryption(String pwd) {
        final String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));
        final String md5Pwd1 = md5Pwd.substring(0, 8);
        final String md5Pwd2 = md5Pwd.substring(8);
        return DigestUtils.md5DigestAsHex(md5Pwd1.getBytes(StandardCharsets.UTF_8)) + md5Pwd2;
    }

}
