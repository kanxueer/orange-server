package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.aspect.SecurityAspect;
import com.skbaby.orange.controller.util.ResponseUtil;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class WeChatUserController {

    @Autowired
    private WeChatUserService userService;

    /**
     * 更新用户信息
     *
     * @param requestType requestType
     * @return int
     */
    @SecurityAspect
    @PostMapping(value = "/orange/user")
    public String updateWeChatUser(@RequestBody RequestType requestType) {
        ResponseType responseType = ResponseUtil.defaultResponse();

        int userId = userService.updateWeChatUser(requestType);
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", userId);
        responseType.setData(data);
        return JSON.toJSONString(responseType);
    }

    /**
     * 获取用户信息
     * @return user
     */
    @SecurityAspect
    @GetMapping(value = "/orange/user")
    public String getWeChatUser(){
        ResponseType responseType = ResponseUtil.defaultResponse();
        WeChatUser user = userService.getWeChatUser();
        HashMap<String, WeChatUser> data = new HashMap<>();
        data.put("user", user);
        responseType.setData(data);
        return JSON.toJSONString(responseType);
    }
}
