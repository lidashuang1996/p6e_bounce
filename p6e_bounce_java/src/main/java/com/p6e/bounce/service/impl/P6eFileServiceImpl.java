package com.p6e.bounce.service.impl;

import com.p6e.bounce.config.P6eFileConfig;
import com.p6e.bounce.model.P6eFileModel;
import com.p6e.bounce.service.P6eFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class P6eFileServiceImpl implements P6eFileService {

    private static final Logger logger = LoggerFactory.getLogger(P6eFileService.class);

    private String root;

    @Autowired
    public P6eFileServiceImpl(P6eFileConfig config) {
        this.root = config.getRoot();
    }

    @Override
    public List<P6eFileModel> root() {
        List<P6eFileModel> result = new ArrayList<>();
        File rootFile = new File(root);
        File[] catalogFiles = rootFile.listFiles();
        if (catalogFiles != null) ergodicFile(rootFile, result);
        return result;
    }

    @Override
    public List<P6eFileModel> path(String path) {
        path = path.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");

        List<P6eFileModel> result = new ArrayList<>();
        File pathFile = new File(root + path);
        File[] catalogFiles = pathFile.listFiles();
        if (catalogFiles != null) ergodicFile(pathFile, result);
        return result;
    }

    @Override
    public List<P6eFileModel> delete(String path) {
        path = path.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");
        File file = new File(root + path);
        if (file.exists() && !file.delete()) {
            logger.error("[ " + file + " ] delete error.");
        }
        return path(getFolderPath(path));
    }

    @Override
    public List<P6eFileModel> rename(String path, String rename) {
        path = path.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");
        rename = rename.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");
        File file = new File(root + path);
        if (file.exists() && !file.renameTo(new File(rename))) {
            logger.error("[ " + file + " ] rename error.");
        }
        return path(getFolderPath(path));
    }

    @Override
    public List<P6eFileModel> mkdir(String path) {
        path = path.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");
        File file = new File(root + path);
        if (!file.mkdirs()) logger.error("[ " + file + " ] mkdir error.");
        return path(getFolderPath(path));
    }

    @Override
    public List<P6eFileModel> folder(String path) {
        path = path.replaceAll("\\\\", "/")
                .replaceAll("\\.\\./", "")
                .replaceAll("\\./", "");
        return path(getFolderPath(path));
    }

    private void ergodicFile(File rootFile, List<P6eFileModel> result) {
        File[] catalogFiles = rootFile.listFiles();
        if (catalogFiles != null) {
            for (File catalogFile : catalogFiles) {
                P6eFileModel p6eFileModel = new P6eFileModel();
                p6eFileModel.setName(catalogFile.getName());
                p6eFileModel.setPath(catalogFile.getPath().substring(root.length()));
                p6eFileModel.setSize(catalogFile.length());
                p6eFileModel.setType(catalogFile.isFile() ? "FILE" : "FOLDER");
                result.add(p6eFileModel);
            }
        }
    }

    private String getFolderPath(String path) {
        if (path != null && !path.isEmpty()) {
            for (int i = path.length() - 1; i >= 0; i--) {
                if (path.charAt(i) == '/') {
                    return path.substring(0, i);
                }
            }
        }
        return "/";
    }
}
