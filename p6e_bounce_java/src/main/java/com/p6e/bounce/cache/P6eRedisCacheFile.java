package com.p6e.bounce.cache;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class P6eRedisCacheFile extends P6eRedisCache implements IP6eCacheFile {

    @Override
    public void set(String key) {
        String value = "FILE CREATE DATE => " + LocalDateTime.now().toString();
        redisTemplate.opsForValue().set(FILE_MARK + key, value, 1800, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(FILE_MARK_LASTING + key, value);
    }

}
