package com.p6e.bounce.cache;

/**
 * 缓存认证数据的接口
 * @version 1.0
 * @author LiDaShuang
 */
public interface IP6eCacheAuth {

    /** 用户认证的信息 */
    static final String USER_INFO = "P6E:USER:AUTH:INFO:";
    /** 用户认证的TOKEN */
    static final String USER_TOKEN = "P6E:USER:AUTH:TOKEN:";
    /** 用户认证的 REFRESHTOKEN */
    static final String USER_REFRESH_TOKEN = "P6E:USER:AUTH:REFRESH_TOKEN:";
    /** 用户认证的过期时间戳 */
    static final long USER_EXPIRATION = 604800L;

    /**
     * 写入一条用户信息的缓存
     * @param key 用户的 ID
     * @param value 用户的信息
     */
    public void setUserInfo(String key, String value);

    /**
     * 写入一条用户 TOKEN 的缓存
     * @param key 用户的 TOKEN
     * @param value 用户的 ID
     */
    public void setUserToken(String key, String value);

    /**
     * 写入一条用户 REFRESH TOKEN 的缓存
     * @param key 用户的 REFRESH TOKEN
     * @param value 用户的 ID
     */
    public void setUserRefreshToken(String key, String value);

    /**
     * 查询用户 ID 对应的用户数据
     * @param key 用户的 ID
     * @return 用户信息
     */
    public String getUserInfo(String key);

    /**
     * 查询 TOKEN 对应的 ID
     * @param key 用户的 TOKEN
     * @return 用户的 TOKEN 对于的用户 ID
     */
    public String getUserToken(String key);

    /**
     * 查询 REFRESH TOKEN 对应的 ID
     * @param key 用户的 REFRESH TOKEN
     * @return 用户的 REFRESH TOKEN 对于的用户 ID
     */
    public String getUserRefreshToken(String key);

    /**
     * 删除用户 ID 对应的用户数据
     * @param key 用户 ID
     */
    public void delUserInfo(String key);

    /**
     * 删除用户 TOKEN 的数据
     * @param key 用户 TOKEN
     */
    public void delUserToken(String key);

    /**
     * 删除用户 REFRESH TOKEN 的数据
     * @param key 用户 REFRESH TOKEN
     */
    public void delUserRefreshToken(String key);

}
