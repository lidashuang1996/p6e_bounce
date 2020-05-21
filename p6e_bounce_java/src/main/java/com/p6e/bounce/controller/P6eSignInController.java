package com.p6e.bounce.controller;

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

@RestController
@RequestMapping("/sign/in")
public class P6eSignInController {

    @Autowired
    private P6eSignInService p6eSignInService;

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
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

}
