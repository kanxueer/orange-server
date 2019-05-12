package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.enums.ResponseCode;
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
    @GetMapping(value = "/orange/activity/{id}")
    public String getActivity(@PathVariable("id") Integer id) {
        ResponseType responseType = defaultResponse();
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
    @PostMapping(value = "/orange/activity")
    public String insertActivity(@RequestBody RequestType requestType) {
        ResponseType responseType = defaultResponse();
        Integer activityId;
        try {
            activityId = activityService.insertActivity(requestType);
            HashMap<String, Integer> data = new HashMap<>();
            data.put("id", activityId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(ResponseCode.Insert_Error.getCode());
            responseType.setErr_msg(ResponseCode.Insert_Error.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 更新
     * @param requestType requestType
     * @return id
     */
    @PutMapping(value = "/orange/activity")
    public String updateActivity(@RequestBody RequestType requestType) {
        ResponseType responseType = defaultResponse();

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
    @DeleteMapping(value = "/orange/activity/{id}")
    public String deleteActivity(@PathVariable("id") Integer id){
        ResponseType responseType = defaultResponse();
        try {
            activityService.deleteActivity(id);
        } catch (DaoException e) {
            responseType.setCode(ResponseCode.Delete_Error.getCode());
            responseType.setErr_msg(ResponseCode.Delete_Error.getMsg());
        }
        return JSON.toJSONString(responseType);
    }


    private ResponseType defaultResponse() {
        ResponseType responseType = new ResponseType();

        responseType.setCode(ResponseCode.SUCCESS.getCode());
        responseType.setErr_msg(ResponseCode.SUCCESS.getMsg());
        return responseType;
    }
}
