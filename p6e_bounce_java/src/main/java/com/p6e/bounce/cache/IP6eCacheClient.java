package com.p6e.bounce.cache;

/**
 * 缓存客户端数据的接口
 * @version 1.0
 * @author LiDaShuang
 */
public interface IP6eCacheClient {

    /** 客户端 IP 名称 */
    static final String CLIENT_IP = "P6E:CLIENT:IP:";
    /** 客户端 ID 名称 */
    static final String CLIENT_ID = "P6E:CLIENT:ID:";
    /** 客户端缓存的时间戳 */
    static final long CLIENT_EXPIRATION = 604800L;

    /**
     * 写入客户端的数据
     * @param id 客户端的 ID
     * @param ip 客户端的 IP
     * @return 当前 IP 统计的次数
     */
    public long setData(String id, String ip);

}
