package com.p6e.bounce.service.impl;

import com.p6e.bounce.cache.IP6eCacheAuth;
import com.p6e.bounce.controller.support.P6eAuthModel;
import com.p6e.bounce.mapper.P6eUserMapper;
import com.p6e.bounce.model.db.P6eUserDb;
import com.p6e.bounce.model.dto.P6eSignInParamDto;
import com.p6e.bounce.model.dto.P6eSignInResultDto;
import com.p6e.bounce.service.P6eSignInService;
import com.p6e.bounce.utils.CommonUtil;
import com.p6e.bounce.utils.CopyUtil;
import com.p6e.bounce.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class P6eSignInServiceImpl implements P6eSignInService {

    @Autowired
    private IP6eCacheAuth p6eCacheAuth;

    @Autowired
    private P6eUserMapper p6eUserMapper;

    @Override
    public P6eSignInResultDto def(P6eSignInParamDto param) {
        P6eSignInResultDto result = new P6eSignInResultDto();
        P6eUserDb p6eUserDb = p6eUserMapper.select(CopyUtil.run(param, P6eUserDb.class));
        if (p6eUserDb != null) {
            String id = String.valueOf(p6eUserDb.getId());
            try {
                String info = p6eCacheAuth.getUserInfo(id);
                if (info != null) {
                    P6eAuthModel p6eAuthModel = GsonUtil.fromJson(info, P6eAuthModel.class);
                    if (p6eAuthModel != null) {
                        if (p6eAuthModel.getToken() != null) p6eCacheAuth.delUserToken(p6eAuthModel.getToken());
                        if (p6eAuthModel.getRefreshToken() != null) p6eCacheAuth.delUserRefreshToken(p6eAuthModel.getRefreshToken());
                    }
                }
            } catch (Exception e) {
                // 忽略异常
            } finally {
                p6eCacheAuth.delUserInfo(id);
            }
            String token = CommonUtil.generateUUID();
            String refreshToken = CommonUtil.generateUUID();
            P6eAuthModel model = new P6eAuthModel();
            model.setId(Integer.valueOf(id));
            model.setAccount(p6eUserDb.getAccount());
            model.setStatus(p6eUserDb.getStatus());
            model.setGroup(p6eUserDb.getGroup());
            model.setAlias(p6eUserDb.getAlias());
            model.setName(p6eUserDb.getName());
            model.setAvatar(p6eUserDb.getAvatar());
            model.setSex(p6eUserDb.getSex());
            model.setBirthday(p6eUserDb.getBirthday());
            model.setToken(token);
            model.setRefreshToken(refreshToken);
            model.setExpire(604800L);
            p6eCacheAuth.setUserInfo(id, GsonUtil.toJson(model));
            p6eCacheAuth.setUserToken(token, id);
            p6eCacheAuth.setUserRefreshToken(refreshToken, id);
            result = CopyUtil.run(model, P6eSignInResultDto.class);
        } else result.setError("ACCOUNT_OR_PASSWORD_ERROR");
        return result;
    }
}
