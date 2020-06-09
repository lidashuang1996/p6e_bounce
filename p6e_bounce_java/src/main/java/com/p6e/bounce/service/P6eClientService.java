package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eClientParamDto;
import com.p6e.bounce.model.dto.P6eClientResultDto;

public interface P6eClientService {

    public P6eClientResultDto generate(P6eClientParamDto param);
}
