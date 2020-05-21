package com.p6e.bounce.controller;


import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eUserParamDto;
import com.p6e.bounce.model.vo.P6eUserParamVo;
import com.p6e.bounce.service.P6eUserService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class P6eUserController {

    @Autowired
    private P6eUserService p6eUserService;

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public P6eResultModel def() {
        return list(new P6eUserParamVo());
    }

    @PostMapping("/list")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public P6eResultModel list(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST, p6eUserService.list(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/create")
    public P6eResultModel create(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST, p6eUserService.create(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE, RequestMethod.POST })
    public P6eResultModel delete(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST,
                    p6eUserService.delete(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.POST })
    public P6eResultModel update(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST, p6eUserService.update(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }
}
