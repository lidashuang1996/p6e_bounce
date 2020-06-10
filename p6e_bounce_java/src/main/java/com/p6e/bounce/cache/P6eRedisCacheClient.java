package com.p6e.bounce.cache;

import com.p6e.bounce.utils.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 该类为 IP6eCacheClient.class 接口的 REDIS 实现
 * @author LiDaShuang
 * @version 1.0
 */
@Component
public class P6eRedisCacheClient extends P6eRedisCache implements IP6eCacheClient {

    @Override
    public long setData(String id, String ip) {
        Long result = redisTemplate.opsForValue().increment(CLIENT_IP + ip);
        redisTemplate.opsForValue().set(CLIENT_ID + id, "{\"ip\":\"" + ip
                + "\",\"date\":\"" + CommonUtil.nowDate() + "\"}", CLIENT_EXPIRATION, TimeUnit.SECONDS);
        return result == null ? 0: result;
    }
}
