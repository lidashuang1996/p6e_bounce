package com.p6e.bounce.mybatis;

import com.p6e.bounce.model.base.P6eBaseDb;
import com.p6e.bounce.utils.GsonUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * MyBatis 拦截器，对密码参数进行加密处理
 * @author LiDaShuang
 * @version 1.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class MybatisPasswordInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数
        Object param = invocation.getArgs()[1];
        if (param instanceof HashMap) {
            @SuppressWarnings("all") // 忽略提示
            HashMap hashMap = (HashMap) param;
            for (Object o : hashMap.keySet()) {
                if (o != null && "DB".equals(String.valueOf(o))) execute(hashMap.get(o));
            }
            invocation.getArgs()[1] = hashMap;
        }
        return invocation.proceed();
    }


    private void execute(Object param) throws IllegalAccessException {
        if (param instanceof P6eBaseDb) {
            Class<?> paramClass = param.getClass();
            List<Field> fields = new ArrayList<>(Arrays.asList(paramClass.getDeclaredFields()));
            for (Field field : fields) {
                field.setAccessible(true);
                if ("PASSWORD".equals(field.getName().toUpperCase()) && field.get(param) != null) {
                    field.set(param, MyBatisTool.encryption(field.get(param).toString()));
                }
            }
        }
    }

}
