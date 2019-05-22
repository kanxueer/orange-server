package com.skbaby.orange.util;

import com.skbaby.orange.dto.UserTokenDto;

public class SecurityThreadLocal {
    private static final ThreadLocal<UserTokenDto> LOCAL = new ThreadLocal<>();

    private SecurityThreadLocal(){
    }

    public static void set(UserTokenDto user){
        LOCAL.set(user);
    }

    public static UserTokenDto get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
