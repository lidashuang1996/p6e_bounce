package com.p6e.bounce.cache;

public interface IP6eCacheGeetest {

    static final String NAME = "P6E:GEETEST:";

    public String get(String key);
    public void set(String key, String value);

}
