package com.skbaby.orange.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Activity {
    private Integer activityId;
    private Integer userId;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String unit;
    private String location;
    private Integer quantity;
    private Integer state;
    private Timestamp dataCreate_LastTime;
    private Timestamp dataChange_LastTime;
    private List<Part> parts;
    private WeChatUser userInfo;
}
