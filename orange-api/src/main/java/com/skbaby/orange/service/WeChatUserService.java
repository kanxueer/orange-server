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

    public WeChatUser queryWeChatUser(String openid) {
        return weChatUserMapper.queryByOpenId(openid);
    }

    public int updateWeChatUser(RequestType requestType){
        WeChatUser weChatUser = convertWeChatUser(requestType);
        weChatUserMapper.updateWeChatUser(weChatUser);
        weChatUser = weChatUserMapper.queryByUserId(weChatUser.getId());
        //更新Redis
        redisUtil.remove(requestType.getToken());
        redisUtil.save(requestType.getToken(), JSON.toJSONString(weChatUser));

        return weChatUser.getId();
    }

    public WeChatUser getWeChatUser(){
        return JSON.parseObject(redisUtil.get(SecurityThreadLocal.get().getToken()),WeChatUser.class);
    }

    private WeChatUser convertWeChatUser(RequestType requestType) {
        WeChatUser weChatUser = new WeChatUser();
        weChatUser.setId(requestType.getUserId());
        weChatUser.setOpenid(requestType.getOpenId());
        weChatUser.setUsername(requestType.getUsername());
        weChatUser.setProfile(requestType.getProfile());
        return weChatUser;
    }
}
