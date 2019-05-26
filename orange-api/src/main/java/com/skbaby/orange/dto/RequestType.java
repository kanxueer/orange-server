package com.skbaby.orange.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestType {
    private String token;

    private int activityId;
    private int userId;
    private int partId;

    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String unit;
    private String location;

    private int partQuantity;
    private int activityQuantity;

    private int shareType;
    private int shareId;

    //微信授权码
    private String code;
    private String openId;

    private String username;
    private String profile;
}
