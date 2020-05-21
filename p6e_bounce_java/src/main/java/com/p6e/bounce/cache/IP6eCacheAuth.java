package com.p6e.bounce.cache;

public interface IP6eCacheAuth {

    static final String USER_INFO = "P6E:USER:AUTH:INFO:";
    static final String USER_TOKEN = "P6E:USER:AUTH:TOKEN:";
    static final String USER_REFRESH_TOKEN = "P6E:USER:AUTH:REFRESH_TOKEN:";

    public void setUserInfo(String key, String value);
    public void setUserToken(String key, String value);
    public void setUserRefreshToken(String key, String value);

    public String getUserInfo(String key);
    public String getUserToken(String key);
    public String getUserRefreshToken(String key);

    public void delUserInfo(String key);
    public void delUserToken(String key);
    public void delUserRefreshToken(String key);

}
