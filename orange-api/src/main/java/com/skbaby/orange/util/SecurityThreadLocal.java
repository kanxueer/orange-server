package com.skbaby.orange.util;

import com.skbaby.orange.entity.WeChatUser;

public class SecurityThreadLocal {
    private static final ThreadLocal<WeChatUser> LOCAL = new ThreadLocal<>();

    private SecurityThreadLocal(){
    }

    public static void set(WeChatUser user){
        LOCAL.set(user);
    }

    public static WeChatUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
