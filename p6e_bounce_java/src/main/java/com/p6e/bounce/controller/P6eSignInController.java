package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eSignInParamDto;
import com.p6e.bounce.model.dto.P6eSignInResultDto;
import com.p6e.bounce.model.vo.P6eSignInParamVo;
import com.p6e.bounce.model.vo.P6eSignInResultVo;
import com.p6e.bounce.service.P6eSignInService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录的接口
 */
@RestController
@RequestMapping("/sign/in")
public class P6eSignInController extends P6eBaseController {

    /**
     * 注入登录的服务对象
     */
    @Autowired
    private P6eSignInService p6eSignInService;

    /**
     * 默认登录的接口
     * @return 通用返回类型
     */
    @RequestMapping("/")
    public P6eResultModel def(@RequestBody P6eSignInParamVo param) {
        try {
            if (param == null || param.getAccount() == null || param.getPassword() == null) {
                return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            } else {
                P6eSignInResultDto result = p6eSignInService.def(CopyUtil.run(param, P6eSignInParamDto.class));
                if (result == null || "ACCOUNT_OR_PASSWORD_ERROR".equals(result.getError())) {
                    return P6eResultModel.build(P6eResultConfig.ERROR_ACCOUNT_OR_PASSWORD);
                } else return P6eResultModel.build(
                        P6eResultConfig.SUCCESS_SIGN_IN_DEF, CopyUtil.run(result, P6eSignInResultVo.class));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
