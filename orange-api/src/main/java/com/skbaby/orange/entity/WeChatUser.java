package com.skbaby.orange.entity;

import lombok.Data;

@Data
public class WeChatUser {
    private int id;
    private String username;
    private String openid;
    private String profile;
    private String state;
}
