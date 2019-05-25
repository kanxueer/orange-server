package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.controller.util.ResponseUtil;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.dto.WeChatOpenIDResponse;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.enums.ErrorCode;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.properties.PropertiesConfig;
import com.skbaby.orange.service.WeChatUserService;
import com.skbaby.orange.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private WeChatUserService service;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 微信获取accessToken的url
     */
    private static final String URL_OPENID = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    @PostMapping(value = "/orange/login")
    public String doLogin(@RequestBody RequestType request) {
        String code = request.getCode();
        ResponseType response = getOpenId(code);
        return JSON.toJSONString(response);
    }

    @PostMapping(value = "/orange/refresh")
    public String refreshToken(@RequestBody RequestType request) {
        String token = request.getToken();
        String openId = request.getOpenId();
        String newtoken = DigestUtils.md5DigestAsHex((openId + "_" + System.currentTimeMillis() + "_" + token).getBytes());
        // update现在的token进去
        int row = service.updateUserToken(token, openId, newtoken);

        ResponseType responseType = ResponseUtil.defaultResponse();
        if (row != 1) {
            responseType.setCode(ErrorCode.FAILED_REFRESH.getCode());
            responseType.setErr_msg(ErrorCode.FAILED_REFRESH.getMsg());
        } else {
            WeChatUser weChatUser = service.queryWeChatUser(openId);
            // redis里保存的用户信息不需要token
            weChatUser.setToken(null);
            redisUtil.save(newtoken, JSON.toJSONString(weChatUser));
            redisUtil.remove(token);
            responseType.setData(newtoken);
        }
        return JSON.toJSONString(responseType);
    }


    private ResponseType getOpenId(String code) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        String url = URL_OPENID.replace("APPID", propertiesConfig.getAppId()).replace("SECRET", propertiesConfig.getAppSecret()).replace("JSCODE", code);
        String s = restTemplate.getForObject(url, String.class);
        WeChatOpenIDResponse response = JSON.parseObject(s, WeChatOpenIDResponse.class);
        if (response != null && response.getOpenid() != null && response.getSession_key() != null) {
            //成功
            String openId = response.getOpenid();
            String session_key = response.getSession_key();
            try {
                // 生成Token
                String token = DigestUtils.md5DigestAsHex((openId + "_" + System.currentTimeMillis() + "_" + session_key).getBytes());
                // 判断用户是否存在 openID唯一
                WeChatUser weChatUser = service.queryWeChatUser(openId);

                if (weChatUser == null) {
                    // 创建用户
                    weChatUser = createWeChatUser(openId, token);
                } else {
                    // 删除redis里面的用户之前如果存在的token
                    redisUtil.remove(weChatUser.getToken());
                    // update现在的token进去
                    service.updateUserToken(weChatUser.getToken(), weChatUser.getOpenid(), token);
                    // redis里保存的用户信息不需要token
                    weChatUser.setToken(null);
                }
                // 保存到Redis里面
                redisUtil.save(token, JSON.toJSONString(weChatUser));

                HashMap<String, String> result = new HashMap<>();
                result.put("token", token);
                responseType.setData(result);
            } catch (DaoException e) {
                responseType.setCode(ErrorCode.INSERT_ERROR.getCode());
                responseType.setErr_msg(ErrorCode.INSERT_ERROR.getMsg());
                return responseType;
            }
        } else {
            assert response != null;
            responseType.setCode(Integer.parseInt(response.getErrcode()));
            responseType.setErr_msg(response.getErrmsg());
        }
        return responseType;
    }

    private WeChatUser createWeChatUser(String openId, String token) throws DaoException {
        RequestType request = new RequestType();
        request.setOpenId(openId);
        request.setToken(token);
        return service.insertWeChatUser(request);
    }
}
