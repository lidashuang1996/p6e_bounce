package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eProverbParamDto;
import com.p6e.bounce.model.dto.P6eProverbResultDto;

/**
 * 谚语服务类
 */
public interface P6eProverbService {

    /** 获取一条谚语数据 */
    public P6eProverbResultDto get();

    /** 创建一条谚语数据 */
    public P6eProverbResultDto create(P6eProverbParamDto param);

}
