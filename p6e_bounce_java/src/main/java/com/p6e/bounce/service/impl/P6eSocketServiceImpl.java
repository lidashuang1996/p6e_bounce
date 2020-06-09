package com.p6e.bounce.service.impl;

import com.p6e.bounce.config.P6eSourceConfig;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eSocketParamDto;
import com.p6e.bounce.model.dto.P6eSocketResultDto;
import com.p6e.bounce.service.P6eSocketService;
import com.p6e.bounce.utils.GsonUtil;
import com.p6e.bounce.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class P6eSocketServiceImpl implements P6eSocketService {

    private String baseUrl;

    @Autowired
    public P6eSocketServiceImpl(P6eSourceConfig p6eSourceConfig) {
        this.baseUrl = p6eSourceConfig.getBaseUrl();
    }

    @Override
    public P6eListResultDto<P6eSocketResultDto> list() {
        P6eListResultDto<P6eSocketResultDto> p6eListResultDto = new P6eListResultDto<>();
        try {
            String getResult = HttpUtil.get(this.baseUrl + "/socket/list");
            List<P6eSocketResultDto> p6eSocketResultDtoList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                P6eSocketResultDto p6eSocketResultDto = new P6eSocketResultDto();
                p6eSocketResultDto.setSid("SID_" + i);
                p6eSocketResultDto.setStatus("STATUS_" + i);
                p6eSocketResultDto.setName("NAME_" + i);
                p6eSocketResultDto.setDescribe("DESCRIBE_" + i);
                p6eSocketResultDto.setDate("DATE_" + i);
                p6eSocketResultDtoList.add(p6eSocketResultDto);
            }
            p6eListResultDto.setPage(1L);
            p6eListResultDto.setSize(3L);
            p6eListResultDto.setTotal(3L);
            p6eListResultDto.setList(p6eSocketResultDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            p6eListResultDto.setError("ERROR_SOCKET_CREATE");
        }
        return p6eListResultDto;
    }

    @Override
    public P6eSocketResultDto remove(P6eSocketParamDto param) {
        P6eSocketResultDto p6eSocketResultDto = new P6eSocketResultDto();
        try {
            String sid = param.getSid();
            String getResult = HttpUtil.get(this.baseUrl + "/socket/get?sid=" + sid);
            String removeResult = HttpUtil.get(this.baseUrl + "/socket/remove?sid=" + sid);
            p6eSocketResultDto.setSid("SID_R");
            p6eSocketResultDto.setStatus("STATUS_R");
            p6eSocketResultDto.setName("NAME_R");
            p6eSocketResultDto.setDescribe("DESCRIBE_R");
            p6eSocketResultDto.setDate("DATE_R");
        } catch (Exception e) {
            e.printStackTrace();
            p6eSocketResultDto.setError("ERROR_SOCKET_CREATE");
        }
        return p6eSocketResultDto;
    }

    @Override
    public P6eSocketResultDto create(P6eSocketParamDto param) {
        P6eSocketResultDto p6eSocketResultDto = new P6eSocketResultDto();
        try {
            String sid = param.getSid();
            String result = HttpUtil.post(this.baseUrl + "/socket/create", GsonUtil.toJson(param));
            p6eSocketResultDto.setSid("SID_C");
            p6eSocketResultDto.setStatus("STATUS_C");
            p6eSocketResultDto.setName("NAME_C");
            p6eSocketResultDto.setDescribe("DESCRIBE_C");
            p6eSocketResultDto.setDate("DATE_C");
        } catch (Exception e) {
            e.printStackTrace();
            p6eSocketResultDto.setError("ERROR_SOCKET_CREATE");
        }
        return p6eSocketResultDto;
    }

}
