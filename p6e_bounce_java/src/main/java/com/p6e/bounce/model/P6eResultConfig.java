package com.p6e.bounce.model;

public class P6eResultConfig {
    /** 参数异常 */
    public static String ERROR_PARAM_EXCEPTION = "400-ERROR_PARAM_EXCEPTION";
    /** COOKIES 异常 */
    public static String ERROR_COOKIES_EXCEPTION = "400-ERROR_COOKIES_EXCEPTION";
    /** CLIENT ID 获取成功 */
    public static String SUCCESS_CLIENT_GENERATE = "200-SUCCESS_CLIENT_GENERATE";
    public static String ERROR_CLIENT_GENERATE = "500-ERROR_CLIENT_GENERATE";
    /** 账号或者密码错误 */
    public static String ERROR_ACCOUNT_OR_PASSWORD = "400-ERROR_ACCOUNT_OR_PASSWORD";
    /** 文件上传不为文件错误 */
    public static String ERROR_NO_FILE = "400-ERROR_NO_FILE";
    /** 创建文件夹 不是一个文件夹 */
    public static String ERROR_NO_FOLDER = "400-ERROR_NO_FOLDER";
    /** 创建文件夹 文件夹存在 */
    public static String ERROR_FOLDER_EXISTENCE = "400-ERROR_FOLDER_EXISTENCE";

    /** 登录成功 */
    public static String SUCCESS_SIGN_IN_DEF = "200-SUCCESS_SIGN_IN_DEF";
    public static String SUCCESS_GEETEST = "200-SUCCESS_GEETEST";

    /** 用户成功 */
    public static String SUCCESS_USER_LIST = "200-SUCCESS_USER_LIST";

    /** 房间成功 */
    public static String SUCCESS_ROOM_LIST = "200-SUCCESS_ROOM_LIST";
    public static String SUCCESS_ROOM_CREAETE = "200-SUCCESS_ROOM_CREAETE";
    public static String SUCCESS_ROOM_REMOVE = "200-SUCCESS_ROOM_REMOVE";

    public static String SUCCESS_SOCKET_LIST = "200-SUCCESS_SOCKET_LIST";
    public static String SUCCESS_SOCKET_CREAETE = "200-SUCCESS_SOCKET_CREAETE";
    public static String SUCCESS_SOCKET_REMOVE = "200-SUCCESS_SOCKET_REMOVE";

    /** 谚语成功 */
    public static String SUCCESS_PROVERB = "200-SUCCESS_PROVERB";
    public static String ERROR_PROVERB_NO_EXISTENT = "400-ERROR_PROVERB_NO_EXISTENT";

    public static String SUCCESS_FILE_ROOT = "200-SUCCESS_FILE_ROOT";
    public static String SUCCESS_FILE_PATH = "200-SUCCESS_FILE_PATH";

    public static String SUCCESS_SIGN_OUT = "200-SUCCESS_SIGN_OUT";
    public static String SUCCESS_SIGN_REFRESH = "200-SUCCESS_SIGN_REFRESH";
    public static String ERROR_SIGN_REFRESH_NO_EXISTENT = "400-ERROR_SIGN_REFRESH_NO_EXISTENT";

    /** 服务器内部出现异常 */
    public static String ERROR_SERVICE_INSIDE = "500-ERROR_SERVICE_INSIDE";
}
