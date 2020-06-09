package com.p6e.bounce.cache;

public interface IP6eCacheClient {

    static final String CLIENT_IP = "P6E:CLIENT:IP:";
    static final String CLIENT_ID = "P6E:CLIENT:ID:";

    static final long CLIENT_EXPIRATION = 604800L;

    public long setData(String id, String ip);

}
