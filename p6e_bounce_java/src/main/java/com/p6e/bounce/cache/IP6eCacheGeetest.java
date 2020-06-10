package com.p6e.bounce.cache;

/**
 * 缓存 GEETEST 数据的接口
 * @version 1.0
 * @author LiDaShuang
 */
public interface IP6eCacheGeetest {

    /** 缓存 GEETEST 名称 */
    static final String NAME = "P6E:GEETEST:";

    /**
     * 读取一条 GEETEST 缓存数据
     * @param key GEETEST 缓存的 KEY
     * @return GEETEST 缓存的 VALUE
     */
    public String get(String key);

    /**
     * 写入一条 GEETEST 缓存数据
     * @param key GEETEST 缓存的 KEY
     * @param value GEETEST 缓存的 VALUE
     */
    public void set(String key, String value);

}
