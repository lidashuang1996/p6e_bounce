package com.p6e.bounce.mapper;

import com.p6e.bounce.model.db.P6eProverbDb;
import org.apache.ibatis.annotations.Param;

public interface P6eProverbMapper {

    public int count();

    public int create(@Param("DB") P6eProverbDb db);

    public P6eProverbDb select(@Param("DB") P6eProverbDb db);

}
