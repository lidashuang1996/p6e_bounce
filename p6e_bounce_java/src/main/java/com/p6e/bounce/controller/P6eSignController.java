package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eSignResultDto;
import com.p6e.bounce.model.vo.P6eSignParamVo;
import com.p6e.bounce.model.vo.P6eSignResultVo;
import com.p6e.bounce.service.P6eSignService;
import com.p6e.bounce.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
public class P6eSignController extends P6eBaseController {

    private static final Logger logger = LoggerFactory.getLogger(P6eSignController.class);

    @Autowired
    private P6eSignService p6eSignService;

    /**
     * 获取谚语的接口
     * @return 通用返回类型
     */
    @RequestMapping(value = "/out", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel out(P6eSignParamVo param) {
        try {
            if (param == null || param.getToken() == null) {
                return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            } else {
                P6eSignResultDto p6eSignResultDto = p6eSignService.out(param.getToken());
                if (p6eSignResultDto.getError() != null) {
                    logger.error("[ SING OUT ] ==> TOKEN: " + param.getToken() + " clean error.");
                }
                return P6eResultModel.build(P6eResultConfig.SUCCESS_SIGN_REFRESH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel refresh(P6eSignParamVo param) {
        try {
            if (param == null || param.getRefreshToken() == null) {
                return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            } else {
                P6eSignResultDto p6eSignResultDto = p6eSignService.refresh(param.getRefreshToken());
                if (p6eSignResultDto.getError() == null) {
                    return P6eResultModel.build(P6eResultConfig.SUCCESS_SIGN_REFRESH,
                            CopyUtil.run(p6eSignResultDto, P6eSignResultVo.class));
                } else return P6eResultModel.build(P6eResultConfig.ERROR_SIGN_REFRESH_NO_EXISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}

