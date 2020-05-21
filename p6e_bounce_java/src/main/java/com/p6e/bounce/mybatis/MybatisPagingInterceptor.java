package com.p6e.bounce.mybatis;

import com.p6e.bounce.model.base.P6eBaseDb;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }))
public class MybatisPagingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数
        Object param = invocation.getArgs()[1];
        if (param instanceof P6eBaseDb) {
            P6eBaseDb db = (P6eBaseDb) param;
            if (db.getPage() == null || db.getPage() <= 0) db.setPage(1);
            if (db.getSize() == null || db.getSize() < 0) db.setSize(16);
            if (db.getSize() > 50) db.setSize(50);
            db.setPage((db.getPage() - 1) * db.getSize());
            invocation.getArgs()[1] = db; // 将修改的对象赋值到原来的对象
        }
        return invocation.proceed();
    }

}
