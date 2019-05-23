package com.skbaby.orange.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class WeChatUser {
    private int id;
    private String username;
    private String openid;
    private String token;
    private String profile;
    private String state;
    private Timestamp dataCreate_LastTime;
    private Timestamp dataChange_LastTime;
}
