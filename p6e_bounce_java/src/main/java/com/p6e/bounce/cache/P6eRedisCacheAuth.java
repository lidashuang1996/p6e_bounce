package com.p6e.bounce.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 该类为 IP6eCacheAuth.class 接口的实现
 * @author LiDaShuang
 * @version 1.0
 */
@Component
public class P6eRedisCacheAuth extends P6eRedisCache implements IP6eCacheAuth {

    @Override
    public void setUserInfo(String key, String value) {
        redisTemplate.opsForValue().set(USER_INFO + key, value, USER_EXPIRATION, TimeUnit.SECONDS);
    }

    @Override
    public void setUserToken(String key, String value) {
        redisTemplate.opsForValue().set(USER_TOKEN + key, value, USER_EXPIRATION, TimeUnit.SECONDS);
    }

    @Override
    public void setUserRefreshToken(String key, String value) {
        redisTemplate.opsForValue().set(USER_REFRESH_TOKEN + key, value, USER_EXPIRATION, TimeUnit.SECONDS);
    }

    @Override
    public String getUserInfo(String key) {
        return redisTemplate.opsForValue().get(USER_INFO + key);
    }

    @Override
    public String getUserToken(String key) {
        return redisTemplate.opsForValue().get(USER_TOKEN + key);
    }

    @Override
    public String getUserRefreshToken(String key) {
        return redisTemplate.opsForValue().get(USER_REFRESH_TOKEN + key);
    }

    @Override
    public void delUserInfo(String key) {
        redisTemplate.delete(USER_INFO + key);
    }

    @Override
    public void delUserToken(String key) {
        redisTemplate.delete(USER_TOKEN + key);
    }

    @Override
    public void delUserRefreshToken(String key) {
        redisTemplate.delete(USER_REFRESH_TOKEN + key);
    }
}
