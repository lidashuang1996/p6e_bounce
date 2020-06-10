package com.p6e.bounce.cache;

import org.springframework.stereotype.Component;

/**
 * 该类为 IP6eCacheGeetest.class 接口的 REDIS 实现
 * @author LiDaShuang
 * @version 1.0
 */
@Component
public class P6eRedisCacheGeetest extends P6eRedisCache implements IP6eCacheGeetest {
    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(NAME + key);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(NAME + key, value);
    }
}
