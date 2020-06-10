package com.p6e.bounce.mapper;

import com.p6e.bounce.model.db.P6eProverbDb;
import org.apache.ibatis.annotations.Param;

/**
 * 数据库谚语表的映射
 * @author LiDaShuang
 * @version 1.0
 */
public interface P6eProverbMapper {

    /**
     * 查询数据的总数
     * @return 数据的总数
     */
    public int count();

    /**
     * 创建一条谚语
     * @param db 创建数对象
     * @return 操作的数据条数
     */
    public int create(@Param("DB") P6eProverbDb db);

    /**
     * 查询一条谚语
     * @param db 查询数对象
     * @return 查询结果对象
     */
    public P6eProverbDb select(@Param("DB") P6eProverbDb db);

}
