package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eUserParamDto;
import com.p6e.bounce.model.dto.P6eUserResultDto;

public interface P6eUserService {

    public P6eListResultDto<P6eUserResultDto> list(P6eUserParamDto param);

    public P6eUserResultDto delete(P6eUserParamDto param);

    public P6eUserResultDto create(P6eUserParamDto param);

    public P6eUserResultDto update(P6eUserParamDto param);

}
