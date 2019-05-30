package com.skbaby.orange.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Part {
    private int partId;
    private int userId;
    private int activityId;
    private int quantity;
    private int shareType;
    private int shareId;
    private int state;
    private Timestamp dataCreate_LastTime;
    private Timestamp dataChange_LastTime;
}
