package com.p6e.bounce.service.impl;

import com.google.gson.reflect.TypeToken;
import com.p6e.bounce.config.P6eSourceConfig;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eSocketParamDto;
import com.p6e.bounce.model.dto.P6eSocketResultDto;
import com.p6e.bounce.service.P6eSocketService;
import com.p6e.bounce.utils.GsonUtil;
import com.p6e.bounce.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 该类是对 P6eSocketService.class 的实现
 * @author LiDaShuang
 * @version 1.0
 */
@Service
public class P6eSocketServiceImpl implements P6eSocketService {

    /** HTTP 调用核心服务的 BASE URL */
    private final String baseUrl;

    /** 构造方法注入参数 */
    @Autowired
    public P6eSocketServiceImpl(P6eSourceConfig p6eSourceConfig) {
        this.baseUrl = p6eSourceConfig.getBaseUrl();
    }

    @Override
    public P6eListResultDto<P6eSocketResultDto> list() {
        P6eListResultDto<P6eSocketResultDto> p6eListResultDto = new P6eListResultDto<>();
        try {
            String resultSocketList = HttpUtil.get(this.baseUrl + "/socket/list");
            Map<String, Object> resultSocketListMap =
                    GsonUtil.fromJson(resultSocketList, new TypeToken<Map<String, Object>>() {}.getType());
            int code = Double.valueOf(resultSocketListMap.get("code").toString()).intValue();
            if (code == 200) {
                @SuppressWarnings("all") // 删除全部提示
                Map<Object, Object> resultSocketListMapData = (Map) resultSocketListMap.get("data");

                long page = Double.valueOf(resultSocketListMapData.get("code").toString()).longValue();
                long size = Double.valueOf(resultSocketListMapData.get("size").toString()).longValue();
                long total = Double.valueOf(resultSocketListMapData.get("total").toString()).longValue();

                p6eListResultDto.setPage(page);
                p6eListResultDto.setSize(size);
                p6eListResultDto.setTotal(total);

                @SuppressWarnings("all") // 删除全部提示
                List<P6eSocketResultDto> resultSocketListMapDataTable = (List) resultSocketListMapData.get("list");
                p6eListResultDto.setList(resultSocketListMapDataTable);

            } else p6eListResultDto.setError("ERROR_SOCKET_LIST");
        } catch (Exception e) {
            e.printStackTrace();
            p6eListResultDto.setError("ERROR_SOCKET_LIST");
        }
        return p6eListResultDto;
    }

    @Override
    public P6eSocketResultDto remove(P6eSocketParamDto param) {
        P6eSocketResultDto p6eSocketResultDto = new P6eSocketResultDto();
        try {
            String sid = param.getSid();
            String resultSocketGet = HttpUtil.get(this.baseUrl + "/socket/get?sid=" + sid);
            Map<String, Object> resultSocketGetMap =
                    GsonUtil.fromJson(resultSocketGet, new TypeToken<Map<String, Object>>() {}.getType());
            int code = Double.valueOf(resultSocketGetMap.get("code").toString()).intValue();
            if (code == 200) {
                String resultSocketRemove = HttpUtil.get(this.baseUrl + "/socket/remove?sid=" + sid);
                Map<String, Object> resultSocketRemoveMap =
                        GsonUtil.fromJson(resultSocketRemove, new TypeToken<Map<String, Object>>() {}.getType());
                code = Double.valueOf(resultSocketRemoveMap.get("code").toString()).intValue();
                if (code == 200) dataMapToResultDto(resultSocketGetMap, p6eSocketResultDto);
                else p6eSocketResultDto.setError("ERROR_SOCKET_REMOVE");
            } else p6eSocketResultDto.setError("ERROR_SOCKET_REMOVE");
        } catch (Exception e) {
            e.printStackTrace();
            p6eSocketResultDto.setError("ERROR_SOCKET_REMOVE");
        }
        return p6eSocketResultDto;
    }

    @Override
    public P6eSocketResultDto create(P6eSocketParamDto param) {
        P6eSocketResultDto p6eSocketResultDto = new P6eSocketResultDto();
        try {
            String resultSocketCreate = HttpUtil.post(this.baseUrl + "/socket/create", GsonUtil.toJson(param));
            Map<String, Object> resultSocketCreateMap =
                    GsonUtil.fromJson(resultSocketCreate, new TypeToken<Map<String, Object>>() {}.getType());
            int code = Double.valueOf(resultSocketCreateMap.get("code").toString()).intValue();
            if (code == 200) {
                String resultSocketGet = HttpUtil.get(this.baseUrl + "/socket/get?sid=" + param.getSid());
                Map<String, Object> resultSocketGetMap =
                        GsonUtil.fromJson(resultSocketGet, new TypeToken<Map<String, Object>>() {}.getType());
                code = Double.valueOf(resultSocketGetMap.get("code").toString()).intValue();
                if (code == 200) dataMapToResultDto(resultSocketGetMap, p6eSocketResultDto);
                else p6eSocketResultDto.setError("ERROR_SOCKET_CREATE");
            } else p6eSocketResultDto.setError("ERROR_SOCKET_CREATE");
        } catch (Exception e) {
            e.printStackTrace();
            p6eSocketResultDto.setError("ERROR_SOCKET_CREATE");
        }
        return p6eSocketResultDto;
    }

    private void dataMapToResultDto(Map<String, Object> dataMap, P6eSocketResultDto resultDto) {
        @SuppressWarnings("all") // 删除全部提示
        Map<String, Object> dataMapData = (Map) dataMap.get("data");
        resultDto.setSid(dataMapData.get("sid").toString());
        resultDto.setStatus(dataMapData.get("status").toString());
        resultDto.setName(dataMapData.get("name").toString());
        resultDto.setDescribe(dataMapData.get("describe").toString());
        resultDto.setDate(dataMapData.get("date").toString());
    }

}
