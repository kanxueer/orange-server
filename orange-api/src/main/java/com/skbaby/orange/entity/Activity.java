package com.skbaby.orange.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Activity {
    private int id;
    private int userId;
    private String title;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String unit;
    private String location;
    private int quantity;
    private int state;
    private Timestamp dataCreate_LastTime;
    private Timestamp dataChange_LastTime;
    private List<Part> parts;
}
