package com.p6e.bounce.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器跨域处理过滤器
 * @author LiDaShuang
 * @version 1.0
 */
@WebFilter(filterName = "CrossDomainFilter", urlPatterns = {"*"})
public class P6eCrossDomainFilter implements Filter {

    /**
     * 注入日志系统
     */
    private static final Logger logger = LoggerFactory.getLogger(P6eCrossDomainFilter.class);

    /**
     * 自定义的 HttpServletRequestWrapper 用来添加自己定义的请求头
     */
    private static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String> myHeader = new HashMap<>();

        MyHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            Enumeration<String> headerNames =  request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String value = request.getHeader(name);
                    myHeader.put(name, value);
                }
            }
        }

        void setHeader(String name, String value) {
            myHeader.put(name.toLowerCase(), value.toLowerCase());
        }

        void setCrossDomain() {
            myHeader.put("cross-domain", "existence");
        }

        boolean isCrossDomain() {
            String crossDomainHeader = myHeader.get("cross-domain");
            return "existence".equals(crossDomainHeader);
        }

        @Override
        public String getHeader(String name) {
            return myHeader.get(name.toLowerCase());
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return Collections.enumeration(myHeader.keySet());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Filter [ CrossDomainFilter ] init complete ... ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String origin = "*";
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String element = enumeration.nextElement();
                if ("origin".equals(element.toLowerCase())) {
                    origin = request.getHeader(element);
                }
            }
        }

        MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
        if (!myHttpServletRequestWrapper.isCrossDomain()) {
            // 头部添加 Cross-Domain 为 existence 标记这个请求已经做了跨域处理
            myHttpServletRequestWrapper.setCrossDomain();
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            // 设置允许 cookie
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Depth, "
                    + "User-Agent, X-File-Size, X-Requested-With, X-Requested-By, If-Modified-Since, "
                    + "X-File-Name, X-File-Type, Cache-Control, Origin, Client");
        }

        // 是否为 OPTIONS 方法
        String method = request.getMethod();
        if (method.toUpperCase().equals("OPTIONS")) response.setStatus(200);
        else filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("Filter [ CrossDomainFilter ] destroy complete ... ");
    }

}
