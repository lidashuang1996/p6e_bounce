package com.p6e.bounce.mapper;

import com.google.gson.Gson;
import com.p6e.bounce.model.db.P6eUserDb;
import com.p6e.bounce.mybatis.MyBatisTool;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class P6eUserMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(P6eUserMapperTest.class);

    @Autowired
    private P6eUserMapper p6eUserMapper;

    @Test
    void select() {
        P6eUserDb p6eUserDb = new P6eUserDb();
        p6eUserDb.setAccount("test@test.com");
        p6eUserDb.setPassword("123456");
        logger.info("[ PASSWORD ] ==> " + MyBatisTool.encryption("123456"));
        P6eUserDb resultP6eUserDb = p6eUserMapper.select(p6eUserDb);
        logger.info(new Gson().toJson(resultP6eUserDb));
    }


}
