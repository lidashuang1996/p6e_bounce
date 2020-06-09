package com.p6e.bounce.service.impl;

import com.p6e.bounce.cache.P6eRedisCacheClient;
import com.p6e.bounce.model.dto.P6eClientParamDto;
import com.p6e.bounce.model.dto.P6eClientResultDto;
import com.p6e.bounce.service.P6eClientService;
import com.p6e.bounce.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class P6eClientServiceImpl implements P6eClientService {

    @Autowired
    private P6eRedisCacheClient p6eRedisCacheClient;

    @Override
    public P6eClientResultDto generate(P6eClientParamDto param) {
        String ip = param.getIp();
        String id = CommonUtil.generateUUID();
        P6eClientResultDto p6eClientResultDto = new P6eClientResultDto();
        if (p6eRedisCacheClient.setData(id, ip) <= 0) p6eClientResultDto.setError("ERROR_GENERATE_CLIENT_ID");
        else {
            p6eClientResultDto.setIp(ip);
            p6eClientResultDto.setCid(id);
            p6eClientResultDto.setDate(CommonUtil.nowDate());
        }
        return p6eClientResultDto;
    }
}
