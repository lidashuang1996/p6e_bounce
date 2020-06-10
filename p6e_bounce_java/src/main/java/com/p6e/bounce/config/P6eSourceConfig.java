package com.p6e.bounce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 导入 ymal 中的 p6e.source 节点数据
 * @author LiDaShuang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "p6e.source")
public class P6eSourceConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
