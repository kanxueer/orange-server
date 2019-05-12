package com.skbaby.orange.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String openid;
    private String profile;
    private String state;
}
