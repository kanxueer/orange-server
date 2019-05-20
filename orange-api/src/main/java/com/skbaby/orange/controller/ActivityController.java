package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.aspect.SecurityAspect;
import com.skbaby.orange.controller.util.ResponseUtil;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.enums.ErrorCode;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询活动
     *
     * @param id id
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/activity/{id}")
    public String getActivity(@PathVariable("id") Integer id) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Activity activity = activityService.queryActivityById(id);
        responseType.setData(activity);
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
            data.put("id", activityId);
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

        int activityId = activityService.updateActivity(requestType);
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", activityId);
        responseType.setData(data);

        return JSON.toJSONString(responseType);
    }

    /**
     * 删除Activity
     * @param id id
     * @return responseType
     */
    @SecurityAspect
    @DeleteMapping(value = "/orange/activity/{id}")
    public String deleteActivity(@PathVariable("id") Integer id){
        ResponseType responseType = ResponseUtil.defaultResponse();
        try {
            activityService.deleteActivity(id);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.DELETE_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.DELETE_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }
}
