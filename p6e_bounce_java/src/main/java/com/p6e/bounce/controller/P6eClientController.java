package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eClientParamDto;
import com.p6e.bounce.model.dto.P6eClientResultDto;
import com.p6e.bounce.model.vo.p6eClientResultVo;
import com.p6e.bounce.service.P6eClientService;
import com.p6e.bounce.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * 获取 CLIENT ID
 * 每一个浏览器客户端都需要一个 CLIENT ID 做为客户端的记号
 * @version 1.0
 * @author LiDaShuang
 */
@RestController
@RequestMapping("/client")
public class P6eClientController extends P6eBaseController {

    /** 注入日志对象 */
    private static final Logger logger = LoggerFactory.getLogger(P6eClientController.class);

    /** 注入服务对象 */
    @Autowired
    private P6eClientService p6eClientService;

    /**
     * 获取客户端 ID
     * 生成日志跟踪客户端 ID 的行为轨迹
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel def() {
        try {
            P6eClientResultDto p6eClientResultDto = p6eClientService.generate(new P6eClientParamDto(obtainIp()));
            if (p6eClientResultDto.getError() == null) {
                return P6eResultModel.build(P6eResultConfig.SUCCESS_CLIENT_GENERATE,
                        CopyUtil.run(p6eClientResultDto, p6eClientResultVo.class));
            } else return P6eResultModel.build(P6eResultConfig.ERROR_CLIENT_GENERATE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
