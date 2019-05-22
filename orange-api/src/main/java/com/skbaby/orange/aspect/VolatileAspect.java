package com.skbaby.orange.aspect;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.dto.UserTokenDto;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.enums.ErrorCode;
import com.skbaby.orange.util.RedisUtil;
import com.skbaby.orange.util.SecurityThreadLocal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class VolatileAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.skbaby.orange.aspect.SecurityAspect)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public String Interceptor(ProceedingJoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            ResponseType response = new ResponseType();
            response.setCode(ErrorCode.SECURITY_ERROR.getCode());
            response.setErr_msg(ErrorCode.SECURITY_ERROR.getMsg());
            return JSON.toJSONString(response);
        }
        String userInfo = redisUtil.get(token);
        if (StringUtils.isEmpty(userInfo)){
            ResponseType response = new ResponseType();
            response.setCode(ErrorCode.NEED_REGIST.getCode());
            response.setErr_msg(ErrorCode.NEED_REGIST.getMsg());
            return JSON.toJSONString(response);
        }
        String result = "";
        try {
            WeChatUser user = JSON.parseObject(userInfo, WeChatUser.class);
            UserTokenDto ut = new UserTokenDto();
            ut.setUserId(user.getId());
            ut.setToken(token);
            SecurityThreadLocal.set(ut);
            result = pjp.proceed().toString();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            SecurityThreadLocal.remove();
        }
        return result;
    }
}