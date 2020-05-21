package com.p6e.bounce.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class MybatisPasswordInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数
        Object param = invocation.getArgs()[1];
        if (param != null) {
            Class<?> paramClass = param.getClass();
            List<Field> fields = new ArrayList<>(Arrays.asList(paramClass.getDeclaredFields()));
            for (Field field : fields) {
                if ("PASSWORD".equals(field.getName().toUpperCase())) {
                    field.setAccessible(true);
                    field.set(param, MyBatisPasswordHandle.encryption(field.get(param).toString()));
                }
            }
        }
        return invocation.proceed();
    }

}
