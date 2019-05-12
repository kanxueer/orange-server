package com.skbaby.orange.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestType {
    private int activityId;
    private int userId;
    private int partId;

    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String unit;
    private String position;

    private int partQuantity;
    private int activityQuantity;

    private int shareType;
    private int shareId;
}
