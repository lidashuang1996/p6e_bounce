package com.p6e.bounce.service.impl;

import com.p6e.bounce.cache.IP6eCacheAuth;
import com.p6e.bounce.cache.IP6eCacheFile;
import com.p6e.bounce.controller.support.P6eAuthModel;
import com.p6e.bounce.mapper.P6eUserMapper;
import com.p6e.bounce.model.db.P6eUserDb;
import com.p6e.bounce.model.dto.P6eListResultDto;
import com.p6e.bounce.model.dto.P6eUserParamDto;
import com.p6e.bounce.model.dto.P6eUserResultDto;
import com.p6e.bounce.service.P6eUserService;
import com.p6e.bounce.utils.CommonUtil;
import com.p6e.bounce.utils.CopyUtil;
import com.p6e.bounce.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class P6eUserServiceImpl implements P6eUserService {

    private static final Logger logger = LoggerFactory.getLogger(P6eUserService.class);

    @Autowired
    private IP6eCacheFile p6eCacheFile;

    @Autowired
    private IP6eCacheAuth p6eCacheAuth;

    @Autowired
    private P6eUserMapper p6eUserMapper;

    @Override
    public P6eListResultDto<P6eUserResultDto> list(P6eUserParamDto param) {
        P6eUserDb p6eUserDb = CopyUtil.run(param, P6eUserDb.class);
        int total = p6eUserMapper.count(p6eUserDb);
        List<P6eUserDb> p6eUserDbList = p6eUserMapper.selectList(p6eUserDb);
        List<P6eUserResultDto> resultDtoList = CopyUtil.run(p6eUserDbList, P6eUserResultDto.class);
        return new P6eListResultDto<>(
                resultDtoList,
                (long) (p6eUserDb.getPage() / p6eUserDb.getSize() + 1),
                (long) p6eUserDb.getSize(),
                (long) total
        );
    }

    @Override
    @Transactional
    public P6eUserResultDto delete(P6eUserParamDto param) {
        P6eUserResultDto p6eUserResultDto = new P6eUserResultDto();
        P6eUserDb paramP6eUserDb = CopyUtil.run(param, P6eUserDb.class);
        P6eUserDb resultP6eUserDb = p6eUserMapper.select(paramP6eUserDb);
        if (resultP6eUserDb != null) {
            if (p6eUserMapper.deleteInfo(paramP6eUserDb) <= 0) {
                logger.error("[ USER DELETE INFO ] ==> ID: " + param.getId() + ", ERROR.");
            }
            if (p6eUserMapper.deleteAuth(paramP6eUserDb) <= 0) {
                logger.error("[ USER DELETE AUTH ] ==> ID: " + param.getId() + ", ERROR.");
            }
            p6eUserResultDto = CopyUtil.run(resultP6eUserDb, P6eUserResultDto.class);
        } else p6eUserResultDto.setError("ID_NO_EXISTENCE");
        return p6eUserResultDto;
    }

    @Override
    @Transactional
    public P6eUserResultDto create(P6eUserParamDto param) {
        P6eUserDb p6eUserDb = CopyUtil.run(param, P6eUserDb.class);
        if (p6eUserMapper.createInfo(p6eUserDb) <= 0) {
            logger.error("[ USER CREATE INFO ] ==> ID: " + param.getId() + ", ERROR.");
        }
        if (p6eUserMapper.createAuth(p6eUserDb) <= 0) {
            logger.error("[ USER CREATE AUTH ] ==> ID: " + param.getId() + ", ERROR.");
        }
        P6eUserDb result = p6eUserMapper.select(p6eUserDb);
        return CopyUtil.run(result, P6eUserResultDto.class);
    }

    @Override
    @Transactional
    public P6eUserResultDto update(P6eUserParamDto param) {
        P6eUserDb p6eUserDb = CopyUtil.run(param, P6eUserDb.class);
        if (p6eUserMapper.updateInfo(p6eUserDb) <= 0) {
            logger.error("[ USER UPDATE INFO ] ==> ID: " + param.getId() + ", ERROR.");
        }
        if (p6eUserMapper.updateAuth(p6eUserDb) <= 0) {
            logger.error("[ USER UPDATE AUTH ] ==> ID: " + param.getId() + ", ERROR.");
        }
        String id = String.valueOf(p6eUserDb.getId());
        P6eUserDb result = p6eUserMapper.select(p6eUserDb);
        try {
            String userInfo = p6eCacheAuth.getUserInfo(id);
            if (userInfo != null) {
                P6eAuthModel p6eAuthModel = GsonUtil.fromJson(userInfo, P6eAuthModel.class);
                if (p6eAuthModel != null) {
                    p6eCacheAuth.delUserToken(p6eAuthModel.getToken());
                    p6eCacheAuth.delUserRefreshToken(p6eAuthModel.getRefreshToken());
                }
            }
        } catch (Exception e) {
            // 忽略异常
        } finally {
            p6eCacheAuth.delUserInfo(id);
        }
        return CopyUtil.run(result, P6eUserResultDto.class);
    }

    @Override
    public String upload(String file) {
        String fileName = CommonUtil.generateUUID();
        String fileSuffix = getFileSuffixName(file);
        String rFile = fileName + fileSuffix;
        // 缓存文件对象，如何没有被删除，说明文件是一个没有被使用的文件，我们之后需要进行删除
        p6eCacheFile.set(rFile);
        return rFile;
    }

    private String getFileSuffixName(String file) {
        if (file == null || file.isEmpty()) return "";
        else {
            for (int i = file.length() - 1; i >= 0; i--) {
                char ch = file.charAt(i);
                if (ch == '.') return file.substring(i + 1);
            }
            return "";
        }
    }

}
