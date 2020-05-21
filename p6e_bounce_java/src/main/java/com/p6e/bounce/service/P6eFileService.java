package com.p6e.bounce.service;

import com.p6e.bounce.model.P6eFileModel;

import java.util.List;

public interface P6eFileService {

    /**
     * 获取根目录
     */
    public List<P6eFileModel> root();

    /**
     * 获取指定路径的目录
     */
    public List<P6eFileModel> path(String path);


    public List<P6eFileModel> delete(String path);

    public List<P6eFileModel> rename(String path, String rename);

    public List<P6eFileModel> mkdir(String path);

    public List<P6eFileModel> folder(String path);
}
