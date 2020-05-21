package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eRoomParamDto;
import com.p6e.bounce.model.dto.P6eRoomResultDto;

public interface P6eRoomService {

    public P6eListResultDto<P6eRoomResultDto> list();

    public P6eRoomResultDto create(P6eRoomParamDto param);

    public P6eRoomResultDto remove(P6eRoomParamDto param);

}
