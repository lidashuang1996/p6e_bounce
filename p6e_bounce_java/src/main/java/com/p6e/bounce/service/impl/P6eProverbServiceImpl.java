package com.p6e.bounce.service.impl;

import com.p6e.bounce.mapper.P6eProverbMapper;
import com.p6e.bounce.model.db.P6eProverbDb;
import com.p6e.bounce.model.dto.P6eProverbParamDto;
import com.p6e.bounce.model.dto.P6eProverbResultDto;
import com.p6e.bounce.service.P6eProverbService;
import com.p6e.bounce.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class P6eProverbServiceImpl implements P6eProverbService {

    @Autowired
    private P6eProverbMapper p6eProverbMapper;

    @Override
    public P6eProverbResultDto get() {
        P6eProverbResultDto p6eProverbResultDto = new P6eProverbResultDto();
        int count = p6eProverbMapper.count();
        if (count > 1) {
            P6eProverbDb paramDb = new P6eProverbDb();
            paramDb.setId(ThreadLocalRandom.current().nextInt(1, count));
            P6eProverbDb p6eProverbDb = p6eProverbMapper.select(paramDb);
            p6eProverbResultDto.setId(p6eProverbDb.getId());
            p6eProverbResultDto.setAuthor(p6eProverbDb.getAuthor());
            p6eProverbResultDto.setContent(p6eProverbDb.getContent());
            p6eProverbResultDto.setDate(p6eProverbDb.getDate());
        } else p6eProverbResultDto.setError("ERROR_NO_DATA");
        return p6eProverbResultDto;
    }

    @Override
    public P6eProverbResultDto create(P6eProverbParamDto param) {
        P6eProverbDb p6eProverbDb = CopyUtil.run(param, P6eProverbDb.class);
        if (p6eProverbMapper.create(p6eProverbDb) > 0) {
            return CopyUtil.run(p6eProverbMapper.select(p6eProverbDb), P6eProverbResultDto.class);
        } else {
            P6eProverbResultDto p6eProverbResultDto = new P6eProverbResultDto();
            p6eProverbResultDto.setError("ERROR_CREATE");
            return p6eProverbResultDto;
        }
    }
}
