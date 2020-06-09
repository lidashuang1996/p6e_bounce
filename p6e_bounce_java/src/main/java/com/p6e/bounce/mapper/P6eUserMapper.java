package com.p6e.bounce.mapper;

import com.p6e.bounce.model.db.P6eUserDb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库用户表的映射
 */
public interface P6eUserMapper {

    /**
     * 验证账号密码
     */
    public P6eUserDb select(@Param("DB")P6eUserDb db);

    /**
     * 查询用户列表数据
     */
    public List<P6eUserDb> selectList(@Param("DB")P6eUserDb db);

    /**
     * 删除用户数据
     */
    public int deleteInfo(@Param("DB")P6eUserDb db);
    public int deleteAuth(@Param("DB")P6eUserDb db);

    /**
     * 创建用户数据
     */
    public int createInfo(@Param("DB")P6eUserDb db);
    public int createAuth(@Param("DB")P6eUserDb db);

    /**
     * 更新用户数据
     */
    public int updateInfo(@Param("DB")P6eUserDb db);
    public int updateAuth(@Param("DB")P6eUserDb db);

    /**
     * 统计长度
     */
    public int count(@Param("DB")P6eUserDb db);
}
