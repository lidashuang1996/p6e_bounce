package com.p6e.bounce.service.impl;

import com.p6e.bounce.cache.IP6eCacheAuth;
import com.p6e.bounce.controller.support.P6eAuthModel;
import com.p6e.bounce.model.dto.P6eSignResultDto;
import com.p6e.bounce.service.P6eSignService;
import com.p6e.bounce.utils.CommonUtil;
import com.p6e.bounce.utils.CopyUtil;
import com.p6e.bounce.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class P6eSignServiceImpl implements P6eSignService {

    @Autowired
    private IP6eCacheAuth p6eCacheAuth;

    @Override
    public P6eSignResultDto out(String token) {
        P6eAuthModel p6eAuthModel = null;
        P6eSignResultDto p6eSignResultDto = new P6eSignResultDto();
        try {
            p6eAuthModel = this.clean(p6eCacheAuth.getUserToken(token));
        } catch (Exception e) {
            // 忽略异常
        } finally {
            p6eCacheAuth.delUserToken(token);
        }
        if (p6eAuthModel == null) p6eSignResultDto.setError("ERROR");
        return p6eSignResultDto;
    }

    @Override
    public P6eSignResultDto refresh(String rToken) {
        P6eAuthModel p6eAuthModel = null;
        P6eSignResultDto p6eSignResultDto = new P6eSignResultDto();
        try {
            p6eAuthModel = this.clean(p6eCacheAuth.getUserRefreshToken(rToken));
        } catch (Exception e) {
            // 忽略异常
        } finally {
            p6eCacheAuth.delUserRefreshToken(rToken);
        }
        if (p6eAuthModel != null) {
            String id = String.valueOf(p6eAuthModel.getId());
            String token = CommonUtil.generateUUID();
            String refreshToken = CommonUtil.generateUUID();
            p6eAuthModel.setToken(token);
            p6eAuthModel.setRefreshToken(refreshToken);
            p6eCacheAuth.setUserInfo(id, GsonUtil.toJson(p6eAuthModel));
            p6eCacheAuth.setUserToken(token, id);
            p6eCacheAuth.setUserRefreshToken(refreshToken, id);
            p6eSignResultDto = CopyUtil.run(p6eAuthModel, P6eSignResultDto.class);
        } else p6eSignResultDto.setError("ERROR");
        return p6eSignResultDto;
    }


    private P6eAuthModel clean(String id){
        if (id != null) {
            String info = p6eCacheAuth.getUserInfo(id);
            if (info != null) {
                try {
                    P6eAuthModel p6eAuthModel = GsonUtil.fromJson(info, P6eAuthModel.class);
                    if (p6eAuthModel != null) {
                        String refreshToken = p6eAuthModel.getRefreshToken();
                        p6eCacheAuth.delUserRefreshToken(refreshToken);
                        return p6eAuthModel;
                    }
                } catch (Exception e) {
                    // 忽略异常
                } finally {
                    p6eCacheAuth.delUserInfo(id);
                }
            }
        }
        return null;
    }
}
