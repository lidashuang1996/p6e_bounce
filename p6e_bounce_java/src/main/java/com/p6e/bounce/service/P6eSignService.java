package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eSignResultDto;

/**
 * 认证之后操作
 * @author LiDaShuang
 * @version 1.0
 */
public interface P6eSignService {

    public P6eSignResultDto out(String token);

    public P6eSignResultDto refresh(String refreshToken);

}
