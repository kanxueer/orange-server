package com.skbaby.orange.util;

import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.enums.ErrorCode;

public class ResponseUtil {
    public static ResponseType defaultResponse() {
        ResponseType responseType = new ResponseType();
        responseType.setCode(ErrorCode.SUCCESS.getCode());
        return responseType;
    }
}
