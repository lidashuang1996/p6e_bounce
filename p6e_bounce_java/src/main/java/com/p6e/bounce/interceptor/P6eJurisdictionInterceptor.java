//package com.p6e.bounce.interceptor;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class P6eJurisdictionInterceptor {
//
//    /**
//     * 注入日志系统
//     */
//    private Logger logger = LoggerFactory.getLogger(P6eJurisdictionInterceptor.class);
//
//    /**
//     * 获取请求的方法的包名类名方法名
//     */
//    private String pathName(JoinPoint jp) {
//        return "";
//    }
//
//    private void before(JoinPoint jp) {
//        logger.info("Access method [ " + pathName(jp) + " ]");
//    }
//
//    private void after(JoinPoint jp){
//        logger.info("Method execution completed [ "+ pathName(jp) + " ]");
//    }
//
//    private void afterReturning(Object ret) {
//        logger.info("Value returned by request [ " + ret + " ]");
//    }
//
//    private void afterThrows(JoinPoint jp){
//        logger.info("Request exception [ " + jp + " ]");
//    }
//
//    private Object around(ProceedingJoinPoint pjp) throws Throwable {
//        return pjp.proceed();
//    }
//
//
//    /**
//     * 声明权限的注解
//     */
//    @Pointcut("execution(public * com.p6e.bounce.controller.*.*(..))")
//    private void jurisdiction(){}
//
//    @Before("jurisdiction()")
//    public void beforeJurisdiction(JoinPoint joinPoint) {
//        before(joinPoint);
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "jurisdiction()")
//    public void afterReturningJurisdiction(Object ret) {
//        afterReturning(ret);
//    }
//
//    @AfterThrowing("jurisdiction()")
//    public void afterThrowsJurisdiction(JoinPoint jp){
//        afterThrows(jp);
//    }
//
//    @After("jurisdiction()")
//    public void afterJurisdiction(JoinPoint jp){
//        after(jp);
//    }
//
//    @Around("jurisdiction()")
//    public Object aroundJurisdiction(ProceedingJoinPoint pjp) throws Throwable {
//        return around(pjp);
//    }
//
//}
