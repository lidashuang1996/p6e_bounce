package com.p6e.bounce.mybatis;

import com.p6e.bounce.model.base.P6eBaseDb;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;

/**
 * MyBatis 拦截器
 * 1. 处理默认参数
 * 2. 设置查询的最大值
 * @author LiDaShuang
 * @version 1.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class MybatisPagingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数
        Object param = invocation.getArgs()[1];
        if (param instanceof HashMap) {
            @SuppressWarnings("all") // 忽略提示
            HashMap hashMap = (HashMap) param;
            for (Object o : hashMap.values()) execute(o);
            invocation.getArgs()[1] = hashMap;
        } else {
            P6eBaseDb p6eBaseDb = new P6eBaseDb();
            p6eBaseDb.setPage(0);
            p6eBaseDb.setSize(16);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("DB", p6eBaseDb);
            invocation.getArgs()[1] = hashMap; // 将修改的对象赋值到原来的对象
        }
        return invocation.proceed();
    }

    private void execute(Object param) {
        if (param instanceof P6eBaseDb) {
            P6eBaseDb db = (P6eBaseDb) param;
            if (db.getPage() == null || db.getPage() <= 0) db.setPage(1);
            if (db.getSize() == null || db.getSize() < 0) db.setSize(16);
            if (db.getSize() > 50) db.setSize(50);
            db.setPage((db.getPage() - 1) * db.getSize());
        }
    }
}
