package com.p6e.bounce.controller;

import com.p6e.bounce.config.P6eFileConfig;
import com.p6e.bounce.model.P6eResultConfig;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.service.P6eFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/file")
public class P6eFileController {

    /** 注入日志系统 */
    private static final Logger logger = LoggerFactory.getLogger(P6eFileController.class);

    @Autowired
    private P6eFileConfig p6eFileConfig;

    @Autowired
    private P6eFileService p6eFileService;

    @PostMapping("/")
    public P6eResultModel def() {
        return root();
    }

    @PostMapping("/root")
    public P6eResultModel root() {
        try {
            return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_ROOT, p6eFileService.root());
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/path")
    public P6eResultModel path(String path) {
        try {
            if (path == null || path.isEmpty()) return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            else return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_PATH, p6eFileService.path(path));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/delete")
    public P6eResultModel delete(String path) {
        try {
            if (path == null || path.isEmpty()) return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            else return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_PATH, p6eFileService.delete(path));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/update")
    public P6eResultModel update(String path, String rename) {
        try {
            if (path == null || path.isEmpty()) return P6eResultModel.build(P6eResultConfig.ERROR_PARAM_EXCEPTION);
            else return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_PATH, p6eFileService.rename(path, rename));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/mkdir")
    public P6eResultModel mkdir(String path) {
        try {
            File file = new File(p6eFileConfig.getRoot() + path);
            if (!file.isDirectory()) return P6eResultModel.build(P6eResultConfig.ERROR_NO_FOLDER);
            if (file.exists()) return P6eResultModel.build(P6eResultConfig.ERROR_FOLDER_EXISTENCE);
            return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_PATH, p6eFileService.mkdir(path));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

    @PostMapping("/upload")
    public P6eResultModel upload(@RequestParam("file") MultipartFile multipartFile, String path) {
        try {
            path = path.replaceAll("\\\\", "/")
                    .replaceAll("\\.\\./", "")
                    .replaceAll("\\./", "");
            File file = new File(p6eFileConfig.getRoot() + path);
            if (!file.isFile()) return P6eResultModel.build(P6eResultConfig.ERROR_NO_FILE);
            if (file.exists()) logger.info("[ UPLOAD ] FILE EXISTE ==> ( " + file + " ) DELETE ? " + file.delete());
            multipartFile.transferTo(file);
            return P6eResultModel.build(P6eResultConfig.SUCCESS_FILE_PATH, p6eFileService.folder(path));
        } catch (Exception e) {
            e.printStackTrace();
            return P6eResultModel.build(500, e.getMessage());
        }
    }

}
