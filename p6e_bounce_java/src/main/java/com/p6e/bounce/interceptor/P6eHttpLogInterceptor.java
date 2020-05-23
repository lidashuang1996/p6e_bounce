package com.p6e.bounce.interceptor;

import com.p6e.bounce.controller.support.P6eBaseController;
import com.p6e.bounce.model.P6eResultModel;
import com.p6e.bounce.model.base.P6eBaseParamVo;
import com.p6e.bounce.utils.GsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class P6eHttpLogInterceptor {

    /** 注入日志系统 */
    private static final Logger logger = LoggerFactory.getLogger(P6eHttpLogInterceptor.class);
    /** 创建时间格式化对象 */
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Http 请求的日志对象 */
    private static class HttpLogModel {
        private String url;
        private String path;
        private Object body;
        private Object result;
        private String ip;
        private String startDateTime;
        private String endDateTime;

        @Override
        public String toString() {
            return GsonUtil.toJson(this);
        }
    }

    /**
     * 获取请求的方法的包名类名方法名
     * @param jp 代理的对象
     * @return 包名和类目组成的路径
     */
    private static String getPath(JoinPoint jp) {
        String name = jp.getThis().getClass().getName();
        if (!name.contains("$$")) return name + "." + jp.getSignature().getName();
        return name.substring(0, name.indexOf("$$")) + "." + jp.getSignature().getName() + "()";
    }

    /**
     * 获取请求的 URL
     * @return 请求的 URL 内容
     */
    private static String getUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            if (request != null) {
                StringBuffer stringBuffer = request.getRequestURL();
                if (request.getQueryString() != null) stringBuffer.append("?").append(request.getQueryString());
                return stringBuffer.toString();
            }
        }
        return null;
    }

    /** 创建的 Http 日志对象 */
    private HttpLogModel httpLogModel;
    private void before(JoinPoint jp) {
        httpLogModel = new HttpLogModel();
        httpLogModel.url = getUrl();
        httpLogModel.path = getPath(jp);
        httpLogModel.ip = P6eBaseController.obtainIp();
        httpLogModel.startDateTime = LocalDateTime.now().format(dateTimeFormatter);
        Object[] jpArgs = jp.getArgs();
        for (Object jpArg : jpArgs) {
            if (jpArg instanceof P6eBaseParamVo) {
                httpLogModel.body = jpArg;
                break;
            }
        }
    }

    private void after(JoinPoint jp){ }

    private void afterReturning(Object ret) {
        if (ret instanceof P6eResultModel && httpLogModel != null) {
            httpLogModel.result = ret;
            httpLogModel.endDateTime = LocalDateTime.now().format(dateTimeFormatter);
            logger.info(GsonUtil.toJson(httpLogModel));
        }
    }

    private void afterThrows(JoinPoint jp){ }

    private Object around(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }


    /**
     * 声明权限的注解
     */
    @Pointcut("execution(public * com.p6e.bounce.controller.*.*(..))")
    private void interceptor(){}

    @Before("interceptor()")
    public void beforeInterceptor(JoinPoint joinPoint) {
        before(joinPoint);
    }

    @AfterReturning(returning = "ret", pointcut = "interceptor()")
    public void afterReturningInterceptor(Object ret) {
        afterReturning(ret);
    }

    @AfterThrowing("interceptor()")
    public void afterThrowsInterceptor(JoinPoint jp){
        afterThrows(jp);
    }

    @After("interceptor()")
    public void afterInterceptor(JoinPoint jp){
        after(jp);
    }

    @Around("interceptor()")
    public Object aroundInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp);
    }

}
