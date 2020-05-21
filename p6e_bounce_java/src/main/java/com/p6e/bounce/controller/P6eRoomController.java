package com.p6e.bounce.controller;

import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eRoomParamDto;
import com.p6e.bounce.model.vo.P6eRoomParamVo;
import com.p6e.bounce.service.P6eRoomService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class P6eRoomController {

    @Autowired
    private P6eRoomService p6eRoomService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel def() {
        return list();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel list() {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_LIST, p6eRoomService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/create")
    public P6eResultModel create(@RequestBody P6eRoomParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_CREAETE,
                    p6eRoomService.create(CopyUtil.run(param, P6eRoomParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.POST, RequestMethod.DELETE})
    public P6eResultModel remove(@RequestBody P6eRoomParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_REMOVE,
                    p6eRoomService.remove(CopyUtil.run(param, P6eRoomParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

}
