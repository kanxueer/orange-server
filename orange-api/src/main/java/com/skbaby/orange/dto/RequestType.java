package com.skbaby.orange.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestType {
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String unit;
    private String position;
    private int quantity;
}
