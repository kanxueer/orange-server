package com.skbaby.orange.dto;

import lombok.Data;

@Data
public class UserTokenDto {
    private int userId;
    private String token;
    private String username;
    private String profile;
}
