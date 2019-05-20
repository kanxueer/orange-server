package com.skbaby.orange.service;

import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.entity.Part;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.mapper.WeChatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeChatUserService {

    @Autowired
    private WeChatUserMapper weChatUserMapper;

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
        int rows = weChatUserMapper.updateWeChatUser(weChatUser);
        return weChatUser.getId();
    }

    private WeChatUser convertWeChatUser(RequestType requestType) {
        WeChatUser weChatUser = new WeChatUser();
        weChatUser.setOpenid(requestType.getOpenId());
        weChatUser.setUsername(requestType.getUsername());
        weChatUser.setProfile(requestType.getProfile());
        return weChatUser;
    }

}
