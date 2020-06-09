package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eRoomParamDto;
import com.p6e.bounce.model.dto.P6eRoomResultDto;
import com.p6e.bounce.model.vo.P6eListResultVo;
import com.p6e.bounce.model.vo.P6eRoomParamVo;
import com.p6e.bounce.model.vo.P6eRoomResultVo;
import com.p6e.bounce.service.P6eRoomService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class P6eRoomController extends P6eBaseController {

    @Autowired
    private P6eRoomService p6eRoomService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel def() {
        return list();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel list() {
        try {
            P6eListResultDto<P6eRoomResultDto> p6eListResultDto = p6eRoomService.list();
            P6eListResultVo<P6eRoomResultVo> p6eListResultVo = new P6eListResultVo<>(
                    CopyUtil.run(p6eListResultDto.getList(), P6eRoomResultVo.class),
                    p6eListResultDto.getPage(),
                    p6eListResultDto.getSize(),
                    p6eListResultDto.getTotal()
            );
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_LIST, p6eListResultVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @PostMapping("/create")
    public P6eResultModel create(@RequestBody P6eRoomParamVo param) {
        try {
            P6eRoomResultDto p6eRoomResultDto = p6eRoomService.create(CopyUtil.run(param, P6eRoomParamDto.class));
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_CREAETE,
                    CopyUtil.run(p6eRoomResultDto, P6eRoomResultVo.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.POST, RequestMethod.DELETE})
    public P6eResultModel remove(@RequestBody P6eRoomParamVo param) {
        try {
            P6eRoomResultDto p6eRoomResultDto = p6eRoomService.remove(CopyUtil.run(param, P6eRoomParamDto.class));
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_REMOVE,
                    CopyUtil.run(p6eRoomResultDto, P6eRoomResultVo.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
