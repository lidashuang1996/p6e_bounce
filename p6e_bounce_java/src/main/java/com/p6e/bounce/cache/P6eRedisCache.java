package com.p6e.bounce.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 该类为 IP6eCache.class 接口的 REDIS 实现
 * @author LiDaShuang
 * @version 1.0
 */
@Component
public class P6eRedisCache implements IP6eCache {

    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Override
    public String toType() {
        return "REDIS_CACHE_TYPE";
    }

}
