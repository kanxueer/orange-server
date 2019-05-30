package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.aspect.SecurityAspect;
import com.skbaby.orange.util.ResponseUtil;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.enums.ErrorCode;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 通过ID查询活动
     *
     * @param activityId activityId
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/activity/{activityId}")
    public String getActivity(@PathVariable("activityId") Integer activityId) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Activity activity = activityService.queryActivityById(activityId);
        responseType.setData(activity);
        return JSON.toJSONString(responseType);
    }

    /**
     * 通过用户查询活动
     *
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/activity")
    public String getActivityByUserId() {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Activity> activities = activityService.queryActivityByUserId();
        responseType.setData(activities);
        return JSON.toJSONString(responseType);
    }


    /**
     * 创建活动
     *
     * @param requestType requestType
     * @return int
     */
    @SecurityAspect
    @PostMapping(value = "/orange/activity")
    public String insertActivity(@RequestBody RequestType requestType) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Integer activityId;
        try {
            activityId = activityService.insertActivity(requestType);
            HashMap<String, Integer> data = new HashMap<>();
            data.put("activityId", activityId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.INSERT_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.INSERT_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 更新
     * @param requestType requestType
     * @return id
     */
    @SecurityAspect
    @PutMapping(value = "/orange/activity")
    public String updateActivity(@RequestBody RequestType requestType) {
        ResponseType responseType = ResponseUtil.defaultResponse();

        activityService.updateActivity(requestType);
        return JSON.toJSONString(responseType);
    }

    /**
     * 删除Activity
     * @param activityId activityId
     * @return responseType
     */
    @SecurityAspect
    @DeleteMapping(value = "/orange/activity/{activityId}")
    public String deleteActivity(@PathVariable("activityId") Integer activityId){
        ResponseType responseType = ResponseUtil.defaultResponse();
        try {
            activityService.deleteActivity(activityId);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.DELETE_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.DELETE_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 结束活动
     * @return responseType
     */
    @SecurityAspect
    @PostMapping(value = "/orange/activity/close/{activityId}")
    public String closeActivity(@PathVariable("activityId") Integer activityId){
        ResponseType responseType = ResponseUtil.defaultResponse();
        try {
            activityService.closeActivity(activityId);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.CLOSE_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.CLOSE_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }
}
