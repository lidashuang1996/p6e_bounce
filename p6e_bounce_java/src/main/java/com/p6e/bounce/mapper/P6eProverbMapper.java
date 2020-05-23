package com.p6e.bounce.mapper;

import com.p6e.bounce.model.db.P6eProverbDb;

public interface P6eProverbMapper {

    public int count();

    public int create(P6eProverbDb db);

    public P6eProverbDb select(P6eProverbDb db);

}
