package com.p6e.bounce.service.impl;

import com.google.gson.reflect.TypeToken;
import com.p6e.bounce.config.P6eSourceConfig;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eRoomParamDto;
import com.p6e.bounce.model.dto.P6eRoomResultDto;
import com.p6e.bounce.service.P6eRoomService;
import com.p6e.bounce.utils.GsonUtil;
import com.p6e.bounce.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class P6eRoomServiceImpl implements P6eRoomService {

    private String baseUrl;

    @Autowired
    public P6eRoomServiceImpl(P6eSourceConfig p6eSourceConfig) {
        this.baseUrl = p6eSourceConfig.getBaseUrl();
    }

    @Override
    public P6eListResultDto<P6eRoomResultDto> list() {
        P6eListResultDto<P6eRoomResultDto> p6eListResultDto = new P6eListResultDto<>();
        try {
            String result = HttpUtil.get(this.baseUrl + "/room/list");
            List<P6eRoomResultDto> p6eRoomResultDtoList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                P6eRoomResultDto p6eRoomResultDto = new P6eRoomResultDto();
                p6eRoomResultDto.setRid("RID_" + i);
                p6eRoomResultDto.setName("NAME_" + i);
                p6eRoomResultDto.setDescribe("DESCRIBE_" + i);
                p6eRoomResultDto.setDate("DATE_" + i);
                p6eRoomResultDtoList.add(p6eRoomResultDto);
            }
            p6eListResultDto.setPage(1L);
            p6eListResultDto.setSize(3L);
            p6eListResultDto.setTotal(3L);
            p6eListResultDto.setList(p6eRoomResultDtoList);
        } catch (IOException e) {
            e.printStackTrace();
            p6eListResultDto.setError("ERROR_ROOM_LIST");
        }
        return p6eListResultDto;
    }

    @Override
    public P6eRoomResultDto create(P6eRoomParamDto param) {
        P6eRoomResultDto p6eRoomResultDto = new P6eRoomResultDto();
        try {
            String rid = param.getRid();
            String createResult = HttpUtil.get(this.baseUrl + "/room/create?rid=" + rid);
            Map<String, String> createMap =
                    GsonUtil.fromJson(createResult, new TypeToken<Map<String, Object>>() {}.getType());
            if (createMap != null && "200".equals(createMap.get("code"))) {
                String getResult = HttpUtil.get(this.baseUrl + "/room/get?rid=" + rid);
                Map<String, String> getMap =
                        GsonUtil.fromJson(getResult, new TypeToken<Map<String, Object>>() {}.getType());
                if (getMap != null && "200".equals(getMap.get("code"))) {
                    p6eRoomResultDto.setRid(getMap.get("data"));
                } else p6eRoomResultDto.setError("ERROR_ROOM_CREATE");
            } else p6eRoomResultDto.setError("ERROR_ROOM_CREATE");
        } catch (IOException e) {
            e.printStackTrace();
            p6eRoomResultDto.setError("ERROR_ROOM_CREATE");
        }
        return p6eRoomResultDto;
    }

    @Override
    public P6eRoomResultDto remove(P6eRoomParamDto param) {
        P6eRoomResultDto p6eRoomResultDto = new P6eRoomResultDto();
        try {
            String rid = param.getRid();
            String getResult = HttpUtil.get(this.baseUrl + "/room/get?rid=" + rid);
            Map<String, String> getMap =
                    GsonUtil.fromJson(getResult, new TypeToken<Map<String, Object>>() {}.getType());
            if (getMap != null && "200".equals(getMap.get("code"))) {
                String removeResult = HttpUtil.get(this.baseUrl + "/room/remove?rid=" + rid);
                Map<String, String> removeMap =
                        GsonUtil.fromJson(removeResult, new TypeToken<Map<String, Object>>() {}.getType());
                if (removeMap != null && "200".equals(removeMap.get("code"))) {
                    p6eRoomResultDto.setRid(getMap.get("data"));
                } else p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
            } else p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
        } catch (IOException e) {
            e.printStackTrace();
            p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
        }
        return p6eRoomResultDto;
    }

}
