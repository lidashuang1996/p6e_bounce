package com.p6e.bounce.controller;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eUserParamDto;
import com.p6e.bounce.model.dto.P6eUserResultDto;
import com.p6e.bounce.model.vo.P6eListResultVo;
import com.p6e.bounce.model.vo.P6eUserParamVo;
import com.p6e.bounce.model.vo.P6eUserResultVo;
import com.p6e.bounce.service.P6eUserService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/user")
public class P6eUserController extends P6eBaseController {

    @Autowired
    private P6eUserService p6eUserService;

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public P6eResultModel def() {
        return list(new P6eUserParamVo());
    }

    @PostMapping("/list")
    public P6eResultModel list(@RequestBody P6eUserParamVo param) {
        try {
            P6eListResultDto<P6eUserResultDto> p6eListResultDto
                    = p6eUserService.list(CopyUtil.run(param, P6eUserParamDto.class));
            P6eListResultVo<P6eUserResultVo> p6eListResultVo =
                    new P6eListResultVo<>(
                            CopyUtil.run(p6eListResultDto.getList(), P6eUserResultVo.class),
                            p6eListResultDto.getPage(),
                            p6eListResultDto.getSize(),
                            p6eListResultDto.getTotal()
                    );
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST, p6eListResultVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @PostMapping("/create")
    public P6eResultModel create(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST,
                    p6eUserService.create(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE, RequestMethod.POST })
    public P6eResultModel delete(@RequestBody P6eUserParamVo param) {
        try {
            P6eUserResultDto p6eUserResultDto = p6eUserService.delete(CopyUtil.run(param, P6eUserParamDto.class));
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST,
                    CopyUtil.run(p6eUserResultDto, P6eUserResultVo.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.POST })
    public P6eResultModel update(@RequestBody P6eUserParamVo param) {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_USER_LIST, p6eUserService.update(CopyUtil.run(param, P6eUserParamDto.class)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }

    @PostMapping("/upload")
    public P6eResultModel upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            long fileSize = multipartFile.getSize();
            String file = multipartFile.getName().toLowerCase();

            if (fileSize > 1024 * 1024 * 3) {
                return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE);
            }

            if (!file.endsWith(".jpg")
                    || !file.endsWith(".png")
                    ||!file.endsWith(".gif")
                    || !file.endsWith(".jpeg")) {
                return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE);
            }
            String newFile = p6eUserService.upload(file);
            multipartFile.transferTo(new File(newFile));
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE);
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(P6eResultConfig.ERROR_SERVICE_INSIDE, e.getMessage());
        }
    }
}
