package com.p6e.bounce.cache;

public interface IP6eCacheFile {

    static final String FILE_MARK = "P6E:FILE:MARK:";
    static final String FILE_MARK_LASTING = "P6E:FILE:MARK:LASTING:";

    public void set(String key);

}
