package com.skbaby.orange.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Part {
    private Integer partId;
    private Integer userId;
    private Integer activityId;
    private Integer quantity;
    private Integer shareType;
    private Integer shareId;
    private Integer state;
    private Timestamp dataCreate_LastTime;
    private Timestamp dataChange_LastTime;
    private WeChatUser userInfo;
    private Activity activityInfo;
}
