package com.p6e.bounce.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 请求的工具包
 */
public final class HttpUtil {

    /**
     * 发送 GET 请求
     */
    public static String get(String url) throws IOException {
        return get(url, null, null);
    }

    public static String get(String url, Map<String, String> params) throws IOException {
        return get(url, params, null);
    }

    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        if (params != null) {
            StringBuilder pContent = new StringBuilder();
            for (String name : params.keySet()) {
                pContent.append("&")
                        .append(URLEncoder.encode(name, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(params.get(name), "UTF-8"));
            }
            if (params.size() > 0) url += "?" + pContent.substring(1);
        }
        return http(new HttpGet(url), headers);
    }

    /**
     * 发送 POST 请求
     */
    public static String post(String url) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, null, headers);
    }

    public static String post(String url, String params) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, params, headers);
    }

    public static String post(String url, Map<String, String> params) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return post(url, GsonUtil.toJson(params), headers);
    }

    public static String post(String url, String params, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (params != null) httpPost.setEntity(new StringEntity(params));
        return http(httpPost, headers);
    }

    private static String http(HttpUriRequest httpUriRequest, Map<String, String> headers) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        if (headers != null) {
            for (String name : headers.keySet()) httpUriRequest.setHeader(name, headers.get(name));
        }
        HttpResponse httpResponse = httpClient.execute(httpUriRequest);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }
}
