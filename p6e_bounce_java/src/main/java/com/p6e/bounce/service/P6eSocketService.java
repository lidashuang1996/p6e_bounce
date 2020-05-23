package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eSocketParamDto;
import com.p6e.bounce.model.dto.P6eSocketResultDto;

public interface P6eSocketService {

    public P6eListResultDto<P6eSocketResultDto> list();

    public P6eSocketResultDto remove(P6eSocketParamDto param);

    public P6eSocketResultDto create(P6eSocketParamDto param);

}
