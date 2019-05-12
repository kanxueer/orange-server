package com.skbaby.orange.dto;

import lombok.Data;

@Data
public class ResponseType {
    Object data;
    String err_msg;
    int code;
}
