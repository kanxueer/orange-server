package com.skbaby.orange.service;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.mapper.WeChatUserMapper;
import com.skbaby.orange.util.RedisUtil;
import com.skbaby.orange.util.SecurityThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeChatUserService {

    @Autowired
    private WeChatUserMapper weChatUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    public WeChatUser insertWeChatUser(RequestType requestType) throws DaoException {
        WeChatUser weChatUser = convertWeChatUser(requestType);
        int rows = weChatUserMapper.insertWeChatUser(weChatUser);
        if (rows != 1) {
            throw new DaoException();
        }
        return weChatUser;
    }

    public WeChatUser queryWeChatUser(String openId) {
        return weChatUserMapper.queryByOpenId(openId);
    }

    /**
     * 靠openID和token更新token
     *
     * @param token
     * @param openId
     * @param newtoken
     */
    public void updateUserToken(String token, String openId, String newtoken) {
        weChatUserMapper.updateToken(token, openId, newtoken);
    }

    public int updateWeChatUser(RequestType requestType) {
        WeChatUser weChatUser = convertWeChatUser(requestType);
        weChatUserMapper.updateWeChatUser(weChatUser);
        weChatUser = weChatUserMapper.queryByUserId(weChatUser.getUserId());
        //更新Redis
        redisUtil.remove(SecurityThreadLocal.get().getToken());
        redisUtil.save(SecurityThreadLocal.get().getToken(), JSON.toJSONString(weChatUser));

        return weChatUser.getUserId();
    }

    public WeChatUser getWeChatUser() {
        return JSON.parseObject(redisUtil.get(SecurityThreadLocal.get().getToken()), WeChatUser.class);
    }

    private WeChatUser convertWeChatUser(RequestType requestType) {
        WeChatUser weChatUser = new WeChatUser();
        try {
            weChatUser.setUserId(SecurityThreadLocal.get().getUserId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("初次登陆没有ID");
        }
        weChatUser.setToken(requestType.getToken());
        weChatUser.setOpenId(requestType.getOpenId());
        weChatUser.setUsername(requestType.getUsername());
        weChatUser.setProfile(requestType.getProfile());
        return weChatUser;
    }
}
