package com.p6e.bounce.controller.geetest;

/**
 * 配置文件，可合理选择properties等方式自行设计
 */
class GeetestConfig {

    /**
     * 填入自己在极验官网申请的账号id和key
     */
    static final String geetestId = "12a7f1ed3a4476ebe9e69940d476c5fa";

    static final String geetestKey = "b20c630e7c5f638484d3694cc15b5add";

    /**
     * 调试开关，是否输出调试日志
     */
    static final boolean isDebug = true;

    /**
     * 以下字段值与前端交互，基本无需改动。
     * 极验验证API服务状态Session Key
     */
    static final String GEETEST_SERVER_STATUS_SESSION_KEY = "gt_server_status";

    /**
     * 极验二次验证表单传参字段 chllenge
     */
    static final String GEETEST_CHALLENGE = "geetest_challenge";

    /**
     * 极验二次验证表单传参字段 validate
     */
    static final String GEETEST_VALIDATE = "geetest_validate";

    /**
     * 极验二次验证表单传参字段 seccode
     */
    static final String GEETEST_SECCODE = "geetest_seccode";

}
