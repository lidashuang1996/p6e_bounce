package com.p6e.bounce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 导入 ymal 中的 p6e.file 节点数据
 * @author LiDaShuang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "p6e.file")
public class P6eFileConfig {
    private String root;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
