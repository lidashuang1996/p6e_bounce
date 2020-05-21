package com.p6e.bounce.mapper;

import com.p6e.bounce.model.db.P6eUserDb;

/**
 * 数据库用户表的映射
 */
public interface P6eUserMapper {

    /**
     * 验证账号密码
     * @param db 查询对象
     */
    public P6eUserDb select(P6eUserDb db);

}
