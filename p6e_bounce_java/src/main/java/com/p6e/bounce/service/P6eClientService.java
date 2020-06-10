package com.p6e.bounce.service;

import com.p6e.bounce.model.dto.P6eClientParamDto;
import com.p6e.bounce.model.dto.P6eClientResultDto;

/**
 * 客户端 ID 生成
 * @author LiDaShuang
 * @version 1.0
 */
public interface P6eClientService {

    /**
     * 生成客户端 ID 数据
     * @param param 客户端参数对象
     * @return 客户端结果对象
     */
    public P6eClientResultDto generate(P6eClientParamDto param);

}
