package com.skbaby.orange.entity;

import lombok.Data;

@Data
public class Part {
    private int id;
    private int userId;
    private int activityId;
    private int quantity;
    private int shareType;
    private int shareId;
    private int state;
}
