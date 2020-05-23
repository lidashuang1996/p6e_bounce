package com.p6e.bounce.service.impl;

import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eUserParamDto;
import com.p6e.bounce.model.dto.P6eUserResultDto;
import com.p6e.bounce.service.P6eSignService;
import com.p6e.bounce.service.P6eUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class P6eUserServiceImpl implements P6eUserService {

    @Autowired
    private P6eSignService p6eSignService;

    @Override
    public P6eListResultDto<P6eUserResultDto> list(P6eUserParamDto param) {
        return null;
    }

    @Override
    public P6eUserResultDto delete(P6eUserParamDto param) {
        return null;
    }

    @Override
    public P6eUserResultDto create(P6eUserParamDto param) {
        return null;
    }

    @Override
    public P6eUserResultDto update(P6eUserParamDto param) {
        p6eSignService.out("");
        return null;
    }
}
