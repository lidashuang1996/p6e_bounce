package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eSocketParamDto;
import com.p6e.bounce.model.vo.P6eRoomParamVo;
import com.p6e.bounce.service.P6eSocketService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 该类是 socket client 的操作接口
 * @author LiDaShuang
 * @version 1.0
 */
@RestController
@RequestMapping("/socket")
public class P6eSocketController extends P6eBaseController {

    @Autowired
    private P6eSocketService p6eSocketService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel def() {
        return list();
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel list() {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_LIST, p6eSocketService.list());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @PostMapping("/create")
    public P6eResultModel create(@RequestBody P6eRoomParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_CREAETE,
                    p6eSocketService.create(CopyUtil.run(param, P6eSocketParamDto.class)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.POST, RequestMethod.DELETE})
    public P6eResultModel remove(@RequestBody P6eRoomParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_ROOM_REMOVE,
                    p6eSocketService.remove(CopyUtil.run(param, P6eSocketParamDto.class)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
