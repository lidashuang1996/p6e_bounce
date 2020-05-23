package com.p6e.bounce.controller;

import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.utils.CommonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


/**
 * 获取 CLIENT ID
 * 每一个浏览器客户端都需要一个 CLIENT ID 做为客户端的记号
 */
@RestController
@RequestMapping("/client")
public class P6eClientController {

    @GetMapping("/")
    public P6eResultModel def() {
        try {
            String uid = CommonUtil.generateUUID();
            Map<String, String> map = new HashMap<>();
            map.put("id", uid);
            return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_ROOT, map);
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
