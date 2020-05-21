package com.p6e.bounce.controller;

import com.p6e.bounce.model.P6eResultModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class P6eClientController {

    @GetMapping("/")
    public P6eResultModel def() {
        try {
            String uid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
            Map<String, String> map = new HashMap<>();
            map.put("id", uid);
            return P6eResultModel.build("200-SUCCESS", map);
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

}
