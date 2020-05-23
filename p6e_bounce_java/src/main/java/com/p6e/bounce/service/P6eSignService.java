package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eSignResultDto;

public interface P6eSignService {

    public P6eSignResultDto out(String token);

    public P6eSignResultDto refresh(String refreshToken);

}
