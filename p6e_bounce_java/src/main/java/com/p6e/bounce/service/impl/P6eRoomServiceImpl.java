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
import java.util.List;
import java.util.Map;

/**
 * 该类是对 P6eRoomService.class 的实现
 * @author LiDaShuang
 * @version 1.0
 */
@Service
public class P6eRoomServiceImpl implements P6eRoomService {

    /** HTTP 调用核心服务的 BASE URL */
    private final String baseUrl;

    /** 构造方法注入参数 */
    @Autowired
    public P6eRoomServiceImpl(P6eSourceConfig p6eSourceConfig) {
        this.baseUrl = p6eSourceConfig.getBaseUrl();
    }

    @Override
    public P6eListResultDto<P6eRoomResultDto> list() {
        P6eListResultDto<P6eRoomResultDto> p6eListResultDto = new P6eListResultDto<>();
        try {
            String resultRoomList = HttpUtil.get(this.baseUrl + "/room/list");
            Map<String, Object> resultRoomListMap =
                    GsonUtil.fromJson(resultRoomList, new TypeToken<Map<String, Object>>(){}.getType());
            int code = Double.valueOf(resultRoomListMap.get("code").toString()).intValue();
            if (code == 200) {
                @SuppressWarnings("all") // 删除全部提示
                Map<Object, Object> resultRoomListMapData = (Map) resultRoomListMap.get("data");
                p6eListResultDto.setPage(Double.valueOf(resultRoomListMapData.get("code").toString()).longValue());
                p6eListResultDto.setSize(Double.valueOf(resultRoomListMapData.get("size").toString()).longValue());
                p6eListResultDto.setTotal(Double.valueOf(resultRoomListMapData.get("total").toString()).longValue());

                @SuppressWarnings("all") // 删除全部提示
                List<P6eRoomResultDto> resultRoomListMapDataTable = (List) resultRoomListMapData.get("list");
                p6eListResultDto.setList(resultRoomListMapDataTable);

            } else p6eListResultDto.setError("ERROR_ROOM_LIST");
        } catch (Exception e) {
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
            String resultRoomCreate = HttpUtil.get(this.baseUrl + "/room/create?rid=" + rid);
            Map<String, Object> resultRoomCreateMap =
                    GsonUtil.fromJson(resultRoomCreate, new TypeToken<Map<String, Object>>() {}.getType());
            int code = Double.valueOf(resultRoomCreateMap.get("code").toString()).intValue();
            if (code == 200) {
                String resultRoomGet = HttpUtil.get(this.baseUrl + "/room/get?rid=" + rid);
                Map<String, Object> resultRoomGetMap =
                        GsonUtil.fromJson(resultRoomGet, new TypeToken<Map<String, Object>>() {}.getType());
                code = Double.valueOf(resultRoomGetMap.get("code").toString()).intValue();
                if (code == 200) {
                    p6eRoomResultDto.setRid(resultRoomGetMap.get("data").toString());
                } else p6eRoomResultDto.setError("ERROR_ROOM_CREATE");
            } else p6eRoomResultDto.setError("ERROR_ROOM_CREATE");
        } catch (Exception e) {
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
            String resultRoomGet = HttpUtil.get(this.baseUrl + "/room/get?rid=" + rid);
            Map<String, Object> resultRoomGetMap =
                    GsonUtil.fromJson(resultRoomGet, new TypeToken<Map<String, Object>>() {}.getType());
            int code = Double.valueOf(resultRoomGetMap.get("code").toString()).intValue();
            if (code == 200) {
                String resultRoomRemove = HttpUtil.get(this.baseUrl + "/room/remove?rid=" + rid);
                Map<String, Object> resultRoomRemoveMap =
                        GsonUtil.fromJson(resultRoomRemove, new TypeToken<Map<String, Object>>() {}.getType());
                code = Double.valueOf(resultRoomRemoveMap.get("code").toString()).intValue();
                if (code == 200) {
                    p6eRoomResultDto.setRid(resultRoomRemoveMap.get("data").toString());
                } else p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
            } else p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
        } catch (Exception e) {
            e.printStackTrace();
            p6eRoomResultDto.setError("ERROR_ROOM_REMOVE");
        }
        return p6eRoomResultDto;
    }

}
