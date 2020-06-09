package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eProverbResultDto;
import com.p6e.bounce.model.vo.P6eProverbResultVo;
import com.p6e.bounce.service.P6eProverbService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 谚语的接口
 */
@RestController
@RequestMapping("/proverb")
public class P6eProverbController extends P6eBaseController {

    /**
     * 注入谚语的服务对象
     */
    @Autowired
    private P6eProverbService p6eProverbService;

    /**
     * 获取谚语的接口
     * @return 通用返回类型
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public P6eResultModel def() {
        try {
            P6eProverbResultDto p6eProverbResultDto = p6eProverbService.get();
            if (p6eProverbResultDto != null && p6eProverbResultDto.getError() == null) {
                return P6eResultModel.build(P6eResultConfig.SUCCESS_PROVERB,
                        CopyUtil.run(p6eProverbResultDto, P6eProverbResultVo.class));
            } else return P6eResultModel.build(P6eResultConfig.ERROR_PROVERB_NO_EXISTENT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

}
