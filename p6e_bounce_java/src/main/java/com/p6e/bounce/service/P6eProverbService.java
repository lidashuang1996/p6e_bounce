package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eProverbParamDto;
import com.p6e.bounce.model.dto.P6eProverbResultDto;

public interface P6eProverbService {

    public P6eProverbResultDto get();

    public P6eProverbResultDto create(P6eProverbParamDto param);

}
