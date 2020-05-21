package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eSignInParamDto;
import com.p6e.bounce.model.dto.P6eSignInResultDto;

public interface P6eSignInService {

    /**
     * 根据账号密码查询，验证用户是否登录成功
     * 如果登录成功，写入数据到缓存，并返回数据
     * @param param 查询对象
     * @return 结果对象
     */
    public P6eSignInResultDto def(P6eSignInParamDto param);

}
